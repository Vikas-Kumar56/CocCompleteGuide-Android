package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.Resources;

public class ArmyActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    private TextView txtHeaderTitle;

    private ImageView imgArmyCamp;
    private ImageView imgArmyBarracks;
    private ImageView imgArmyDarkBarracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army);
        initViews();
        setUpViews();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ArmyActivity.class);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txtToolbarTitle = findViewById(R.id.toolbarText);
        txtHeaderTitle = findViewById(R.id.header_view_title);
        imgArmyCamp = findViewById(R.id.army_camp);
        imgArmyBarracks = findViewById(R.id.army_barracks);
        imgArmyDarkBarracks = findViewById(R.id.army_dark_barracks);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgArmyCamp.setOnClickListener(this);
        imgArmyBarracks.setOnClickListener(this);
        imgArmyDarkBarracks.setOnClickListener(this);
    }

    private void setUpViews() {
        txtToolbarTitle.setText("Beginner's guide");
        txtHeaderTitle.setText("Army");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.army_camp: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Army Camps", getResources().getString(R.string.army_description_camp));break;
            case R.id.army_barracks: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Army Barracks", getResources().getString(R.string.army_description_barracks));break;
            case R.id.army_dark_barracks: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Army Dark Barracks", getResources().getString(R.string.army_description_dark_barracks));break;
        }
    }
}
