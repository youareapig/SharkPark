package com.weiye.mycourse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.adapter.ManagerAdapter;
import com.weiye.manager.Apply;
import com.weiye.manager.Audition;
import com.weiye.myview.NoScrollViewPager;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyCoruseActivity extends AutoLayoutActivity {
    @BindView(R.id.mycourseBack)
    RelativeLayout mycourseBack;
    @BindView(R.id.courseAll)
    TextView courseAll;
    @BindView(R.id.courseSheng)
    TextView courseSheng;
    @BindView(R.id.courseYong)
    TextView courseYong;
    @BindView(R.id.mycourseTab)
    TabLayout mycourseTab;
    @BindView(R.id.courseTabPager)
    NoScrollViewPager courseTabPager;
    private Unbinder unbinder;
    private List<String> nameList;
    private List<Fragment> fList;
    private FragmentManager fragmentManager;
    private String indexID;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coruse);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        mycourseTab.setupWithViewPager(courseTabPager);
        fragmentManager = getSupportFragmentManager();
        nameList = new ArrayList<>();
        nameList.add("未上");
        nameList.add("已上");
        nameList.add("异常");
        fList = new ArrayList<>();
        fList.add(new WeiShang());
        fList.add(new YiShang());
        fList.add(new YiChang());
        courseTabPager.setAdapter(new ManagerAdapter(fragmentManager, nameList, fList));
        courseTabPager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.mycourseBack)
    public void onViewClicked() {
        finish();
    }
}
