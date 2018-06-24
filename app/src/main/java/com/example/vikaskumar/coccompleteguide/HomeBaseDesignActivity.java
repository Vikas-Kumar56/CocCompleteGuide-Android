package com.example.vikaskumar.coccompleteguide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
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

    private PopupMenu popupMenu;
    private FloatingActionButton townhallPopMenuShow;
    static int townhallid=10;

    private WarBase warBase;
    private WarBase farmingBase;
    private WarBase trophyBase;
    private WarBase hybridBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_base_design);
        initData();
        initViews();

    }

    private void initViews() {

        //setting up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolBarText = (TextView) findViewById(R.id.toolbarText);
        toolBarText.setText(Resources.homeBaseDesignToolBarText);

        townhallPopMenuShow = findViewById(R.id.townhallSelection);
        //setting tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        InitTabSettings();
        SetListnerOnTabToChangeTextStyle();

        townhallPopMenuShow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                townhallPopMenuShow.hide();
                showPopupOption(view);
            }
        });
    }

    private void SetListnerOnTabToChangeTextStyle() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                TextView text = (TextView) tab.getCustomView();

                text.setTypeface(null, Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView text = (TextView) tab.getCustomView();

                text.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    private void InitTabSettings() {
        //Typeface font = ResourcesCompat.getFont(this, R.font.aclonica_font_family);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                tabTextView.setText(tab.getText());
                tabTextView.setTextSize(17);
                tabTextView.setTextColor(getResources().getColor(R.color.white));
                //tabTextView.setTypeface(font);
                // First tab is the selected tab, so if i==0 then set BOLD typeface
                if (i == 0) {
                    tabTextView.setTypeface(null, Typeface.BOLD);
                }

            }

        }
    }

    private void onRefresh(){
        WarBase.townhallid=townhallid;
        warBase.onRefresh();
        trophyBase.onRefresh();
        farmingBase.onRefresh();
        hybridBase.onRefresh();

    }

    private void initData() {
        warBase = WarBase.getInstance(Resources.warBase);
        trophyBase = WarBase.getInstance(Resources.tropiesBase);
        farmingBase = WarBase.getInstance(Resources.farmingBase);
        hybridBase = WarBase.getInstance(Resources.hybridBase);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addBase(warBase, "War");
        adapter.addBase(trophyBase, "Trophy");
        adapter.addBase(farmingBase, "Farming");
        adapter.addBase(hybridBase, "Hybrid");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

    @SuppressLint("ResourceType")
    private void showPopupOption(View view) {
        MenuBuilder menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.layout.townhall_menu, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, view);
        optionsMenu.setForceShowIcon(true);
        optionsMenu.setAnchorView(townhallPopMenuShow);

        optionsMenu.setGravity(Gravity.END);
        optionsMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                townhallPopMenuShow.show();

            }
        });
// Set Item Click Listener
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                townhallPopMenuShow.show();
                switch (item.getItemId()) {
                    case R.id.townhall5:
                        townhallid=5;
                        onRefresh();
                        return true;
                    case R.id.townhall6:
                        townhallid=6;
                        return true;
                    case R.id.townhall7:
                        townhallid=7;
                        onRefresh();
                        return true;
                    case R.id.townhall8:
                        townhallid=8;
                        onRefresh();
                        return true;
                    case R.id.townhall9:
                        townhallid=9;
                        onRefresh();
                        return true;
                    case R.id.townhall10:
                        townhallid=10;
                        onRefresh();
                        return true;
                    case R.id.townhall11:
                        townhallid=11;
                        onRefresh();
                        return true;
                    case R.id.townhall12:
                        townhallid=12;
                        onRefresh();
                        return true;
                    default:
                        return false;
                }

            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }

        });

        optionsMenu.show();
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeBaseDesignActivity.class);
    }
}
