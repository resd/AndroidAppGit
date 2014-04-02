package com.evgen3.xmlparser.app.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.evgen3.xmlparser.app.R;
import com.evgen3.xmlparser.app.service.MyRunnable;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    final String FILENAME = "file";// Файл для памяти телефона
    //final String FILENAME_TEMP = "fileTemp.xml";// Файл для памяти телефона
    //final String DIR_SD = "DCIM"; // Дериктория, содержащая файл
    //final String FILENAME_SD = "Text.txt"; // Файл для SD-карты
    Button btn;
    Button btn2;
    EditText editTxt;
    TextView txtView;

    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        editTxt = (EditText) findViewById(R.id.editText);
        txtView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        /*if (editTxt.length() > 5) {
            txtView.setText("Длина ID 5!");
            return;
        } else if (editTxt.length() < 5) {
            txtView.setText("Длина ID 5!");
            return;
        } else {*/
            Intent intent;
            switch (view.getId()) {
                case R.id.button:
                    String id = editTxt.getText().toString();
                    //id = "90001";
                    ShowResult.updateArrays();
                    if (!update(id, getApplicationContext())){
                        Toast.makeText(this, "Id is invalid.", Toast.LENGTH_SHORT).show();
                        editTxt.setText("");
                    } else{
                        ShowResult.updateXD(); //todo Переделать мб?
                        intent = new Intent(this, ShowResult.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                    break;
                case R.id.button2:
                    /*intent = new Intent(this, ShowResult.class);
                    startActivity(intent);*/
                    break;
                default:
                    Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    public void onClickMenu(MenuItem menu) {
        /*switch (menu.getItemId()){todo ?
            case R.id.information_settings:
                //Intent intent1 = new Intent(this, Settings.class);
                //startActivity(intent1);
                break;
            case R.id.results_settings:
                *//*Intent intent2 = new Intent(this, ShowResult.class);
                startActivity(intent2);*//*
                break;
        }*/
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
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    public boolean update(String id, Context context){
        //trying to connect //TODO System services not available to Activities before onCreate()
        //connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // if (networkInfo != null && networkInfo.isConnected()) {
        if (connectToUrl(id, context)){
            //Парсим
            parse(context);
        } else
            return false;
        // } else {
        //      Toast.makeText(this, getResources().getString(R.string.connecting_error), Toast.LENGTH_SHORT).show();
        // }

        return true;
    }

    void writeFile(String fileName, String data, Context context) {
        if (data.length() != 0){
            try {
                // отрываем поток для записи
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(fileName, MODE_PRIVATE)));
                // пишем данные
                bw.write(data);
                // закрываем поток
                bw.close();
                Log.d(LOG_TAG, "Файл записан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String readFile(String fileName, Context context) {
        String r = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));

            String str;
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                r+=str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public void parse(Context context) {
        String d = readFile(FILENAME, context);//todo ?
        SaxLister sax = new SaxLister();
        try {
            sax.gogo(d);
            getData(sax.getElement(), sax.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean connectToUrl(String id, Context context) {
        String t;
        MyRunnable my = new MyRunnable();
        byte[] data = my.go(id);
        if (data == null){
            Log.e(LOG_TAG, "Data == null, Connection problem?");
            return false;
        }
        if (data.length > 38){
            t = new String(data);//Arrays.toString(data);
            writeFile(FILENAME, t, context);
        } else{
            Log.e(LOG_TAG, "Id is invalid!");
            return false;
        }
        return true;
    }

    private void getData(ArrayList<String> element, ArrayList<String> value){
        for (int i = 0; i < element.size(); i++) {
            //Log.d(LOG_TAG, element.get(i)+ ": " + value.get(i));
            switch (element.get(i)) {
                case "person":
                    ShowResult.person = value.get(i);
                    break;
                case "department":
                    ShowResult.department.add(value.get(i));
                    break;
                case "qualification":
                    ShowResult.qualification.add(value.get(i));
                    break;
                case "licence":
                    ShowResult.licence.add(value.get(i));
                    break;
                case "speciality":
                    ShowResult.speciality.add(value.get(i));
                    break;
                case "budget":
                    ShowResult.budget.add(value.get(i));
                    break;
                case "place":
                    ShowResult.place.add(value.get(i));
                    break;
                case "originplace":
                    ShowResult.originplace.add(value.get(i));
                    break;
                case "rating":
                    ShowResult.rating.add(value.get(i));
                    break;
                case "privilege":
                    ShowResult.privilege.add(value.get(i));
                    break;
                case "docstate":
                    ShowResult.docstate.add(value.get(i));
                    break;
                case "title":
                    ShowResult.title.add(value.get(i));
                    break;
                case "comment":
                    ShowResult.comment.add(value.get(i));
                    break;
                default:
                    break;
            }
        }
    }

}
