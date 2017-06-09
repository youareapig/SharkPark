package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.weiye.adapter.CurriculumGalleryAdapter;
import com.weiye.adapter.CurriculumListViewAdapter;
import com.weiye.data.KCBBean;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.utils.UserLoginDialog1;
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
    private List<Integer> iconList;
    private int height, iconID;
    private String indexID;
    private List<KCBBean.RowsBeanX> cList;
    private SharedPreferences sharedPreferences;
    private KCBBean.RowsBeanX rowsBean;
    private List<KCBBean.RowsBeanX.RowsBean> myList;
private CurriculumListViewAdapter adapterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        Intent intent = getIntent();
        indexID = intent.getStringExtra("LXID");
        changDate();
        showDay();
        init();
    }

    private void changDate() {
        iconList = new ArrayList<>();
        iconList.add(R.mipmap.day1);
        iconList.add(R.mipmap.day2);
        iconList.add(R.mipmap.day3);
        iconList.add(R.mipmap.day4);
        iconList.add(R.mipmap.day5);
        iconList.add(R.mipmap.day6);
        iconList.add(R.mipmap.day7);
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

    private void init() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_KCAPDataService.ashx?op=getTAB_KCAP");
        params.addBodyParameter("KCLX", indexID);
        params.addBodyParameter("start", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "课程安排" + result);
                Gson gson = new Gson();
                final KCBBean bean = gson.fromJson(result, KCBBean.class);
                cList = bean.getRows();
                final CurriculumGalleryAdapter adapter = new CurriculumGalleryAdapter(iconList, cList, CurriculumActivity.this);
                curricuGallery.setAdapter(adapter);
                curricuGallery.setSpacing(60);
                curricuGallery.setSelection(77);
                curricuGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        myList=bean.getRows().get(i% iconList.size()).getRows();
                        rowsBean = cList.get(i % iconList.size());
                        iconID = iconList.get(i % iconList.size());
                        adapter.setSelectItem(i % (iconList.size()));
                        adapter.notifyDataSetChanged();
                        adapterList = new CurriculumListViewAdapter(CurriculumActivity.this, myList);
                        curricuListview.setAdapter(adapterList);
                        adapterList.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "课程安排请求错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
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
            CurriculumtitleImage.setImageResource(iconID);
            CurriculumtitleImage.setAlpha(alpha);
            Curriculumtitlexq.setText(rowsBean.getWeek());
            Curriculumtitlexq.setAlpha(alpha);
            Curriculumtitlerq.setText(rowsBean.getDate());
            Curriculumtitlerq.setAlpha(alpha);
        }
    }

    @OnClick({R.id.curricuButton, R.id.back5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.curricuButton:
                String tag = sharedPreferences.getString("usertag", "0");
                if (tag.equals("1")) {
                    Intent intent1 = new Intent(CurriculumActivity.this, SubmitActivity.class);
                    startActivity(intent1);
                } else {
                    new UserLoginDialog1(this).loginDialog();
                }
                break;
            case R.id.back5:
                finish();
                break;
        }

    }
}
