package com.weiye.mycourse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.weiye.adapter.ManagerAdapter;
import com.weiye.data.ClassTimes;
import com.weiye.data.YichangBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;
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
    XTabLayout mycourseTab;
    @BindView(R.id.courseTabPager)
    ViewPager courseTabPager;
    @BindView(R.id.myCourseShow)
    LinearLayout myCourseShow;
    @BindView(R.id.courseallName)
    TextView courseallName;
    private Unbinder unbinder;
    private List<String> nameList;
    private List<Fragment> fList;
    private SharedPreferences sharedPreferences;
    private String userTimes, userType, userID, babyID;
    private String all, sy, yh;
    private YiChang yiChang;

    private YiShang yiShang;

    private WeiShang weiShang;

    private HuoDong huoDong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coruse);
        unbinder = ButterKnife.bind(this);
        init();
    }


    private void init() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userTimes = sharedPreferences.getString("usertimes", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        babyID = sharedPreferences.getString("babyId", "未知");
        visit("1");
        nameList = new ArrayList<>();
        nameList.add("未上");
        nameList.add("已上");
        nameList.add("异常");
        nameList.add("活动");
        fList = new ArrayList<>();
        weiShang = new WeiShang();
        yiShang = new YiShang();
        yiChang = new YiChang();
        huoDong = new HuoDong();
        fList.add(weiShang);
        fList.add(yiShang);
        fList.add(yiChang);
        fList.add(huoDong);

        courseTabPager.setAdapter(new ManagerAdapter(getSupportFragmentManager(), nameList, fList));
        mycourseTab.setupWithViewPager(courseTabPager);
        mycourseTab.getTabAt(0).select();
        mycourseTab.getTabAt(1).select();
        courseTabPager.setCurrentItem(0);
        mycourseTab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                Log.e("tag",".."+tab.getPosition());
                if (tab.getPosition() == 3) {
                    courseallName.setText("活动课");
                    visit("2");
                } else {
                    courseallName.setText("常规课");
                    visit("1");
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R.id.mycourseBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mycourseBack:
                finish();
                break;
        }
    }

    private void visit(String type) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/myGold");
        params.addBodyParameter("bid", babyID);
        params.addBodyParameter("gp", type);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final ClassTimes bean = gson.fromJson(result, ClassTimes.class);
                if (bean.getCode() == 3000) {
                    all = bean.getData().getTotalnum()+"";
                    sy = bean.getData().getEnablenum() + "";
                    yh = bean.getData().getWithdrawed() + "";
                    courseAll.setText(all);
                    courseSheng.setText(sy);
                    courseYong.setText(yh);
                }

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
