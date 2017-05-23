package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.InfoBean;
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
    private Unbinder unbinder;
    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    private String xyfcID, jxhjID;
    private CustomProgressDialog customProgressDialog, customProgressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        unbinder = ButterKnife.bind(this);
        visitJXHJ();
        visitXYFC();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.back2, R.id.schoolappearance, R.id.jxhj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back2:
                finish();
                break;
            case R.id.schoolappearance:
                list.clear();
                openXYFC();
                break;
            case R.id.jxhj:
                list1.clear();
                openJXHJ();
                break;
        }
    }

    private void visitXYFC() {
        customProgressDialog = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main5.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XYFCDataService.ashx?op=getTAB_XYFC");
        params.addBodyParameter("LX", "0");
        params.addBodyParameter("start", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main5.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                XYFCBean bean = gson.fromJson(result, XYFCBean.class);
                xyfcID = bean.getRows().get(0).getID();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getRows().get(0).getTXLJ(), schoolappearance);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "校园风采失败");
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

    private void visitJXHJ() {
        customProgressDialog1 = new CustomProgressDialog(this, "玩命加载中...", R.drawable.frame);
        customProgressDialog1.setCanceledOnTouchOutside(false);
        customProgressDialog1.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XYFCDataService.ashx?op=getTAB_XYFC");
        params.addBodyParameter("LX", "1");
        params.addBodyParameter("start", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                XYFCBean bean = gson.fromJson(result, XYFCBean.class);
                jxhjID = bean.getRows().get(0).getID();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getRows().get(0).getTXLJ(), jxhj);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "教学环境失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog1.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void openXYFC() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID", xyfcID);
        params.addBodyParameter("LX", "6");
        params.addBodyParameter("start", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                InfoBean bean = gson.fromJson(result, InfoBean.class);
                for (int i = 0; i < bean.getRows().size(); i++) {
                    list.add(bean.getRows().get(i).getTXLJ());
                }
                Intent intent = new Intent(AppearanceActivity.this, ImagePagerActivity.class);
                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) list);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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

    private void openJXHJ() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID", jxhjID);
        params.addBodyParameter("LX", "6");
        params.addBodyParameter("start", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                InfoBean bean = gson.fromJson(result, InfoBean.class);
                for (int i = 0; i < bean.getRows().size(); i++) {
                    list1.add(bean.getRows().get(i).getTXLJ());
                }
                Intent intent = new Intent(AppearanceActivity.this, ImagePagerActivity.class);
                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) list1);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
}
