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

public class HeroesActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TextView toolbarText;
    private TextView headerText;
    private ImageView imgHeroesKing;
    private ImageView imgHeroesQueen;
    private ImageView imgHeroesWarden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        initViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbarText);
        headerText = findViewById(R.id.header_view_title);
        imgHeroesKing = findViewById(R.id.heroes_king);
        imgHeroesQueen = findViewById(R.id.heroes_queen);
        imgHeroesWarden = findViewById(R.id.heroes_warden);

        toolbarText.setText("Beginner's guide");
        headerText.setText("Heroes");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgHeroesKing.setOnClickListener(this);
        imgHeroesQueen.setOnClickListener(this);
        imgHeroesWarden.setOnClickListener(this);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HeroesActivity.class);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.heroes_king:
                Navigator.getInstance().navigateToCompleteGuideActivity(this, "Barbarian King", getResources().getString(R.string.hero_description_king));break;
            case R.id.heroes_queen: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Barbarian Queen", getResources().getString(R.string.hero_description_king));break;
            case R.id.heroes_warden: Navigator.getInstance().navigateToCompleteGuideActivity(this, "Barbarian Warden", getResources().getString(R.string.hero_description_king));break;
        }
    }
}
