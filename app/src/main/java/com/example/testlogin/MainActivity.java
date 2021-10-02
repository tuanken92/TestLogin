package com.example.testlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testlogin.sources.CallAPI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user_name, pass_word;
    Button btnGetListUser;

    void InitGui()
    {
        btnGetListUser = (Button) findViewById(R.id.button_list_user);
        btnGetListUser.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitGui();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_list_user:
                /*CallAPI login = new CallAPI(this);
                login.InitParam(account);
                login.execute();*/
                break;

            default:
                break;
        }
    }
}