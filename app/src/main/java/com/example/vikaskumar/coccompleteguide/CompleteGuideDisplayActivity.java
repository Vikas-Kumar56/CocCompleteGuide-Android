package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.utility.Resources;

public class CompleteGuideDisplayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    private TextView txtHeaderTitle;
    private TextView txtDescription;

    private String headerTitle;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_guide_display);
        initViews();
        initData();
        setUpViews();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CompleteGuideDisplayActivity.class);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txtToolbarTitle = findViewById(R.id.toolbarText);
        txtHeaderTitle = findViewById(R.id.header_view_title);
        txtDescription = findViewById(R.id.description);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        headerTitle = intent.getStringExtra(Resources.HEADER_TITLE_KEY);
        description = intent.getStringExtra(Resources.SOURCE_DESCRIPTION_KEY);
    }

    private void setUpViews() {
        txtToolbarTitle.setText("Beginner's guide");
        txtHeaderTitle.setText(headerTitle);
        txtDescription.setText(description);
    }
}
