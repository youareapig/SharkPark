package com.weiye.zl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.weiye.myview.ObservableScrollView;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class SchoolNextActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener{
    @BindView(R.id.titleimage)
    RelativeLayout titleimage;
    @BindView(R.id.scrollview2)
    ObservableScrollView scrollview2;
    @BindView(R.id.back1)
    RelativeLayout back1;
    @BindView(R.id.share1)
    RelativeLayout share1;
    @BindView(R.id.title1)
    RelativeLayout title1;
    private Unbinder unbinder;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_next);
        unbinder = ButterKnife.bind(this);
        changTitle();
    }
    private void changTitle(){
        scrollview2.smoothScrollTo(0, 20);
        title1.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = titleimage.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleimage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = titleimage.getHeight();
                titleimage.getWidth();
                scrollview2.setScrollViewListener(SchoolNextActivity.this);
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
            title1.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }
    }
}
