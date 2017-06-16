package com.weiye.zl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.weiye.schooltabfragment.SchoolActivity_1;
import com.weiye.schooltabfragment.SchoolActivity_2;
import com.weiye.schooltabfragment.SchoolActivity_3;
import com.weiye.adapter.SchoolTabAdapter;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SchoolActivity extends AutoLayoutActivity {
    @BindView(R.id.school_Back)
    RelativeLayout schoolBack;
    @BindView(R.id.schooltab)
    TabLayout schooltab;
    @BindView(R.id.tabviewpager)
    ViewPager tabviewpager;
    @BindView(R.id.main4)
    LinearLayout main4;
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
        titleList.add("幼儿园");
        titleList.add("小学");
        titleList.add("嘉年华");
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

    @OnClick(R.id.school_Back)
    public void onViewClicked() {
        finish();
    }
}
