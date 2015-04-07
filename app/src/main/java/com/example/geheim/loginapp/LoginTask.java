package com.example.geheim.loginapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

// params, progress, result
public class LoginTask extends AsyncTask<String, String, Boolean> {

    private static final String LOG_TAG = LoginTask.class.getSimpleName();

    private Context context;
    private ProgressDialog dialog;


    public LoginTask(Context context){
        super();
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        dialog=new ProgressDialog(context);
        dialog.setTitle("Login");
        dialog.setMessage("Processing login...");
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Log.d(LOG_TAG, "doInBackground()");

        if(params == null || params.length < 2){
            Log.d(LOG_TAG, "missing user name or password in params");
            return false;
        }

        String user = params[0];
        Log.d(LOG_TAG, "user="+user);
        String password = params[1];
        Log.d(LOG_TAG, "password="+password);

        try{

            int timeout = 8000;
            Thread.sleep(timeout);
        }catch(InterruptedException e){
            Log.d(LOG_TAG, "login interrupted");
            return false;
        }

        return "password".equals(password);
    }

    @Override
    protected void onPostExecute(Boolean result) {

        Toast.makeText(context, "Login "+(result?"successful":"failed"), Toast.LENGTH_SHORT).show();

        dialog.dismiss();

        super.onPostExecute(result);
    }
}
