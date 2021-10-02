package com.example.testlogin.sources;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.testlogin.MainActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CallAPI extends AsyncTask<Void, String, String> {
    // Variables
    Activity contextParent;
    URL url = null;
    HttpURLConnection http = null;
    String response = null;
    String request = null;

    Gson gson = null;
    int api_type = API_Type.None;

    //Instance
    Account account = null;

    //function
    public CallAPI(Activity contextParent)
    {
        this.contextParent = contextParent;
        this.response = "";
        this.request = "";
        this.gson = new Gson();
        try {
            url = new URL(MyLib.end_point);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InitParam(Account my_account)
    {
        this.api_type = API_Type.Login;
        this.account = my_account;
        this.request = gson.toJson(this.account);
        MyLib.Log(this.request, 0);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        response = "";
        MyLib.Log("start call API", 0);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try
        {
            switch (this.api_type)
            {
                case API_Type.Login:

                    String data = this.request;
                    byte[] out = data.getBytes(StandardCharsets.UTF_8);

                    OutputStream stream = http.getOutputStream();
                    stream.write(out);

                    MyLib.Log(http.getResponseCode() + " " + http.getResponseMessage(), 0);

                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                    br.close();


                    MyLib.Log(response, 0);

                    http.disconnect();
                    break;

                default:
                    break;
            }

        }
        catch (Exception e)
        {
            publishProgress(e.getMessage());
            return e.getMessage();
        }

        return response.isEmpty()?MyLib.getRandomString(10):response;
    }


    protected void onProgressUpdate(String... progress) {
        MyLib.Log(progress[0],-1);
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        MyLib.Log(String.format("token = %s", string),-1);
        MyLib.Toast(this.contextParent.getApplicationContext(), string);

        switch (this.api_type)
        {
            case API_Type.Login:
                WebLogin weblogin = gson.fromJson(response, WebLogin.class);
                weblogin.PrintInfor();
                if (weblogin.status.equalsIgnoreCase(MyParam.API_OK))
                {
                    MyParam.token = weblogin.token;
                    MyLib.Toast(this.contextParent.getApplicationContext(), MyParam.token);
                    contextParent.startActivity(new Intent(contextParent, MainActivity.class));
                }
                else
                {
                    MyLib.Toast(this.contextParent.getApplicationContext(), "Log in fail -> try again!");
                }
                break;
        }

    }
}
