package com.eugez.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import android.text.TextUtils;
import android.util.Log;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Simple lister - extract name and email tags from a user file. Updated for SAX
 * 2.0
 */
public class SaxLister {
    final String LOG_TAG = "myLogs";
    ArrayList<String> person = new ArrayList<String>();
    ArrayList<String> department = new ArrayList<String>();
    ArrayList<String> qualification = new ArrayList<String>();
    ArrayList<String> licence = new ArrayList<String>();
    ArrayList<String> speciality = new ArrayList<String>();
    ArrayList<String> budget = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<String> originplace = new ArrayList<String>();
    ArrayList<String> rating = new ArrayList<String>();
    ArrayList<String> privilege = new ArrayList<String>();
    ArrayList<String> docstate = new ArrayList<String>();
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> comment = new ArrayList<String>();
    private ArrayList<String> element = new ArrayList<String>();
    private ArrayList<String> value = new ArrayList<String>();

	public void getData(ArrayList<String> element, ArrayList<String> value){

		for (int i = 0; i < element.size(); i++) {
            //Log.d(LOG_TAG, element.get(i)+ ": " + value.get(i));
			switch (element.get(i)) {
			case "speciality":
				speciality.add(value.get(i));
				break;
			case "budget":
				budget.add(value.get(i));
				break;
			case "place":
				place.add(value.get(i));
				break;
			case "originplace":
				originplace.add(value.get(i));
				break;

			default:
				break;
			}
		}
		/*for (int i = 0; i < speciality.size(); i++) {
			System.out.println(speciality.get(i));
			System.out.println(budget.get(i));
			System.out.println(place.get(i));
			System.out.println(originplace.get(i));
		}*/
	}

    XmlPullParser prepareXpp(String s) throws XmlPullParserException {
        // получаем фабрику
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // включаем поддержку namespace (по умолчанию выключена)
        factory.setNamespaceAware(true);
        // создаем парсер
        XmlPullParser xpp = factory.newPullParser();
        // даем парсеру на вход Reader
        xpp.setInput(new StringReader(s));
        return xpp;
    }
    public void gogo(String s, String userId){
        String tmp = "";

        try {
            XmlPullParser xpp = prepareXpp(s);
            boolean id = false;
            Log.d(LOG_TAG, userId);
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "START_DOCUMENT");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        tmp = "";
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            //Log.d(LOG_TAG, ""+xpp.getAttributeValue(i));
                            if ( xpp.getAttributeName(i).equals("id") && xpp.getAttributeValue(i).equals(userId))
                                id = true;
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                        }
                        if (!(xpp.getName().equals("request") || xpp.getName().equals("requests") || xpp.getName().equals("state"))){
                        if (id){
                            element.add(xpp.getName());
                            /*Log.d(LOG_TAG, "START_TAG: name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + ", attrCount = "
                                + xpp.getAttributeCount());*/
                        }}

                        //if (!TextUtils.isEmpty(tmp))
                            //Log.d(LOG_TAG, "Attributes: " + tmp);
                        //}
                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:
                        if (xpp.getName().equals("requests"))
                            id = false;
                        //Log.d(LOG_TAG, "END_TAG: name = " + xpp.getName());
                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        if (!xpp.isWhitespace() && id){
                            //Log.d(LOG_TAG, "text = " + xpp.getText());
                            value.add(xpp.getText());
                        }
                        break;

                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }
            Log.d(LOG_TAG, "END_DOCUMENT");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getData(element, value);
    }
}