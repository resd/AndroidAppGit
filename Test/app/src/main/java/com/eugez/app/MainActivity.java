package com.eugez.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.eugez.app.R.id.button;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn;
    EditText editTxt;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        editTxt = (EditText) findViewById(R.id.editText);
        txtView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (editTxt.length() != 12) {
            txtView.setText("Длина ID вряд ли может быть меньше 12, стоит проверить ввод!");
            return;
        } else {
            switch (view.getId()) {
                case R.id.button:
                    //call second activity
                    Intent intent = new Intent(this, results.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
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

}
