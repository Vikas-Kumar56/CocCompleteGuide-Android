package com.example.vikaskumar.coccompleteguide;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.vikaskumar.coccompleteguide.Fragments.FarmingBase;
import com.example.vikaskumar.coccompleteguide.Fragments.HybridBase;
import com.example.vikaskumar.coccompleteguide.Fragments.TropiesBase;
import com.example.vikaskumar.coccompleteguide.Fragments.WarBase;
import com.example.vikaskumar.coccompleteguide.adapters.ViewPagerAdapter;
import com.example.vikaskumar.coccompleteguide.utility.Resources;

public class HomeBaseDesignActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolBarText;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base_design);
        initViews();
        initData();
    }

    private void initViews() {

        //setting up toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolBarText=(TextView)findViewById(R.id.toolbarText);
        toolBarText.setText(Resources.homeBaseDesignToolBarText);

        //setting tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addBase(WarBase.getInstance(Resources.warBase), "War");
        adapter.addBase(WarBase.getInstance(Resources.tropiesBase), "Troopy");
        adapter.addBase(WarBase.getInstance(Resources.farmingBase), "Farming");
        adapter.addBase(WarBase.getInstance(Resources.hybridBase),"Hybrid");
        viewPager.setAdapter(adapter);
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseDesignActivity.class);
    }
}
