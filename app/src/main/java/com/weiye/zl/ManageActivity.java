package com.weiye.zl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RelativeLayout;

import com.weiye.adapter.ManagerAdapter;
import com.weiye.manager.Apply;
import com.weiye.manager.Audition;
import com.weiye.myview.NoScrollViewPager;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ManageActivity extends AutoLayoutActivity {
    @BindView(R.id.manageBack)
    RelativeLayout manageBack;
    @BindView(R.id.manageTab)
    TabLayout manageTab;
    @BindView(R.id.manageTabView)
    RelativeLayout manageTabView;
    @BindView(R.id.managerTabPager)
    NoScrollViewPager managerTabPager;
    private Unbinder unbinder;
    private List<String> nameList;
    private List<Fragment> fList;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        manageTab.setupWithViewPager(managerTabPager);
        fragmentManager = getSupportFragmentManager();
        nameList = new ArrayList<>();
        nameList.add("试听");
        //nameList.add("报名");
        fList = new ArrayList<>();
        fList.add(new Audition());
        //fList.add(new Apply());
        managerTabPager.setAdapter(new ManagerAdapter(fragmentManager, nameList, fList));
        managerTabPager.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.manageBack)
    public void onViewClicked() {
        finish();
    }
}
