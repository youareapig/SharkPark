package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.XYFCBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.photoshow.ImagePagerActivity;
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
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class AppearanceActivity extends AutoLayoutActivity {
    @BindView(R.id.back2)
    RelativeLayout back2;
    @BindView(R.id.schoolappearance)
    ImageView schoolappearance;
    @BindView(R.id.jxhj)
    ImageView jxhj;
    @BindView(R.id.main5)
    LinearLayout main5;
    @BindView(R.id.number1)
    TextView number1;
    @BindView(R.id.number2)
    TextView number2;
    private Unbinder unbinder;
    private CustomProgressDialog customProgressDialog;
    private List<String> picture1;
    private List<String> picture2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        unbinder = ButterKnife.bind(this);
        visitXYFC();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.back2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back2:
                finish();
                break;
        }
    }

    private void visitXYFC() {
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main5.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/environment");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag","环境"+result);
                main5.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                XYFCBean bean = gson.fromJson(result, XYFCBean.class);
                if (bean.getCode() == 1000) {
                    String fengmian1 = bean.getData().getOne().get(0).getUrl();
                    String fengmian2 = bean.getData().getTwo().get(0).getUrl();
                    picture1=new ArrayList<String>();
                    picture2=new ArrayList<String>();
                    for (int i=0;i<bean.getData().getOne().size();i++){
                        picture1.add(bean.getData().getOne().get(i).getUrl());
                    }
                    for (int i=0;i<bean.getData().getTwo().size();i++){
                        picture2.add(bean.getData().getTwo().get(i).getUrl());
                    }
                    //TODO 圆角和满屏同时实现
                    Glide.with(AppearanceActivity.this).load(SingleModleUrl.singleModleUrl().getImgUrl() + fengmian1)
                            .placeholder(R.mipmap.hui)
                            .error(R.mipmap.hui)
                            .bitmapTransform(new CenterCrop(AppearanceActivity.this),new RoundedCornersTransformation(AppearanceActivity.this,8,0))
                            .into(schoolappearance);
                    Glide.with(AppearanceActivity.this)
                            .load(SingleModleUrl.singleModleUrl().getImgUrl() + fengmian2)
                            .bitmapTransform(new CenterCrop(AppearanceActivity.this),new RoundedCornersTransformation(AppearanceActivity.this,8,0))
                            .into(jxhj);
                    number1.setText(picture1.size()+"");
                    number2.setText(picture2.size()+"");
                    schoolappearance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AppearanceActivity.this, ImagePagerActivity.class);
                            intent.putStringArrayListExtra("photoarr", (ArrayList<String>) picture1);
                            startActivity(intent);
                        }
                    });
                    jxhj.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(AppearanceActivity.this, ImagePagerActivity.class);
                            intent1.putStringArrayListExtra("photoarr", (ArrayList<String>) picture2);
                            startActivity(intent1);
                        }
                    });
                } else {
                    Toast.makeText(AppearanceActivity.this, "暂无介绍", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(AppearanceActivity.this,"网络不佳，请稍后再试",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                main5.setVisibility(View.VISIBLE);
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


}
