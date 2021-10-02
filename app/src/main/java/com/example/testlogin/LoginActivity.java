package com.example.testlogin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.testlogin.sources.CallAPI;
import com.example.testlogin.sources.MyLib;
import com.example.testlogin.sources.MyParam;
import com.example.testlogin.sources.Account;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    EditText user_name, pass_word;
    Button btnLogin;

    Account account = null;
    SharedPreferences mPrefs = null;

    void InitVariables()
    {
        account = new Account();
        mPrefs = getPreferences(MODE_PRIVATE);

        LoadParam();
    }

    void InitGui()
    {
        user_name = (EditText) findViewById(R.id.text_username);
        pass_word = (EditText) findViewById(R.id.text_password);
        btnLogin = (Button) findViewById(R.id.button_sign_in);
        btnLogin.setOnClickListener(this);


        if(account != null)
        {
            user_name.setText(account.User_Name + "");
            pass_word.setText(account.User_Password + "");
            login();
        }
    }

    void get_account()
    {
        account.User_Name = user_name.getText().toString();
        account.User_Password = pass_word.getText().toString();
        account.PrintInfor();
    }

    void login()
    {
        get_account();

        CallAPI login = new CallAPI(this);
        login.InitParam(account);
        login.execute();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI(findViewById(R.id.login));
        InitVariables();
        InitGui();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                login();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SaveParam();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveParam();
    }

    void SaveParam()
    {
        //get account
        get_account();

        //save
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(account);
        prefsEditor.putString(MyParam.KEY_MY_ACCOUNT, json);
        prefsEditor.commit();
        MyLib.Log("save data json = " + json, 0);
    }

    void LoadParam()
    {
        Gson gson = new Gson();
        String json = mPrefs.getString(MyParam.KEY_MY_ACCOUNT, "");
        if(json.isEmpty())
        {
            account = new Account();
        }
        else
            account = gson.fromJson(json, Account.class);
        MyLib.Log("load data json = " + json, 0);
        account.PrintInfor();
    }


    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    MyLib.hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}