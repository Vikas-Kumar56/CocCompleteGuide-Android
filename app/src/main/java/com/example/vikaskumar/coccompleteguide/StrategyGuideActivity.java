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

import com.example.vikaskumar.coccompleteguide.adapters.GridViewAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrategyGuideActivity extends AppCompatActivity implements GridViewAdapter.ItemClickListener{

    private List<String> gridTexts;
    private List<Integer> gridImages;
    private List<Integer> fitCenterPositions;
    private Toolbar toolbar;
    private RecyclerView gridItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_guide);
        initViews();
        initData();
        setUpViews();
    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, StrategyGuideActivity.class);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        gridItems = findViewById(R.id.strategy_guide);
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
        TypedArray typedArray = getResources().obtainTypedArray(R.array.strategy_guide_images);
        int length = typedArray.length();
        gridImages = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            gridImages.add(typedArray.getResourceId(i, 0));
        }
        fitCenterPositions = new ArrayList<>();
        gridTexts = new ArrayList<>(Arrays.asList(this.getResources().getStringArray(R.array.strategy_guide_text)));
    }

    private void setUpViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridItems.setLayoutManager(gridLayoutManager);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, gridImages, gridTexts, this,fitCenterPositions);
        gridItems.addItemDecoration(new SpaceItemDecoration(10));
        gridItems.setAdapter(gridViewAdapter);
    }



    @Override
    public void onItemClick(View parentView) {
        int position = gridItems.getChildLayoutPosition(parentView);
        switch (position) {
            case 0: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Attacking:Basics",getResources().getString(R.string.attacking_basics));break;
            case 1: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Beginner's Guide", getResources().getString(R.string.beginner_Guide));break;
            case 2: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Anti 3 Star Bases", getResources().getString(R.string.anti_3_star_bases));break;
            case 3: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Clan Castle Troops", getResources().getString(R.string.clan_castle_troops));break;
            case 4: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Clan Wars:Banned!", getResources().getString(R.string.clan_wars_banned)); break;
            case 5: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Clan Wars:Matching", getResources().getString(R.string.goho));break;
            case 6: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Clan Wars:Tactics", getResources().getString(R.string.golaloon));break;
            case 7: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Clan Wars:Tips", getResources().getString(R.string.golem_x5));break;
            case 8: Navigator.getInstance().navigateToCompleteGuideActivity(this, "EarthQuake VS Jump", getResources().getString(R.string.gowiva));break;
            case 9: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Farming Guide", getResources().getString(R.string.gowiwipe));break;
            case 10: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Farming: Troops", getResources().getString(R.string.lavaloonion));break;
            case 11: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Funneling", getResources().getString(R.string.mass_bowlers));break;
            case 12: Navigator.getInstance().navigateToCompleteGuideActivity(this, "InActive Bases", getResources().getString(R.string.miners));break;
            case 13: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Infero Towers: Multi VS Single", getResources().getString(R.string.miners));break;
            case 14: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Mastering The Eagle", getResources().getString(R.string.miners));break;
            case 15: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Spells Guide", getResources().getString(R.string.miners));break;
            case 16: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Village Entry Point", getResources().getString(R.string.miners));break;
            case 17: Navigator.getInstance().navigateToCompleteGuideActivity(this, "ZapQuake", getResources().getString(R.string.miners));break;

        }
    }
}
