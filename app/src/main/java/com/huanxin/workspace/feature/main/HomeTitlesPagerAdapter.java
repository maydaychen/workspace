package com.huanxin.workspace.feature.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class HomeTitlesPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = null;
    private String [] titles;

    public HomeTitlesPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < fragments.size()) {
            fragment = fragments.get(position);
        } else {
            fragment = fragments.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0) {
            return titles[position];
        }
        return null;
    }

}
