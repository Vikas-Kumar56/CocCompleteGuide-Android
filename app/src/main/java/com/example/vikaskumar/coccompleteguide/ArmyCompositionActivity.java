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

import com.example.vikaskumar.coccompleteguide.adapters.BaseDesignAdapter;
import com.example.vikaskumar.coccompleteguide.adapters.GridViewAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Navigator;
import com.example.vikaskumar.coccompleteguide.utility.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmyCompositionActivity extends AppCompatActivity implements GridViewAdapter.ItemClickListener {

    private List<String> gridTexts;
    private List<Integer> gridImages;
    private List<Integer> fitCenterPositions;

    private Toolbar toolbar;
    private RecyclerView gridItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army_composition);
        initViews();
        initData();
        setUpViews();
    }
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ArmyCompositionActivity.class);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        gridItems = findViewById(R.id.army_composition);
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
        TypedArray typedArray = getResources().obtainTypedArray(R.array.army_composition_images);
        int length = typedArray.length();
        gridImages = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            gridImages.add(typedArray.getResourceId(i, 0));
        }
        fitCenterPositions = new ArrayList<>();
        gridTexts = new ArrayList<>(Arrays.asList(this.getResources().getStringArray(R.array.army_composition_text)));
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
            case 0: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Army Composition By TownHall",getResources().getString(R.string.army_composition_by_townhall));break;
            case 1: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Queen Walk", getResources().getString(R.string.queen_walk));break;
            case 2: Navigator.getInstance().navigateToCompleteGuideActivity(this, "BARCH", getResources().getString(R.string.barch));break;
            case 3: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Dragon", getResources().getString(R.string.dragon));break;
            case 4: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Giant+Healer", getResources().getString(R.string.giant_healer)); break;
            case 5: Navigator.getInstance().navigateToCompleteGuideActivity(this, "GOHO", getResources().getString(R.string.goho));break;
            case 6: Navigator.getInstance().navigateToCompleteGuideActivity(this, "GOLALoon", getResources().getString(R.string.golaloon));break;
            case 7: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Golem X5", getResources().getString(R.string.golem_x5));break;
            case 8: Navigator.getInstance().navigateToCompleteGuideActivity(this, "GoWiVA", getResources().getString(R.string.gowiva));break;
            case 9: Navigator.getInstance().navigateToCompleteGuideActivity(this, "GoWiWiPe", getResources().getString(R.string.gowiwipe));break;
            case 10: Navigator.getInstance().navigateToCompleteGuideActivity(this, "LavaLoonion", getResources().getString(R.string.lavaloonion));break;
            case 11: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Mass Bowlers", getResources().getString(R.string.mass_bowlers));break;
            case 12: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Miners", getResources().getString(R.string.miners));break;
           }
    }
}
