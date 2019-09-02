package com.amitshekhar.example.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {

    Context context;

    public CommonUtils(Context context) {
        this.context = context;
    }


    public void showToastSmall(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


}
