package com.weiye.zl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RelativeLayout;

import com.weiye.schoolTabFragment.SchoolActivityFragment;
import com.weiye.adapter.SchoolTabAdapter;
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
import qiu.niorgai.StatusBarCompat;

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
        visit("0");
        fragmentList = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(new SchoolActivityFragment());
        }

        tabviewpager.setAdapter(new SchoolTabAdapter(fragmentManager, titleList, fragmentList));
        tabviewpager.setCurrentItem(0);
        schooltab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.e("tag", "选择" + tab.getPosition());
                visit(tab.getPosition() + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("tag", "未选择" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("tag", "再选择" + tab.getTag());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void visit(String tag) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXHDDataService.ashx?op=getTAB_XXHD");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("ZT", tag);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "-------------------" + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
