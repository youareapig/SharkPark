package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.CurriculumGalleryAdapter;
import com.weiye.adapter.CurriculumListViewAdapter;
import com.weiye.data.KCBBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.utils.UserLoginDialog1;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
    @BindView(R.id.wu)
    TextView wu;
    @BindView(R.id.main9)
    LinearLayout main9;
    @BindView(R.id.main11)
    FrameLayout main11;
    private Unbinder unbinder;
    private int height;
    private String indexID, userID, iconID,hTime,hDate;
    private SharedPreferences sharedPreferences;
    private CurriculumListViewAdapter adapterList;
    private CurriculumListViewAdapter.callBack callBack;
    private List<KCBBean.DataBean> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "0");
        Intent intent = getIntent();
        indexID = intent.getStringExtra("LXID");
        callBack = new CurriculumListViewAdapter.callBack() {

            @Override
            public void callBackKCID(StringBuilder gradeID) {
                if (gradeID.substring(0, 1).equals(",")) {
                    gradeID.delete(0, 1);
                    String tag = sharedPreferences.getString("usertag", "0");
                    if (tag.equals("1")) {
                        Intent intent1 = new Intent(CurriculumActivity.this, SubmitActivity.class);
                        intent1.putExtra("kcid", gradeID.toString());
                        startActivity(intent1);
                    } else {
                        new UserLoginDialog1(CurriculumActivity.this, gradeID.toString()).loginDialog();
                    }
                }
            }
        };
        showDay();
        init();
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/courseLst");
        params.addBodyParameter("sbid", indexID);
        params.addBodyParameter("uid", userID);
        Log.d("tag",indexID+"----"+userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                curricuScrollview.setVisibility(View.VISIBLE);
                curricuButton.setVisibility(View.VISIBLE);
                wu.setVisibility(View.GONE);
                Gson gson = new Gson();
                final KCBBean bean = gson.fromJson(result, KCBBean.class);
                if (bean.getCode() == 1000) {
                    list1 = bean.getData();
                    final CurriculumGalleryAdapter adapter = new CurriculumGalleryAdapter(list1, CurriculumActivity.this);
                    curricuGallery.setAdapter(adapter);
                    curricuGallery.setSpacing(60);
                    //curricuGallery.setSelection((list1.size())*10);
                    curricuGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            List<KCBBean.DataBean.ChildrenBean> list2 = bean.getData().get(i).getChildren();
                            iconID = list1.get(i).getPic();
                            hDate=list1.get(i).getDates();
                            hTime=list1.get(i).getWeek();
                            adapter.setSelectItem(i);
                            adapter.notifyDataSetChanged();
                            adapterList = new CurriculumListViewAdapter(CurriculumActivity.this, list2, list1, callBack);
                            curricuListview.setAdapter(adapterList);
                            adapterList.notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    curricuScrollview.setVisibility(View.GONE);
                    curricuButton.setVisibility(View.GONE);
                    wu.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(CurriculumActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            Log.d("tag","滑动了"+iconID+hTime+hDate);
            float scale = (float) y / height;
            float alpha = (255 * scale);
            Curriculumtitlexq.setText(hTime);
            Curriculumtitlexq.setAlpha(alpha);
            Curriculumtitlerq.setText(hDate);
            Curriculumtitlerq.setAlpha(alpha);
            ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + iconID, CurriculumtitleImage);
            CurriculumtitleImage.setAlpha(alpha);
        }
    }

    @OnClick({R.id.curricuButton, R.id.back5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.curricuButton:
                adapterList.KCyuyue();
                break;
            case R.id.back5:
                finish();
                break;
        }

    }


}
