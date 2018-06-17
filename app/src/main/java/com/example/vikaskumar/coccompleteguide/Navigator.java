package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;

public class Navigator {

    private static Navigator instance;

    private Navigator() {
    }

    public static Navigator getInstance() {
        if (instance == null) {
            synchronized (Navigator.class) {
                if (instance == null) {
                    instance = new Navigator();
                }
            }
        }
        return instance;
    }

    public void navigateToHomeBaseActivity(Context context, String data) {
        Intent subAct = HomeBaseActivity.getCallingIntent(context);
        // This is the data where you want to send to target activity.
        subAct.putExtra("DATA", data);
        context.startActivity(subAct);
    }
}