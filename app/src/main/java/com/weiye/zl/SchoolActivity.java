package com.weiye.zl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.weiye.data.HuodongBean;
import com.weiye.schoolTabFragment.SchoolActivity_1;
import com.weiye.adapter.SchoolTabAdapter;
import com.weiye.schoolTabFragment.SchoolActivity_2;
import com.weiye.schoolTabFragment.SchoolActivity_3;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SchoolActivity extends AutoLayoutActivity {
    @BindView(R.id.school_Back)
    RelativeLayout schoolBack;
    @BindView(R.id.schooltab)
    TabLayout schooltab;
    @BindView(R.id.tabviewpager)
    ViewPager tabviewpager;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        unbinder = ButterKnife.bind(this);
        schooltab.setupWithViewPager(tabviewpager);
        fragmentManager = getSupportFragmentManager();
        titleList = new ArrayList<>();
        titleList.add("即将开始");
        titleList.add("进行中");
        titleList.add("往期");
        fragmentList = new ArrayList<>();
        fragmentList.add(new SchoolActivity_1());
        fragmentList.add(new SchoolActivity_2());
        fragmentList.add(new SchoolActivity_3());
        tabviewpager.setAdapter(new SchoolTabAdapter(fragmentManager, titleList, fragmentList));
        tabviewpager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
