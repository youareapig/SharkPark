package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weiye.adapter.FourSchoolGalleryAdapter;
import com.weiye.data.TestBean1;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class FourSchoolActivity extends AutoLayoutActivity {
    @BindView(R.id.fourschoolBack)
    RelativeLayout fourschoolBack;
    @BindView(R.id.fourschoolGallery)
    Gallery fourschoolGallery;
    @BindView(R.id.videoText1)
    TextView videoText1;
    @BindView(R.id.photoText1)
    TextView photoText1;
    @BindView(R.id.schoolfragment)
    LinearLayout schoolfragment;
    @BindView(R.id.schoolScrollview)
    ScrollView schoolScrollview;
    @BindView(R.id.screening)
    ImageView screening;
    private Unbinder unbinder;
    private List<TestBean1> mList;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<Fragment> list;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_school);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        schoolScrollview.smoothScrollTo(0, 20);
        shcoolGallery();
        schoolFragment();
    }

    //TODO 四大学校
    private void shcoolGallery() {
        mList = new ArrayList<>();
        mList.add(new TestBean1(R.mipmap.gicon, "学院一"));
        mList.add(new TestBean1(R.mipmap.gicon, "学院二"));
        mList.add(new TestBean1(R.mipmap.gicon, "学院三"));
        mList.add(new TestBean1(R.mipmap.gicon, "学院四"));
        fourschoolGallery.setAdapter(new FourSchoolGalleryAdapter(mList, this));
        fourschoolGallery.setSpacing(60);
        fourschoolGallery.setSelection(1, true);
        fourschoolGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FourSchoolActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });
    }

    private void schoolFragment() {
        list = new ArrayList<>();
        fragment = new VideoFragment();
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.schoolfragment, fragment).commit();
        list.add(new VideoFragment());
        list.add(new PhotoFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.videoText1, R.id.photoText1,R.id.screening})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.videoText1:
                videoText1.setTextColor(this.getResources().getColor(R.color.black));
                photoText1.setTextColor(this.getResources().getColor(R.color.gray));
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.schoolfragment, list.get(0));
                fragmentTransaction.commit();
                break;
            case R.id.photoText1:
                videoText1.setTextColor(this.getResources().getColor(R.color.gray));
                photoText1.setTextColor(this.getResources().getColor(R.color.black));
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.schoolfragment, list.get(1));
                fragmentTransaction.commit();
                break;
            case R.id.screening:
                Intent intent=new Intent(FourSchoolActivity.this,ScreenActivity.class);
                startActivity(intent);
                break;
        }
    }
}
