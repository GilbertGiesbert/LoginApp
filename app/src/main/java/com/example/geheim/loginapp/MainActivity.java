package com.example.geheim.loginapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, LoginResponseListener{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

        tv_info = (TextView) findViewById(R.id.tv_info);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "onClick()");

        if(v.getId() == R.id.bt_login){

            tv_info.setText("");

            String user = ((EditText)findViewById(R.id.et_user)).getText().toString();
            String password = ((EditText)findViewById(R.id.et_password)).getText().toString();

            LoginTask loginTask = new LoginTask(this, this);
            loginTask.execute(user, password);

        }
    }

    @Override
    public void onLoginResponse(String loginResponseMessage) {
        tv_info.setText(loginResponseMessage);
    }
}
