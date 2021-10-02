package com.example.testlogin.sources;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


class LogType
{
    public static final int DEBUG = 0;
    public static final int INFOR = 1;
    public static final int ERROR = -1;
}

public class MyLib {
    public static String TAG = "atk";
    public static String end_point = "https://rest-api.tanhungha.com.vn/api/users/login/";


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }



    public static void Toast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void Log(String data, int log_type)
    {
        switch (log_type)
        {

            case LogType.INFOR: Log.i(TAG, data); break;
            case LogType.ERROR: Log.e(TAG, data); break;
            case LogType.DEBUG:
            default: Log.d(TAG, data); break;

        }

    }



    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    public static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}
