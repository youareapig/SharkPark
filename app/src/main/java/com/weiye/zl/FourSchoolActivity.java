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
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.FourSchoolGalleryAdapter;
import com.weiye.data.IndexBean;
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
    @BindView(R.id.screening)
    ImageView screening;
    private Unbinder unbinder;
    private List<IndexBean.RowsBean> mList;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private List<Fragment> list;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_school);
        unbinder = ButterKnife.bind(this);
        schoolScrollview.smoothScrollTo(0, 20);
        sdxyVisit();
        schoolFragment();
    }


    private void schoolFragment() {
        list = new ArrayList<>();
        fragment = new VideoFragment(null);
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.schoolfragment, fragment).commit();
        list.add(new VideoFragment(null));
        list.add(new PhotoFragment(null));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.videoText1, R.id.photoText1, R.id.screening,R.id.fourschoolBack})
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
//                Intent intent = new Intent(FourSchoolActivity.this, ScreenActivity.class);
//                startActivity(intent);
                break;
            case R.id.fourschoolBack:
                finish();
        }
    }

    private void sdxyVisit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXXX");
        params.addBodyParameter("start", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                IndexBean bean = gson.fromJson(result, IndexBean.class);
                mList = bean.getRows();
                fourschoolGallery.setAdapter(new FourSchoolGalleryAdapter(mList, FourSchoolActivity.this));
                fourschoolGallery.setSpacing(60);
                fourschoolGallery.setSelection(40);
                fourschoolGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        IndexBean.RowsBean rBean = (IndexBean.RowsBean) adapterView.getItemAtPosition(i%mList.size());
                        Intent intent = new Intent(FourSchoolActivity.this, SubjectActivity.class);
                        intent.putExtra("indexID", rBean.getID()+"");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(FourSchoolActivity.this,"数据加载失败",Toast.LENGTH_SHORT).show();
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
