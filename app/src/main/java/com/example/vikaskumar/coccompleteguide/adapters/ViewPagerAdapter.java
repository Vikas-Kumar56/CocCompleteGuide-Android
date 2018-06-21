package com.example.vikaskumar.coccompleteguide.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> baseList = new ArrayList<>();
    private final List<String> baseTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return baseList.get(position);
    }

    @Override
    public int getCount() {
        return baseList.size();
    }

    public void addBase(Fragment base, String title) {
        baseList.add(base);
        baseTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return baseTitleList.get(position);
    }
}
