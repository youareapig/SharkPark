package com.weiye.zl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SubjectActivity extends AutoLayoutActivity {


    @BindView(R.id.videoText)
    TextView videoText;
    @BindView(R.id.photoText)
    TextView photoText;
    @BindView(R.id.tablayout)
    LinearLayout tablayout;
    @BindView(R.id.subfragment)
    LinearLayout subfragment;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<Fragment> list;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 取消状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_subject);
        unbinder = ButterKnife.bind(this);
        list = new ArrayList<>();
        fragment = new VideoFragment();
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.subfragment, fragment).commit();
        list.add(new VideoFragment());
        list.add(new PhotoFragment());
        //TODO 设置Scrollview从顶部开始
        scrollview.smoothScrollTo(0, 20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.videoText, R.id.photoText})
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
        }
    }
}
