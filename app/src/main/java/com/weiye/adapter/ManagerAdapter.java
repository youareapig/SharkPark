package com.weiye.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class ManagerAdapter extends FragmentPagerAdapter {
    private List<String> nameList;
    private List<Fragment> fragmentList;

    public ManagerAdapter(FragmentManager fm, List<String> nameList, List<Fragment> fragmentList) {
        super(fm);
        this.nameList = nameList;
        this.fragmentList = fragmentList;
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
        return nameList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
