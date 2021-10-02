package com.example.testlogin.sources;

import java.util.List;

public class WebLogin {
    public String token;
    public String message;
    public String status;
    public List<String> params;

    public void PrintInfor()
    {
        MyLib.Log("---WebLogin Information---", 0);
        MyLib.Log(String.format("Token = %s", token), 0);
        MyLib.Log(String.format("Message = %s", message), 0);
        MyLib.Log(String.format("Status = %s", status), 0);
        if(params != null)
            MyLib.Log(String.format("Params = %d", params.toArray().length), 0);
    }
}
