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

public class DefensesActivity extends AppCompatActivity implements GridImagesViewAdapter.GridImageClickListener {

    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    private TextView txtHeaderTitle;
    private SuperRecyclerView gridImageRecycler;

    private String toolbarTitle;
    private String headerTitle;
    private int activityTypeId;

    private List<Integer> gridImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_view);
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

        TypedArray typedArray = getResources().obtainTypedArray(R.array.defenses_images);
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
        return new Intent(context, DefensesActivity.class);
    }

    @Override
    public void onImageClick(View parentView) {
        int position = gridImageRecycler.getRecyclerView().getChildLayoutPosition(parentView);
        switch (position) {
            case 0: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Air Bomb", getResources().getString(R.string.gems_description));break;
            case 1: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Air Defenses", getResources().getString(R.string.laboratory_description));break;
            case 2: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Air Sweeper", getResources().getString(R.string.obstacles_description));break;
            case 3: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Archer Tower", getResources().getString(R.string.gems_description));break;
            case 4: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Bomb", getResources().getString(R.string.laboratory_description));break;
            case 5: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Bomb Tower", getResources().getString(R.string.obstacles_description));break;
            case 6: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Cannon", getResources().getString(R.string.gems_description));break;
            case 7: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Eagle Artillery", getResources().getString(R.string.laboratory_description));break;
            case 8: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Giant Bomb", getResources().getString(R.string.obstacles_description));break;
            case 9: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Hidden Tesla", getResources().getString(R.string.gems_description));break;
            case 10: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Inferno Tower", getResources().getString(R.string.laboratory_description));break;
            case 11: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Mortar", getResources().getString(R.string.obstacles_description));break;
            case 12: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Pumkin Bomb", getResources().getString(R.string.gems_description));break;
            case 13: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Seeking Air Mine", getResources().getString(R.string.laboratory_description));break;
            case 14: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Skeleton Trap", getResources().getString(R.string.obstacles_description));break;
            case 15: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Spring Trap", getResources().getString(R.string.gems_description));break;
            case 16: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Walls", getResources().getString(R.string.laboratory_description));break;
            case 17: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Wizard tower", getResources().getString(R.string.obstacles_description));break;
            case 18: Navigator.getInstance().navigateToCompleteGuideActivity(this, "X-Bow", getResources().getString(R.string.obstacles_description));break;
        }
    }
}
