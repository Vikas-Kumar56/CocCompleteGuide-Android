package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.adapters.GridViewAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.Resources;
import com.example.vikaskumar.coccompleteguide.utility.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeBaseActivity extends AppCompatActivity implements GridViewAdapter.ItemClickListener {

    private List<String> gridTexts;
    private List<Integer> gridImages;
    private List<Integer> fitCenterPositions;

    private Toolbar toolbar;
    private TextView txtHeaderTitle;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base);
        initViews();
        initData();
        setUpViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txtHeaderTitle = findViewById(R.id.header_view_title);
        recyclerView  = findViewById(R.id.recycler_begineer_guide);
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
        TypedArray typedArray = getResources().obtainTypedArray(R.array.home_village_images);
        int length = typedArray.length();
        gridImages = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            gridImages.add(typedArray.getResourceId(i, 0));
        }

        int[] arrayPositions = getResources().getIntArray(R.array.home_village_fit_center_positions);
        fitCenterPositions = new ArrayList<>();
        for(int i : arrayPositions) {
            fitCenterPositions.add(Integer.valueOf(i));
        }

        gridTexts = new ArrayList<>(Arrays.asList(this.getResources().getStringArray(R.array.home_village_texts)));
    }

    private void setUpViews() {
        txtHeaderTitle.setText("Complete Guide");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, gridImages, gridTexts, this,fitCenterPositions);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setAdapter(gridViewAdapter);
    }

    public static Intent getCallingIntent(Context context) {

        return new Intent(context, HomeBaseActivity.class);

    }

    @Override
    public void onItemClick(View parentView) {
        int position = recyclerView.getChildLayoutPosition(parentView);
        switch (position) {
            case 0: Navigator.getInstance().navigateToBeginnersGuideActivity(this, "");break;
            case 1: Navigator.getInstance().navigateToHomeBaseDesignActivity(this, Resources.homeBaseDesign + "");break;
            case 2: Navigator.getInstance().navigateToCompleteGuideActivity(this,"Engineered Base",getResources().getString(R.string.engineered_base));break;
            case 3: Navigator.getInstance().navigateToArmyCompositionActivity(this,Resources.armyComposition+"");break;
            case 4: Navigator.getInstance().navigateToStrategyGuideActivity(this,Resources.strategyGuide+" ");break;
        }
    }
}
