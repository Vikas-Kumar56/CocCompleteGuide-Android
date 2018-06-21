package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vikaskumar.coccompleteguide.adapters.GridViewAdapter;
import com.example.vikaskumar.coccompleteguide.utility.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeginnersGuideActivity extends AppCompatActivity {

    private List<String> gridTexts = new ArrayList<>(Arrays.asList("Army", "Builders", "Clan Castle", "Clan Games", "Clan Wars", "Clan", "Defences", "Leagues", "Townhall", "Hero", "Gems", "Laboratory", "Obstacles"));
    private List<Integer> gridImages = new ArrayList<>(Arrays.asList(R.drawable.pic_army, R.drawable.pic_builder, R.drawable.pic_clan_castle, R.drawable.pic_clan_games, R.drawable.pic_army, R.drawable.pic_builder, R.drawable.pic_defenses, R.drawable.pic_leagues, R.drawable.pic_townhall, R.drawable.pic_hero, R.drawable.pic_gems, R.drawable.pic_laboratory, R.drawable.pic_obstacles));

    private Toolbar toolbar;
    private RecyclerView gridItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginners_guide);
        initViews();
        setUpViews();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, BeginnersGuideActivity.class);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridItems = findViewById(R.id.recycler_view);
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

    private void setUpViews() {
//        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.grid_space);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridItems.setLayoutManager(gridLayoutManager);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, gridImages, gridTexts);
        gridItems.addItemDecoration(new SpaceItemDecoration(10));
        gridItems.setAdapter(gridViewAdapter);
    }
}
