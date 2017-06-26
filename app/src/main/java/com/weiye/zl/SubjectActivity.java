package com.weiye.zl;
/**
 * 首页的二级界面
 */

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.weiye.data.SubjectBean;
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
    @BindView(R.id.main2)
    FrameLayout main2;

    private Unbinder unbinder;
    private int height;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment fragment = new Fragment();
    private List<Fragment> list = new ArrayList<>();
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    private String indexID;
    private List<SubjectBean.DataBean.TeacherBean> mlist;
    private int mOriginButtonTop;
    private GestureDetectorCompat mDetectorCompat;
    private SharedPreferences sharedPreferences;
    private CustomProgressDialog customProgressDialog, customProgressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Intent intent = this.getIntent();
        indexID = intent.getStringExtra("indexID");
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
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

        visit();
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
                    intent1.putExtra("LXID",indexID);
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
            titleSubject.setBackgroundColor(Color.argb((int) alpha, 0, 173, 236));
        }

    }

    //TODO 标题描述数据
    private void visit() {
        main2.setVisibility(View.GONE);
        customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/subLst");
        params.addBodyParameter("sbid", indexID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                changTitle();
                main2.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                SubjectBean bean=gson.fromJson(result,SubjectBean.class);
                mlist=bean.getData().getTeacher();
                if (bean.getCode()==1000){
                    mytitleText.setText(bean.getData().getSbtitle());
                    subjectContent.setText(bean.getData().getSbdesc());
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getBjpic(), subjecttitlebackground);
                    recycleTeacher.setLayoutManager(new LinearLayoutManager(SubjectActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    recycleTeacher.setAdapter(new TeacherRecycleAdapter(mlist));
                    recycleTeacher.setHasFixedSize(true);
                }else {
                    Toast.makeText(SubjectActivity.this,"暂无更多数据",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Intent intent=new Intent(SubjectActivity.this,RestartActivity.class);
                startActivity(intent);
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




}
