package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.adapter.CurriculumGalleryAdapter;
import com.weiye.adapter.CurriculumListViewAdapter;
import com.weiye.data.TestCurrBean;
import com.weiye.myview.ObservableScrollView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CurriculumActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.back5)
    RelativeLayout back5;
    @BindView(R.id.curricuListview)
    ListView curricuListview;
    @BindView(R.id.curricuGallery)
    Gallery curricuGallery;
    @BindView(R.id.CurriculumtitleImage)
    ImageView CurriculumtitleImage;
    @BindView(R.id.curricuGalleryView)
    LinearLayout curricuGalleryView;
    @BindView(R.id.curricuScrollview)
    ObservableScrollView curricuScrollview;
    @BindView(R.id.curricuButton)
    Button curricuButton;
    @BindView(R.id.Curriculumtitlexq)
    TextView Curriculumtitlexq;
    @BindView(R.id.Curriculumtitlerq)
    TextView Curriculumtitlerq;
    private Unbinder unbinder;
    private List<TestCurrBean> mList;
    private int height;
    private TestCurrBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        unbinder = ButterKnife.bind(this);
        changDate();
        classbook();
        showDay();
    }

    private void changDate() {
        mList = new ArrayList<>();
        TestCurrBean bean1 = new TestCurrBean(R.mipmap.day1, "周一", "(18日)");
        TestCurrBean bean2 = new TestCurrBean(R.mipmap.day2, "周二", "(19日)");
        TestCurrBean bean3 = new TestCurrBean(R.mipmap.day3, "周三", "(20日)");
        TestCurrBean bean4 = new TestCurrBean(R.mipmap.day4, "周四", "(21日)");
        TestCurrBean bean5 = new TestCurrBean(R.mipmap.day5, "周五", "(22日)");
        TestCurrBean bean6 = new TestCurrBean(R.mipmap.day6, "周五", "(23日)");
        TestCurrBean bean7 = new TestCurrBean(R.mipmap.day7, "周五", "(24日)");
        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);
        mList.add(bean4);
        mList.add(bean5);
        mList.add(bean6);
        mList.add(bean7);
        final CurriculumGalleryAdapter adapter = new CurriculumGalleryAdapter(mList, this);
        curricuGallery.setAdapter(adapter);
        curricuGallery.setSpacing(60);
        curricuGallery.setSelection(100);
        curricuGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bean = (TestCurrBean) adapterView.getItemAtPosition(i % (mList.size()));
                adapter.setSelectItem(i % (mList.size()));
                adapter.notifyDataSetChanged();
                Log.e("tag", "选择" + i % mList.size() + "  " + bean.getText1() + bean.getIcon());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void classbook() {
        curricuListview.setAdapter(new CurriculumListViewAdapter(this));
    }

    private void showDay() {
        ViewTreeObserver observer = curricuGalleryView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                curricuGalleryView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = curricuGalleryView.getHeight();
                curricuGalleryView.getWidth();
                curricuScrollview.setScrollViewListener(CurriculumActivity.this);
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
            CurriculumtitleImage.setImageResource(bean.getIcon());
            CurriculumtitleImage.setAlpha(alpha);
            Curriculumtitlexq.setText(bean.getText1());
            Curriculumtitlexq.setAlpha(alpha);
            Curriculumtitlerq.setText(bean.getText2());
            Curriculumtitlerq.setAlpha(alpha);
        }
    }

    @OnClick(R.id.curricuButton)
    public void onViewClicked() {
        Intent intent = new Intent(CurriculumActivity.this, SubmitActivity.class);
        startActivity(intent);
    }
}
