package com.huanxin.workspace.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/8/22  15:43
 * desc   :
 * version: 1.0
 */
public class ContentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> tabTitleList;
    private ArrayList<Fragment> fragmentList;

    public ContentPagerAdapter(FragmentManager fm, ArrayList<String> tabTitleList, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.tabTitleList = tabTitleList;
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }
}
