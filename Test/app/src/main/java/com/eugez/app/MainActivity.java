package com.eugez.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    final String FILENAME = "file.xml";// Файл для памяти телефона
    Button btn;
    Button btn2;
    EditText editTxt;
    TextView txtView;

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
                    //call second activity
                    intent = new Intent(this, Results.class);
                    intent.putExtra("id", editTxt.getText().toString());
                    startActivity(intent);
                    break;
                case R.id.button2:
                    /*intent = new Intent(this, Results.class);
                    startActivity(intent);*/
                    break;
                default:
                    Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT);
                    break;
            }
        }
    //}

    public void onClickMenu(MenuItem menu) {
        switch (menu.getItemId()){
            case R.id.information_settings:
                //Intent intent1 = new Intent(this, Settings.class);
                //startActivity(intent1);
                break;
            case R.id.results_settings:
                /*Intent intent2 = new Intent(this, Results.class);
                startActivity(intent2);*/
                break;
        }
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
