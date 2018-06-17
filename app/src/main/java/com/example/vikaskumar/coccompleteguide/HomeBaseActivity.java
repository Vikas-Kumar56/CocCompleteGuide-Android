package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeBaseActivity extends AppCompatActivity {

    private int homeBaseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base);
        // show back parent activity button enable
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        // get data passed from calling activity
        Bundle extras = getIntent().getExtras();
        homeBaseId = Integer.parseInt(extras.getString("DATA"));
    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseActivity.class);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
