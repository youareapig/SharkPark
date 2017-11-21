package com.weiye.mycourse;

import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.weiye.adapter.ManagerAdapter;
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
    TabLayout mycourseTab;
    @BindView(R.id.courseTabPager)
    ViewPager courseTabPager;
    @BindView(R.id.wodeyuyue)
    RelativeLayout wodeyuyue;
    @BindView(R.id.myCourseShow)
    LinearLayout myCourseShow;
    @BindView(R.id.wuyuyue)
    RelativeLayout wuyuyue;
    @BindView(R.id.courseallName)
    TextView courseallName;
    private Unbinder unbinder;
    private List<String> nameList;
    private List<Fragment> fList;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;
    private String userTimes, userType, userID;
    private String all,sy,yh;
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

    @Override
    protected void onRestart() {
        super.onRestart();
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userTimes = sharedPreferences.getString("usertimes", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        //TODO 非会员用户
        if (userType.equals("3")) {
            if (userTimes.equals("0")) {
                myCourseShow.setVisibility(View.GONE);
                wodeyuyue.setVisibility(View.VISIBLE);
                wuyuyue.setVisibility(View.GONE);
            } else {
                myCourseShow.setVisibility(View.GONE);
                wodeyuyue.setVisibility(View.GONE);
                wuyuyue.setVisibility(View.VISIBLE);
            }
        }
        visit("1");
        mycourseTab.setupWithViewPager(courseTabPager);
        fragmentManager = getSupportFragmentManager();
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
        courseTabPager.setAdapter(new ManagerAdapter(fragmentManager, nameList, fList));
        courseTabPager.setCurrentItem(0);
        mycourseTab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mycourseTab, 25, 25);
            }
        });
        mycourseTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 3) {
                    courseallName.setText("活动课");
                    visit("4");
                } else {
                    courseallName.setText("常规课");
                    visit("1");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/myCourselst");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", type);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final YichangBean bean = gson.fromJson(result, YichangBean.class);
                if (bean.getCode() == 3000) {
                    all = bean.getData().getNums().getTotalnum();
                    sy = bean.getData().getNums().getEnablenum();
                    yh = bean.getData().getNums().getWithdrawed();
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
    //TODO TabLayout下划线的长度
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
