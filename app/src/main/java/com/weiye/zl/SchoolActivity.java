package com.weiye.zl;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.weiye.schooltabfragment.ZhaoMu;
import com.weiye.schooltabfragment.WangQi;
import com.weiye.adapter.SchoolTabAdapter;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Field;
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
    XTabLayout schooltab;
    @BindView(R.id.tabviewpager)
    ViewPager tabviewpager;
    @BindView(R.id.main4)
    LinearLayout main4;
    private Unbinder unbinder;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        unbinder = ButterKnife.bind(this);
        titleList = new ArrayList<>();
        titleList.add("往期");
        titleList.add("进行");
        fragmentList = new ArrayList<>();
        fragmentList.add(new WangQi());
        fragmentList.add(new ZhaoMu());

        tabviewpager.setAdapter(new SchoolTabAdapter(getSupportFragmentManager(), titleList, fragmentList));
        schooltab.setupWithViewPager(tabviewpager);
        schooltab.getTabAt(0).select();
        schooltab.getTabAt(1).select();
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

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}