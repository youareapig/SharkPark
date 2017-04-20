package com.weiye.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/20.
 */
public class SchoolTabAdapter extends FragmentPagerAdapter{
    private List<String> titleList;
    private List<Fragment> fragmentList;
    public SchoolTabAdapter(FragmentManager fm,List<String> titleList,List<Fragment> fragmentList) {
        super(fm);
        this.titleList=titleList;
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
