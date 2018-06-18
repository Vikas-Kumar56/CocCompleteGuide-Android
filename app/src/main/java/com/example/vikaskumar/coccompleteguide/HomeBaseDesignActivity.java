package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeBaseDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base_design);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseDesignActivity.class);
    }
}
