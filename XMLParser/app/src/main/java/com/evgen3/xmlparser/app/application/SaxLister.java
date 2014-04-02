package com.evgen3.xmlparser.app.application;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Simple lister - extract name and email tags from a user file. Updated for SAX
 * 2.0
 */
public class SaxLister {
    final String LOG_TAG = "myLogs";

    private ArrayList<String> element = new ArrayList<String>();
    private ArrayList<String> value = new ArrayList<String>();

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

    public void gogo(String s){
        try {
            XmlPullParser xpp = prepareXpp(s);
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "START_DOCUMENT");
                        break;
                    // начало тэга
                    case XmlPullParser.START_TAG:
                        if (!(xpp.getName().equals("request") || xpp.getName().equals("requests") || xpp.getName().equals("state") || xpp.getName().equals("abiturient") )) {
                            element.add(xpp.getName());
                            /*Log.d(LOG_TAG, "START_TAG: name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + ", attrCount = "
                                + xpp.getAttributeCount());*/
                        }

                        //if (!TextUtils.isEmpty(tmp))
                            //Log.d(LOG_TAG, "Attributes: " + tmp);
                        //}
                        break;
                    // конец тэга
                    case XmlPullParser.END_TAG:
                        //Log.d(LOG_TAG, "END_TAG: name = " + xpp.getName());
                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        if (!xpp.isWhitespace()){
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

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getElement(){
        return element;
    }
    public ArrayList<String> getValue(){
        return value;
    }
}