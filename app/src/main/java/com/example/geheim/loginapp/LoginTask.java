package com.example.geheim.loginapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;

// params, progress, result
public class LoginTask extends AsyncTask<String, String, String> {

    private static final String LOG_TAG = LoginTask.class.getSimpleName();

    private Context context;
    private ProgressDialog dialog;
    private LoginResponseListener loginResponseListener;


    public LoginTask(Context context, LoginResponseListener loginResponseListener){
        super();
        this.context=context;
        this.loginResponseListener = loginResponseListener;
    }

    @Override
    protected void onPreExecute() {
        dialog=new ProgressDialog(context);
        dialog.setTitle("Login");
        dialog.setMessage("Processing login...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(LOG_TAG, "doInBackground()");

        String response;

        if(params == null || params.length < 2){
            String msg = "missing user name or password in params";
            Log.d(LOG_TAG, msg);
            response = msg;

        }else{

            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("username", params[0]));
            postParameters.add(new BasicNameValuePair("password",params[1]));

            InputStream keyStoreData = context.getResources().openRawResource(R.raw.keystore);
            if(keyStoreData == null){
                String msg = "missing keystore data file";
                Log.d(LOG_TAG, msg);
                response = msg;

            }else{
                try{

                    response = SimpleHttpClient.executeHttpPost("https://192.168.0.90:8443/login/login.do", postParameters, keyStoreData);
//                    response = response.replaceAll("\\s+", "");

                }catch(Exception e){
                    Log.d(LOG_TAG, "login interrupted");
                    response = e.getMessage();
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {

        loginResponseListener.onLoginResponse(result);

        dialog.dismiss();

        super.onPostExecute(result);
    }
}
