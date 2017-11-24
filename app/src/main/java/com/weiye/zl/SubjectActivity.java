package com.weiye.zl;
/**
 * 首页的二级界面
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.XTabAdapter;
import com.weiye.data.BJBean;
import com.weiye.data.VipClassVidioBean;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.CustomViewPager;
import com.weiye.myview.ObservableScrollView;
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
import qiu.niorgai.StatusBarCompat;

public class SubjectActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {

    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.title_subject)
    RelativeLayout titleSubject;
    @BindView(R.id.mytitle)
    FrameLayout mytitle;
    @BindView(R.id.mytitleText)
    TextView mytitleText;
    @BindView(R.id.subject_content)
    TextView subjectContent;
    @BindView(R.id.subjecttitlebackground)
    ImageView subjecttitlebackground;
    @BindView(R.id.back6)
    RelativeLayout back6;
    @BindView(R.id.main2)
    FrameLayout main2;
    @BindView(R.id.xTab)
    XTabLayout xTab;
    @BindView(R.id.xViewpage)
    CustomViewPager xViewpage;

    private Unbinder unbinder;
    private int height;
    private List<Fragment> flist;
    private List<String> slist;
    private String userType, userID, gid;
    private SharedPreferences sharedPreferences;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        unbinder = ButterKnife.bind(this);
        init();
        visit();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        gid = sharedPreferences.getString("ggid", "未知");
        flist=new ArrayList<>();
        flist.add(new VideoFragment(20));
        flist.add(new PhotoFragment(20));
        slist=new ArrayList<>();
        slist.add("视频集");
        slist.add("相册集");
        LinearLayout linearLayout = (LinearLayout) xTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tabline));
        linearLayout.setDividerPadding(60);
        xViewpage.setAdapter(new XTabAdapter(getSupportFragmentManager(),slist,flist));
        xViewpage.setOffscreenPageLimit(2);
        xTab.setupWithViewPager(xViewpage);
        xTab.getTabAt(0).select();
        xTab.getTabAt(1).select();
        xViewpage.setCurrentItem(0);

    }


    //TODO 滑动改变标题栏
    private void changTitle() {
        //TODO 设置Scrollview从顶部开始
        scrollview.smoothScrollTo(0, 20);
        titleSubject.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = mytitle.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mytitle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = mytitle.getHeight();
                mytitle.getWidth();
                scrollview.setScrollViewListener(SubjectActivity.this);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            titleSubject.setBackgroundColor(Color.argb((int) alpha, 0, 173, 236));
        }

    }

    //TODO 标题描述数据
    private void visit() {
        main2.setVisibility(View.GONE);
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/gradeInfo");
        params.addBodyParameter("tp", "1");
        params.addBodyParameter("gid", gid);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                changTitle();
                main2.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                BJBean bean = gson.fromJson(result, BJBean.class);
                if (bean.getCode() == 3000) {
                    mytitleText.setText(bean.getData().getGname());
                    subjectContent.setText(bean.getData().getInform());
                    Glide.with(SubjectActivity.this)
                            .load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getData().getPic())
                            .error(R.mipmap.hui)
                            .placeholder(R.mipmap.hui)
                            .centerCrop()
                            .into(subjecttitlebackground);
                } else {
                    Toast.makeText(SubjectActivity.this, "暂无更多数据", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.back6)
    public void onViewClicked() {
        finish();
    }
}
