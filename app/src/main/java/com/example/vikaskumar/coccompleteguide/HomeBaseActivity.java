package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.Resources;

public class HomeBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private int homeBaseId;
    private LinearLayout baseDesignPicture;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base);
        initViews();
        initData();

    }

    private void initViews() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        baseDesignPicture=(LinearLayout) findViewById(R.id.baseDesignPicture);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        baseDesignPicture.setOnClickListener(this);
    }

    private void initData() {
        // get data passed from calling activity
        Bundle extras = getIntent().getExtras();
        homeBaseId = Integer.parseInt(extras.getString("DATA"));
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseActivity.class);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.baseDesignPicture: Navigator.getInstance().navigateToBeginnersGuideActivity(this, Resources.homeBaseDesign+"");baseDesignPicture.setOnClickListener(this);
        }
    }


}
