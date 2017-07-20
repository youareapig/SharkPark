package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class AppearanceActivity extends AutoLayoutActivity {
    @BindView(R.id.back2)
    RelativeLayout back2;
    @BindView(R.id.schoolappearance)
    RoundedImageView schoolappearance;
    @BindView(R.id.jxhj)
    RoundedImageView jxhj;
    @BindView(R.id.main5)
    LinearLayout main5;
    @BindView(R.id.number1)
    TextView number1;
    @BindView(R.id.number2)
    TextView number2;
    private Unbinder unbinder;
    private CustomProgressDialog customProgressDialog;

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
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/detailLst");
        params.addBodyParameter("tp","3");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main5.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                XYFCBean bean = gson.fromJson(result, XYFCBean.class);
                if (bean.getCode() == 1000) {
                    String fengmian1 = bean.getData().get(0).getImgurl().get(0);
                    String fengmian2 = bean.getData().get(1).getImgurl().get(0);
                    final List<String> picture1 = bean.getData().get(0).getImgurl();
                    final List<String> picture2 = bean.getData().get(1).getImgurl();
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + fengmian1, schoolappearance);
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + fengmian2, jxhj);
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
                Toast.makeText(AppearanceActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
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
