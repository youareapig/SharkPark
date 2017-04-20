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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.adapter.TeacherRecycleAdapter;
import com.weiye.data.TestBean;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SpacesItemDecoration;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class SubjectActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {


    @BindView(R.id.videoText)
    TextView videoText;
    @BindView(R.id.photoText)
    TextView photoText;
    @BindView(R.id.tablayout)
    LinearLayout tablayout;
    @BindView(R.id.subfragment)
    LinearLayout subfragment;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.title_subject)
    RelativeLayout titleSubject;
    @BindView(R.id.mytitle)
    RelativeLayout mytitle;
    @BindView(R.id.mytitleText)
    TextView mytitleText;
    @BindView(R.id.recycleTeacher)
    RecyclerView recycleTeacher;
    @BindView(R.id.btnOrder)
    Button btnOrder;
    @BindView(R.id.mytitleAll)
    RelativeLayout mytitleAll;

    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<Fragment> list;
    private FragmentTransaction fragmentTransaction;
    private int height;
    private List<TestBean> testBeenList;
    private TestBean testBean1, testBean2, testBean3, testBean4;
    private GestureDetectorCompat mDetectorCompat;
    private int mOriginButtonTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 取消状态栏
        setContentView(R.layout.activity_subject);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        //orerBtn();
        changTitle();
        list = new ArrayList<>();
        fragment = new VideoFragment();
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.subfragment, fragment).commit();
        list.add(new VideoFragment());
        list.add(new PhotoFragment());
        teacher();
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

    //TODO 向上滑动隐藏按钮
    private void orerBtn() {
        btnOrder.post(new Runnable() {//post一个线程去获取button的原始top值
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

    //TODO 老师介绍
    private void teacher() {
        testBeenList = new ArrayList<>();
        testBean1 = new TestBean(R.mipmap.aaaa, "我是一个好老师");
        testBean2 = new TestBean(R.mipmap.aaaa, "我是一个好老师");
        testBean3 = new TestBean(R.mipmap.aaaa, "我是一个好老师");
        testBean4 = new TestBean(R.mipmap.aaaa, "我是一个好老师");
        testBeenList.add(testBean1);
        testBeenList.add(testBean2);
        testBeenList.add(testBean3);
        testBeenList.add(testBean4);
        recycleTeacher.addItemDecoration(new SpacesItemDecoration(10));
        recycleTeacher.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleTeacher.setAdapter(new TeacherRecycleAdapter(testBeenList));
        recycleTeacher.setHasFixedSize(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.videoText, R.id.photoText, R.id.btnOrder,R.id.mytitleAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.videoText:
                videoText.setTextColor(this.getResources().getColor(R.color.black));
                photoText.setTextColor(this.getResources().getColor(R.color.gray));
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.subfragment, list.get(0));
                fragmentTransaction.commit();
                break;
            case R.id.photoText:
                videoText.setTextColor(this.getResources().getColor(R.color.gray));
                photoText.setTextColor(this.getResources().getColor(R.color.black));
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.subfragment, list.get(1));
                fragmentTransaction.commit();
                break;
            case R.id.btnOrder:
                break;
            case R.id.mytitleAll:
                Intent intent=new Intent(SubjectActivity.this,FourSchoolActivity.class);
                startActivity(intent);
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

    //TODO 滑动隐藏按钮
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (Math.abs(distanceY) > Math.abs(distanceX)) {//判断是否竖直滑动
                int buttonTop = btnOrder.getTop();
                int buttonBottom = btnOrder.getBottom();

                //是否向下滑动
                boolean isScrollDown = e1.getRawY() < e2.getRawY() ? true : false;

                //根据滑动方向和mButton当前的位置判断是否需要移动Button的位置
                if (!ifNeedScroll(isScrollDown)) return false;

                if (isScrollDown) {
                    //下滑上移Button
                    btnOrder.setTop(buttonTop - (int) Math.abs(distanceY));
                    btnOrder.setBottom(buttonBottom - (int) Math.abs(distanceY));
                } else if (!isScrollDown) {
                    //上滑下移Button
                    btnOrder.setTop(buttonTop + (int) Math.abs(distanceY));
                    btnOrder.setBottom(buttonBottom + (int) Math.abs(distanceY));
                }
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        //写一个方法，根据滑动方向和mButton当前的位置，判断按钮是否应该继续滑动
        private boolean ifNeedScroll(boolean isScrollDown) {
            int nowButtonTop = btnOrder.getTop();

//button不能超出原来的上边界
            if (isScrollDown && nowButtonTop <= mOriginButtonTop) return false;

//判断按钮是否在屏幕范围内，如果不在，则不需要再移动位置
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
