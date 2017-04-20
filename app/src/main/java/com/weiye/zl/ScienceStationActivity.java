package com.weiye.zl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.myview.ObservableScrollView;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class ScienceStationActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener{
    @BindView(R.id.scienceStationImg)
    RelativeLayout scienceStationImg;
    @BindView(R.id.scienceStation_scrollview)
    ObservableScrollView scienceStationScrollview;
    @BindView(R.id.scienceStationBack)
    RelativeLayout scienceStationBack;
    @BindView(R.id.scienceStationShare)
    RelativeLayout scienceStationShare;
    @BindView(R.id.scienceStationTitle)
    RelativeLayout scienceStationTitle;
    private Unbinder unbinder;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_station);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        changTitle();
    }
    private void changTitle(){
        scienceStationScrollview.smoothScrollTo(0, 20);
        scienceStationTitle.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = scienceStationImg.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scienceStationImg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = scienceStationImg.getHeight();
                scienceStationImg.getWidth();
                scienceStationScrollview.setScrollViewListener(ScienceStationActivity.this);
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
            scienceStationTitle.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }
    }
}
