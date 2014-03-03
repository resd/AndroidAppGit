package com.eugez.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showFiles = (Button) findViewById(R.id.showFiles);
        Button loadFile = (Button) findViewById(R.id.loadFiles);

        View.OnClickListener oclList = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1 = (EditText) findViewById(R.id.editText);
                EditText path1 = (EditText) findViewById(R.id.pat);
                try {
                    String pathTo = path1.getText().toString();
                    File f = new File(pathTo);
                    File file[] = f.listFiles();
                    String conten = null;
                    for (int i = 0; i < file.length; i++) {
                        conten = file[i].getName();
                        editText1.setText(conten + "/n");
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };


        View.OnClickListener oclLoad = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText path1 = (EditText) findViewById(R.id.pat);
                XmlPullParserFactory pullParserFactory;
                try {
                    pullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = pullParserFactory.newPullParser();
                    InputStream in_s = getApplicationContext().getAssets().open(path1.getText().toString());
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(in_s, null);
                    parseXML(parser);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        loadFile.setOnClickListener(oclLoad);
        showFiles.setOnClickListener(oclList);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Students> students = null;
        ArrayList<Napravlenie> napravleniya = null;
        int eventType = parser.getEventType();
        Students currentStudent = null;
        Napravlenie currentNapravlenie = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    students = new ArrayList<Students>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("student")) {
                        currentStudent = new Students();
                    } else if (currentStudent != null) {
                        if (name == "fio") {
                            currentStudent.fio = parser.nextText();
                        } else if (name == "id") {
                            currentStudent.id = parser.nextText();
                        } else if (name == "ratingPoints") {
                            currentStudent.ratingPoints = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("student") && currentStudent != null) {
                        students.add(currentStudent);
                    }
            }
            eventType = parser.next();
        }
        printStudents(students);
    }

    private void printStudents(ArrayList<Students> students) {
        String content = "";
        Iterator<Students> it = students.iterator();
        while (it.hasNext()) {
            Students currStud = it.next();
            content = content + currStud.fio + "/n";
            content = content + currStud.id + "/n";
            content = content + currStud.ratingPoints + "/n";

        }

        EditText display = (EditText) findViewById(R.id.editText);
        display.setText(content);
    }


}
