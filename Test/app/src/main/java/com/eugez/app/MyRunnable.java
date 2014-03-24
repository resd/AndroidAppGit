package com.eugez.app;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class MyRunnable extends MainActivity {
    final String LOG_TAG = "myLogs";
	private static final String FILE_NAME = "ifs%d.txt";
	private static final String URL = "http://ac.opu.ua/2014/xml/xml_vstup_onpu.php";//"http://ac.opu.ua/2014/xml/xml_vstup_onpu.php&id=%s";

	public MyRunnable() {
		}

    public byte[] go() {
        byte[] data;

        String s = "12345"; // getStudentId
        final String url = String.format(URL, s);
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            uc.connect();
            uc.getContent();
            String contentType = uc.getContentType();
            int contentLength = uc.getContentLength();
            data = getData(uc, contentLength, u);
            Log.d(LOG_TAG, "Success");
            return data;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = null;
        return data;
    }

    byte[] getData(URLConnection uc, int contentLength, URL u) {
        InputStream raw = null;
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
