package com.eugez.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Result extends Activity {

    final String LOG_TAG = "myLogs";
    final String FILENAME = "file";// Файл для памяти телефона
    final String DIR_SD = "DCIM"; // Дериктория, содержащая файл
    final String FILENAME_SD = "Text.txt"; // Файл для SD-карты
    String test = "<?xml version='1.0' encoding='UTF-8'?><abiturient>\n" +
            "\t<person id=\"1\" as = \"sadf\" dd = \"sds\">Іванов Іван Іванович</person>\n" +
            "\t<requests>\n" +
            "\t\t<request>\n" +
            "\t\t\t<department>ІКС</department>\n" +
            "\t\t\t<speciality>6.050201 Системна інженерія</speciality>\n" +
            "\t\t\t<qualification>Бакалавр</qualification>\n" +
            "\t\t\t<licence>110</licence>\n" +
            "\t\t\t<budget>50</budget>\n" +
            "\t\t\t<rating>763.5</rating>\n" +
            "\t\t\t<privilege>Вступ поза конкурсом</privilege>\n" +
            "\t\t\t<docstate>Подано оригінали</docstate>\n" +
            "\t\t\t<state>\n" +
            "\t\t\t\t<title>Рекомендовано до зарахування на бюджет</title>\n" +
            "\t\t\t\t<comment>Рекомендація діє до 11.08.2014</comment>\n" +
            "\t\t\t</state>\n" +
            "\t\t\t<place>15</place>\n" +
            "\t\t\t<originplace>2</originplace>\n" +
            "\t\t</request>\n" +
            "\t\t<request>\n" +
            "\t\t\t<department>ІБЕИТ</department>\n" +
            "\t\t\t<speciality>6.030502 Економічна кібернетика</speciality>\n" +
            "\t\t\t<qualification>Бакалавр</qualification>\n" +
            "\t\t\t<licence>80</licence>\n" +
            "\t\t\t<budget>8</budget>\n" +
            "\t\t\t<rating>700.5</rating>\n" +
            "\t\t\t<privilege>Вступ на загальних засадах</privilege>\n" +
            "\t\t\t<docstate>Подано копії</docstate>\n" +
            "\t\t\t<state>\n" +
            "\t\t\t\t<title>Рекомендовано до зарахування на контракт</title>\n" +
            "\t\t\t\t<comment>Рекомендація діє до 11.08.2014</comment>\n" +
            "\t\t\t</state>\n" +
            "\t\t\t<place>267</place>\n" +
            "\t\t\t<originplace>31</originplace>\n" +
            "\t\t</request>\n" +
            "\t</requests>\n" +
            "\t<person id=\"2\">Василий Василиевич</person>\n" +
            "\t<requests>\n" +
            "\t\t<request>\n" +
            "\t\t\t<department>УНІ</department>\n" +
            "\t\t\t<speciality>6.030502 Економічна кібернетика</speciality>\n" +
            "\t\t\t<qualification>Бакалавр</qualification>\n" +
            "\t\t\t<licence>45</licence>\n" +
            "\t\t\t<budget>5</budget>\n" +
            "\t\t\t<rating>543.3</rating>\n" +
            "\t\t\t<privilege>Вступ на загальних засадах</privilege>\n" +
            "\t\t\t<docstate>Подано копії</docstate>\n" +
            "\t\t\t<state>\n" +
            "\t\t\t\t<title>Рекомендовано до зарахування на контракт</title>\n" +
            "\t\t\t\t<comment>Рекомендація діє до 11.08.2014</comment>\n" +
            "\t\t\t</state>\n" +
            "\t\t\t<place>11233</place>\n" +
            "\t\t\t<originplace>312</originplace>\n" +
            "\t\t</request>\n" +
            "\t\t<request>\n" +
            "\t\t\t<department>ІДЗО</department>\n" +
            "\t\t\t<speciality>7.050201 Електромеханічні системи автоматизації та електропривод</speciality>\n" +
            "\t\t\t<qualification>Спеціаліст</qualification>\n" +
            "\t\t\t<licence>120</licence>\n" +
            "\t\t\t<budget>20</budget>\n" +
            "\t\t\t<rating>363.5</rating>\n" +
            "\t\t\t<privilege>Вступ на загальних засадах</privilege>\n" +
            "\t\t\t<docstate>Подано довідку</docstate>\n" +
            "\t\t\t<state>\n" +
            "\t\t\t\t<title>Рекомендовано до зарахування на бюджет</title>\n" +
            "\t\t\t\t<comment>Рекомендація діє до 05.08.2014</comment>\n" +
            "\t\t\t</state>\n" +
            "\t\t\t<place>25</place>\n" +
            "\t\t\t<originplace>12</originplace>\n" +
            "\t\t</request>\n" +
            "\t</requests>\n" +
            "</abiturient>";


    static ArrayList<String> speciality = new ArrayList<String>();
    static ArrayList<String> budget = new ArrayList<String>();
    static ArrayList<String> place = new ArrayList<String>();
    static ArrayList<String> originplace = new ArrayList<String>();
    //тестовое название факультетов
    String[] groups = new String[]{"Системная инженерия", "Программная инженерия",
            "Шоколадная инженерия"};

    String[] firstInfo  =   new String[] {"Рейтинг - 15", "Рейтинг по оригиналам - 10"
                                            , "Бюджетных мест - 12"};
    String[] secondInfo =   new String[] {"Рейтинг - 12", "Рейтинг по оригиналам - 1"
                                         ,"Бюджетных мест - 2"};
    String[] thirdInfo  =   new String[] {"Рейтинг - 5", "Рейтинг по оригиналам - 2"
                                         ,"Бюджетных мест - 25"};

    //коллекция для групп
    ArrayList<Map<String, String>> groupData;
    //коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;
    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;

    //список атрибутов группы или элементов
    Map<String,String> m;

    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        String id;

        MyRunnable my = new MyRunnable();
        byte[] data = my.go();
        if (data.length!=0){
        String t = new String(data);//Arrays.toString(data);
        writeFile(t);
        //Log.d(LOG_TAG, readFile());
        } else
            Log.d(LOG_TAG, "Connection error! Check access to internet!");
        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        SaxLister sax = new SaxLister();
        try {
            sax.gogo(test, id);
            speciality = sax.speciality;
            Log.d(LOG_TAG, "SAXSIZE = "+speciality.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        groups = Arrays.copyOf(speciality.toArray(), speciality.size(), String[].class);
        groupData = new ArrayList<Map<String, String>>();

        for (String group : groups){
            m = new HashMap<String, String>();
            m.put("groupName",group);
            groupData.add(m);
        }

        String groupForm[] = new String[] {"groupName"};
        int groupTo[] = new int[] {android.R.id.text1};

        childData = new ArrayList<ArrayList<Map<String, String>>>();

        pushToCollection(firstInfo);
        pushToCollection(secondInfo);
        pushToCollection(thirdInfo);

        String childFrom[] = new String[] {""};
        int childTo[]      = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupForm,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.expandableListView);
        elvMain.setAdapter(adapter);
    }

    private void pushToCollection(String[] firstInfo) {
        childDataItem = new ArrayList<Map<String, String>>();
        for (String info : firstInfo){
            m = new HashMap<String, String>();
            m.put("",info);
            childDataItem.add(m);
            //writeFile(info);
        }
        childData.add(childDataItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    public void onClickMenu(MenuItem menu) {
        switch (menu.getItemId()){
            case R.id.information_settings:
               //Intent intent1 = new Intent(this, Settings.class);
               //startActivity(intent1);
                break;
            case R.id.results_settings:
                Intent intent2 = new Intent(this, Result.class);
                startActivity(intent2);
                break;
        }
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

    void writeFile(String data) {
        if (data.length() != 0){
            try {
                // отрываем поток для записи
                File f = new File(FILENAME);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_APPEND)));
                // пишем данные
                bw.write(data);
                // закрываем поток
                bw.close();
                Log.d(LOG_TAG, "Файл записан");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String readFile() {
        String r = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));

            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                //Log.d(LOG_TAG, str);
                r+=str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
}
