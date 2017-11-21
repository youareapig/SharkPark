package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.weiye.adapter.FourSchoolGalleryAdapter;
import com.weiye.adapter.XTabAdapter;
import com.weiye.data.Park_1Bean;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.CustomViewPager;
import com.weiye.myview.MyScrollView;
import com.weiye.utils.SingleModleUrl;
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

public class FourSchoolActivity extends AutoLayoutActivity {
    @BindView(R.id.fourschoolBack)
    RelativeLayout fourschoolBack;
    @BindView(R.id.fourschoolGallery)
    Gallery fourschoolGallery;
    @BindView(R.id.schoolScrollview)
    MyScrollView schoolScrollview;
    @BindView(R.id.main8)
    LinearLayout main8;
    @BindView(R.id.backtop)
    RelativeLayout backtop;
    @BindView(R.id.xTab)
    XTabLayout xTab;
    @BindView(R.id.xViewpage)
    CustomViewPager xViewpage;
    private Unbinder unbinder;
    private List<Park_1Bean.DataBean> mList;
    private List<Fragment> list;
    private List<String> stringList;
    private int myEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_school);
        unbinder = ButterKnife.bind(this);
        //schoolScrollview.smoothScrollTo(0, 20);
        Intent intent = getIntent();
        myEvent = intent.getIntExtra("myevent", 20);
        sdxyVisit();
        schoolFragment();
    }


    private void schoolFragment() {
        stringList=new ArrayList<>();
        stringList.add("视频集");
        stringList.add("相册集");
        list = new ArrayList<>();
        list.add(new VideoFragment(myEvent));
        list.add(new PhotoFragment(myEvent));
        LinearLayout linearLayout = (LinearLayout) xTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tabline));
        linearLayout.setDividerPadding(60);

        xViewpage.setAdapter(new XTabAdapter(getSupportFragmentManager(),stringList,list));
        xViewpage.setOffscreenPageLimit(2);
        xTab.setupWithViewPager(xViewpage);
        xTab.getTabAt(0).select();
        xTab.getTabAt(1).select();
        xViewpage.setCurrentItem(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({ R.id.fourschoolBack, R.id.backtop})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.fourschoolBack:
                finish();
                break;
            case R.id.backtop:
                schoolScrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        schoolScrollview.fullScroll(View.FOCUS_UP);
                    }
                });
                break;
        }
    }

    private void sdxyVisit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        schoolScrollview.setVisibility(View.GONE);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/parkAll");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                schoolScrollview.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                Park_1Bean bean = gson.fromJson(result, Park_1Bean.class);
                mList = bean.getData();
                if (bean.getCode() == 1000) {
                    fourschoolGallery.setAdapter(new FourSchoolGalleryAdapter(mList, FourSchoolActivity.this));
                    fourschoolGallery.setSpacing(60);
                    fourschoolGallery.setSelection(40);
                    fourschoolGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Park_1Bean.DataBean rBean = (Park_1Bean.DataBean) adapterView.getItemAtPosition(i % mList.size());
                            Intent intent = new Intent(FourSchoolActivity.this, TeacherStyleActivity.class);
                            intent.putExtra("teacherID", rBean.getId()+"");
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(FourSchoolActivity.this, "暂无更多数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(FourSchoolActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
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
