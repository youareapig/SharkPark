package com.weiye.mycourse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weiye.adapter.ManagerAdapter;
import com.weiye.adapter.WeishangAdapter;
import com.weiye.data.WeishangBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.NoScrollViewPager;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
    @BindView(R.id.wodeyuyue)
    TextView wodeyuyue;
    @BindView(R.id.myCourseShow)
    LinearLayout myCourseShow;
    @BindView(R.id.wuyuyue)
    RelativeLayout wuyuyue;
    private Unbinder unbinder;
    private List<String> nameList;
    private List<Fragment> fList;
    private FragmentManager fragmentManager;
    private SharedPreferences sharedPreferences;
    private String userTimes, userType, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coruse);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userTimes = sharedPreferences.getString("usertimes", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        init();
        visit();
    }

    private void init() {
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

    private void visit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/myCourselst");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                WeishangBean bean = gson.fromJson(result, WeishangBean.class);
                courseAll.setText(bean.getData().getNums().getTotalnum());
                courseSheng.setText(bean.getData().getNums().getEnablenum());
                courseYong.setText(bean.getData().getNums().getWithdrawed());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "未上错误");
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
}
