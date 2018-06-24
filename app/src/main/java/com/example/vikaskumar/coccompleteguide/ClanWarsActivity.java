package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.adapters.GridImagesViewAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClanWarsActivity extends AppCompatActivity implements GridImagesViewAdapter.GridImageClickListener {

    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    private TextView txtHeaderTitle;
    private SuperRecyclerView gridImageRecycler;

    private String toolbarTitle;
    private String headerTitle;

    private List<Integer> gridImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan_wars);
        initViews();
        initData();
        setUpViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txtToolbarTitle = findViewById(R.id.toolbarText);
        txtHeaderTitle = findViewById(R.id.header_view_title);
        gridImageRecycler = findViewById(R.id.grid_image_recycler);

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
        toolbarTitle = intent.getStringExtra(Resources.TOOLBAR_TITLE_KEY);
        headerTitle = intent.getStringExtra(Resources.HEADER_TITLE_KEY);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.clan_wares_images);
        int length = typedArray.length();
        gridImages = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            gridImages.add(typedArray.getResourceId(i, 0));
        }
    }

    private void setUpViews() {
        txtToolbarTitle.setText(toolbarTitle);
        txtHeaderTitle.setText(headerTitle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridImageRecycler.setLayoutManager(gridLayoutManager);
        GridImagesViewAdapter gridImagesViewAdapter = new GridImagesViewAdapter(this, gridImages, this);
        gridImageRecycler.setAdapter(gridImagesViewAdapter);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ClanWarsActivity.class);
    }

    @Override
    public void onImageClick(View parentView) {
        int position = gridImageRecycler.getRecyclerView().getChildLayoutPosition(parentView);
        switch (position) {
            case 0:
                Navigator.getInstance().navigateToCompleteGuideActivity(this, "Overview", getResources().getString(R.string.clan_wars_overview_description)); break;
            case 1:Navigator.getInstance().navigateToCompleteGuideActivity(this, "Day 1", getResources().getString(R.string.clan_wars_day1_description));break;
            case 2: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Day 2", getResources().getString(R.string.clan_wars_day2_description));break;

        }
    }
}
