package com.example.testlogin.sources;

public class Account {
    public String User_Name;
    public String User_Password;
    public Account()
    {
        this.User_Name = "thinhbodoi";
        this.User_Password = "admin";
    }

    public Account(String user, String pass)
    {
        this.User_Name = user;
        this.User_Password = pass;
    }
    public void PrintInfor()
    {
        MyLib.Log("---Login Account Information---", 0);
        MyLib.Log(String.format("User_Name = %s, User_Password = %s", User_Name, User_Password), 0);
    }
}

/*public class WebAPILogin
{
    public String Token;
    public String Message;
    public String Status;
    //public List<String> Params;



    public WebAPILogin()
    {
        Status = "";
        Token = "";
        Message = "";
    }

}*/
