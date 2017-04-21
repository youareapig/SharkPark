package com.weiye.zl;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.weiye.adapter.CurriculumGalleryAdapter;
import com.weiye.data.TestCurrBean;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class CurriculumActivity extends AutoLayoutActivity {
    @BindView(R.id.back5)
    RelativeLayout back5;
    @BindView(R.id.curricuListview)
    ListView curricuListview;
    @BindView(R.id.curricuGallery)
    Gallery curricuGallery;
    private Unbinder unbinder;
    private List<TestCurrBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        changDate();
    }

    private void changDate() {
        mList = new ArrayList<>();
        TestCurrBean bean1 = new TestCurrBean(R.mipmap.mon, "周一", "(18日)");
        TestCurrBean bean2 = new TestCurrBean(R.mipmap.tus, "周二", "(19日)");
        TestCurrBean bean3 = new TestCurrBean(R.mipmap.tha, "周三", "(20日)");
        TestCurrBean bean4 = new TestCurrBean(R.mipmap.sta, "周四", "(21日)");
        TestCurrBean bean5 = new TestCurrBean(R.mipmap.sun, "周五", "(22日)");
        mList.add(bean1);
        mList.add(bean2);
        mList.add(bean3);
        mList.add(bean4);
        mList.add(bean5);
        final CurriculumGalleryAdapter adapter = new CurriculumGalleryAdapter(mList, this);
        curricuGallery.setAdapter(adapter);
        curricuGallery.setSpacing(30);
        curricuGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectItem(i);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}