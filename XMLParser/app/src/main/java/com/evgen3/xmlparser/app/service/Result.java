/*
package com.evgen3.xmlparser.app.service;

import android.content.Intent;
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

public class Result {


    final String LOG_TAG = "myLogs";
    final String FILENAME = "file";// Файл для памяти телефона
    final String FILENAME_TEMP = "fileTemp.xml";// Файл для памяти телефона
    final String DIR_SD = "DCIM"; // Дериктория, содержащая файл
    final String FILENAME_SD = "Text.txt"; // Файл для SD-карты
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
    ArrayList<String[]> d;

    //список атрибутов группы или элементов
    Map<String,String> m;

    public void onCreate(String d) {

        String t = "";
        MyRunnable my = new MyRunnable();
        byte[] data = my.go(id);
        if (data.length!=0){
            t = new String(data);//Arrays.toString(data);
         //   writeFile(FILENAME, t);
          //  writeFile(FILENAME_TEMP, t);
        //Log.d(LOG_TAG, readFile());
        } else{
            Log.d(LOG_TAG, "Connection error! Check access to internet!");
        }

        //String d = readFile(FILENAME);
        Log.d(LOG_TAG, "Начало");
        Log.d(LOG_TAG, d);
        Log.d(LOG_TAG, "Конец");
        SaxLister sax = new SaxLister();
        try {
            sax.gogo(d);
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
}
*/
