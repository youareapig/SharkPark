package com.weiye.zl;
/**
 * 首页的二级界面
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.SpinnerAdpter;
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
    @BindView(R.id.jiantou)
    ImageView jiantou;

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
    private ListView popListView;
    private PopupWindow popupWindow;
    private List<String> list_1;

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
        list_1 = new ArrayList<>();
        list_1.add("生命科学");
        list_1.add("人文科学");
        list_1.add("天文科学");
        list_1.add("地理科学");

        //visit();
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

    @OnClick({R.id.videoText, R.id.photoText, R.id.back6, R.id.mytitleText})
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
            case R.id.back6:
                finish();
                break;
            case R.id.mytitleText:
                mypop(view);
                popupWindow.showAsDropDown(view, 0, 45);
                jiantou.setImageResource(R.mipmap.up);
                break;
        }
    }

    private void mypop(View view) {
        View customView = getLayoutInflater().inflate(R.layout.mypop,
                null, false);
        popListView = (ListView) customView.findViewById(R.id.popListView);
        // 创建PopupWindow实例,宽度和高度
        popupWindow = new PopupWindow(customView, 1080, 760);
        // 设置动画效果
        // popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popListView.setAdapter(new SpinnerAdpter(this, list_1));
        //TODO 选择品牌，请求数据
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("tag", "选择了" + list_1.get(position));
                jiantou.setImageResource(R.mipmap.dwon);
                popupWindow.dismiss();
                mytitleText.setText(list_1.get(position));
            }
        });
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
        customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame, R.style.dialog);
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
                SubjectBean bean = gson.fromJson(result, SubjectBean.class);
                mlist = bean.getData().getTeacher();
                if (bean.getCode() == 1000) {
                    mytitleText.setText(bean.getData().getSbtitle());
                    subjectContent.setText(bean.getData().getSbdesc());
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getBjpic(), subjecttitlebackground);
                } else {
                    Toast.makeText(SubjectActivity.this, "暂无更多数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Intent intent = new Intent(SubjectActivity.this, RestartActivity.class);
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
