package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.FourSchoolGalleryAdapter;
import com.weiye.data.Park_1Bean;
import com.weiye.listenfragment.PhotoFragment;
import com.weiye.listenfragment.VideoFragment;
import com.weiye.myview.CustomProgressDialog;
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
    @BindView(R.id.main8)
    LinearLayout main8;
    @BindView(R.id.backtop)
    RelativeLayout backtop;
    private Unbinder unbinder;
    private List<Park_1Bean.DataBean.TeacherBean> mList;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<Fragment> list;
    private FragmentTransaction fragmentTransaction;
    private int myEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_school);
        unbinder = ButterKnife.bind(this);
        schoolScrollview.smoothScrollTo(0, 20);
        Intent intent = getIntent();
        myEvent = intent.getIntExtra("myevent", 20);
        sdxyVisit();
        schoolFragment();
    }


    private void schoolFragment() {
        list = new ArrayList<>();
        fragment = new VideoFragment(myEvent);
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.schoolfragment, fragment).commit();
        list.add(new VideoFragment(myEvent));
        list.add(new PhotoFragment(myEvent));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.videoText1, R.id.photoText1, R.id.fourschoolBack,R.id.backtop})
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
            case R.id.fourschoolBack:
                finish();
                break;
            case R.id.backtop:
                schoolScrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        schoolScrollview.fullScroll(View.FOCUS_UP);
                    }
                });
                break;
        }
    }

    private void sdxyVisit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        schoolScrollview.setVisibility(View.GONE);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/parkAll");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                schoolScrollview.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                Park_1Bean bean = gson.fromJson(result, Park_1Bean.class);
                mList = bean.getData().getTeacher();
                if (bean.getCode() == 1000) {
                    fourschoolGallery.setAdapter(new FourSchoolGalleryAdapter(mList, FourSchoolActivity.this));
                    fourschoolGallery.setSpacing(60);
                    fourschoolGallery.setSelection(40);
                    fourschoolGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Park_1Bean.DataBean.TeacherBean rBean = (Park_1Bean.DataBean.TeacherBean) adapterView.getItemAtPosition(i % mList.size());
                            Intent intent = new Intent(FourSchoolActivity.this, TeacherStyleActivity.class);
                            intent.putExtra("teacherID", rBean.getTid());
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(FourSchoolActivity.this, "暂无更多数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(FourSchoolActivity.this,"网络不佳，请稍后再试",Toast.LENGTH_SHORT).show();
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
