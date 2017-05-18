package com.weiye.zl;
/**
 * 首页的二级界面
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.TeacherRecycleAdapter;
import com.weiye.data.IndexBean;
import com.weiye.data.TeacherBean;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.weiye.myview.CustomProgressDialog;
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

public class SubjectActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {


    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.title_subject)
    RelativeLayout titleSubject;
    @BindView(R.id.mytitle)
    FrameLayout mytitle;
    @BindView(R.id.mytitleText)
    TextView mytitleText;
    @BindView(R.id.recycleTeacher)
    RecyclerView recycleTeacher;
    @BindView(R.id.btnOrder)
    Button btnOrder;
    @BindView(R.id.videoText)
    TextView videoText;
    @BindView(R.id.photoText)
    TextView photoText;
    @BindView(R.id.tablayout)
    LinearLayout tablayout;
    @BindView(R.id.subfragment)
    LinearLayout subfragment;
    @BindView(R.id.subject_content)
    TextView subjectContent;
    @BindView(R.id.subjecttitlebackground)
    ImageView subjecttitlebackground;
    @BindView(R.id.back6)
    RelativeLayout back6;

    private Unbinder unbinder;
    private int height;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment fragment = new Fragment();
    private List<Fragment> list = new ArrayList<>();
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    private String indexID;
    private List<TeacherBean.RowsBean> mlist;
    private int mOriginButtonTop;
    private GestureDetectorCompat mDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Intent intent = this.getIntent();
        indexID = intent.getStringExtra("indexID");
        unbinder = ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            list.removeAll(list);
            list.add(fragmentManager.findFragmentByTag(0 + ""));
            list.add(fragmentManager.findFragmentByTag(1 + ""));
            restoreFragment();
        } else {
            list.add(new VideoFragment(indexID));
            list.add(new PhotoFragment(indexID));
            showFragment();
        }

        changTitle();
        visit();
        visitTeacher();
        myScroll();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!list.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.subfragment, list.get(currentIndex), "" + currentIndex);
        } else {
            transaction
                    .hide(fragment)
                    .show(list.get(currentIndex));
        }
        fragment = list.get(currentIndex);
        transaction.commit();
    }

    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            if (i == currentIndex) {
                mBeginTreansaction.show(list.get(i));
            } else {
                mBeginTreansaction.hide(list.get(i));
            }
        }
        mBeginTreansaction.commit();
        fragment = list.get(currentIndex);

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

    private void myScroll() {
        btnOrder.post(new Runnable() {
            @Override
            public void run() {
                mOriginButtonTop = btnOrder.getTop();
            }
        });
        mDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetectorCompat.onTouchEvent(event);
                return false;
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.btnOrder, R.id.videoText, R.id.photoText, R.id.back6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.videoText:
                videoText.setTextColor(this.getResources().getColor(R.color.black));
                photoText.setTextColor(this.getResources().getColor(R.color.gray));
                currentIndex = 0;
                showFragment();
                break;
            case R.id.photoText:
                videoText.setTextColor(this.getResources().getColor(R.color.gray));
                photoText.setTextColor(this.getResources().getColor(R.color.black));
                currentIndex = 1;
                showFragment();
                break;
            case R.id.btnOrder:
                Intent intent1 = new Intent(SubjectActivity.this, CurriculumActivity.class);
                startActivity(intent1);
                break;
            case R.id.back6:
                finish();
                break;
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            titleSubject.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }

    }

    //TODO 标题描述数据
    private void visit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXXX");
        params.addBodyParameter("ID", indexID);
        params.addBodyParameter("start", "0");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IndexBean bean = gson.fromJson(result, IndexBean.class);
                mytitleText.setText(bean.getRows().get(0).getLXMC());
                subjectContent.setText(bean.getRows().get(0).getLXMS());
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getRows().get(0).getBJTXLJ(), subjecttitlebackground);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubjectActivity.this, "描述标题失败", Toast.LENGTH_SHORT).show();
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

    //TODO 老师介绍数据
    private void visitTeacher() {
        final CustomProgressDialog customProgressDialog1 = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame);
        customProgressDialog1.setCanceledOnTouchOutside(false);
        customProgressDialog1.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXJSXX");
        params.addBodyParameter("LXID", indexID);
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX", "3");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TeacherBean teacherBean = gson.fromJson(result, TeacherBean.class);
                mlist = teacherBean.getRows();
                recycleTeacher.setLayoutManager(new LinearLayoutManager(SubjectActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recycleTeacher.setAdapter(new TeacherRecycleAdapter(mlist));
                recycleTeacher.setHasFixedSize(true);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SubjectActivity.this, "获取老师信息失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog1.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        boolean isScrollDown;
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (Math.abs(distanceY) > Math.abs(distanceX)) {//判断是否竖直滑动
                int buttonTop = btnOrder.getTop();
                int buttonBottom = btnOrder.getBottom();
                try {
                    isScrollDown =e1.getRawY() < e2.getRawY() ? true : false;
                }catch (Exception e){
                    e.printStackTrace();
                    isScrollDown=true;
                }

                if (!ifNeedScroll(isScrollDown)) return false;
                if (isScrollDown) {
                    btnOrder.setTop(buttonTop - (int) Math.abs(distanceY));
                    btnOrder.setBottom(buttonBottom - (int) Math.abs(distanceY));
                } else if (!isScrollDown) {
                    btnOrder.setTop(buttonTop + (int) Math.abs(distanceY));
                    btnOrder.setBottom(buttonBottom + (int) Math.abs(distanceY));
                }
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private boolean ifNeedScroll(boolean isScrollDown) {
            int nowButtonTop = btnOrder.getTop();
            if (isScrollDown && nowButtonTop <= mOriginButtonTop) return false;

            if (!isScrollDown) {
                return isInScreen(btnOrder);
            }
            return true;
        }

        private boolean isInScreen(View view) {
            int width, height;
            Point p = new Point();
            getWindowManager().getDefaultDisplay().getSize(p);
            width = p.x;
            height = p.y;

            Rect rect = new Rect(0, 0, width, height);

            if (!view.getLocalVisibleRect(rect)) return false;

            return true;
        }


    }

}
