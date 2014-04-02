package com.evgen3.xmlparser.app.service;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MyRunnable {
    final String LOG_TAG = "myLogs";
	private static final String URL = "http://ac.opu.ua/2014/xml/xml_vstup_onpu.php?id=%s";//"http://ac.opu.ua/2014/xml/xml_vstup_onpu.php&id=%s";

	public MyRunnable() {
		}

    public byte[] go(String id) {
        byte[] data = null;
        final String url = String.format(URL, id);
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            uc.connect();
            uc.getContent();
            int contentLength = uc.getContentLength();
            data = getData(uc, contentLength);
            Log.d(LOG_TAG, "Success");
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    byte[] getData(URLConnection uc, int contentLength) {
        InputStream raw = null;
        if (contentLength <= 0){
            return null;
        }
        byte[] data = new byte[contentLength];
        try {
            raw = uc.getInputStream();
            InputStream in = new BufferedInputStream(raw);
            int bytesRead = 0;
            int offset = 0;
            while (offset < contentLength) {
                bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            in.close();

            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected "
                        + contentLength + " bytes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
