package com.weiye.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.IndexBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.third.BaseAdapterHelper;
import com.weiye.third.Gallery;
import com.weiye.third.QuickPagerAdapter;
import com.weiye.updateversion.UpdateService;
import com.weiye.utils.ShadowProperty;
import com.weiye.utils.ShadowViewDrawable;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.MainActivity;
import com.weiye.zl.MyApplication;
import com.weiye.zl.R;
import com.weiye.zl.RestartActivity;
import com.weiye.zl.SubjectActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Child_Fragment extends Fragment {
    private Gallery mGallery;
    private QuickPagerAdapter<IndexBean.DataBean> quickPagerAdapter;
    private List<IndexBean.DataBean> mList;
    private CustomProgressDialog customProgressDialog;
    private String updateUrl,updateName,updateContent,updateVersion;
    private int locationVersion = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment, container, false);
        MyApplication application= (MyApplication) getActivity().getApplication();
        locationVersion=application.location;
        updateVersion();
        mGallery = (Gallery) view.findViewById(R.id.myGallery);
        index();
        return view;
    }

    private void index() {
        customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        mGallery.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/index");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mGallery.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                IndexBean bean = gson.fromJson(result, IndexBean.class);
                mList = bean.getData();
                if (bean.getCode() == 1000) {
                    quickPagerAdapter = new QuickPagerAdapter<IndexBean.DataBean>(getActivity(), R.layout.galleryitem, mList) {
                        @Override
                        protected void convertView(BaseAdapterHelper helper, final IndexBean.DataBean item) {
                            ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + item.getSbpic(), (ImageView) helper.getView(R.id.galleryitem_img));
                            helper.setText(R.id.galleryitem_title, item.getSbtitle());
                            helper.setImageOnClickListener(R.id.galleryitem_img, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), SubjectActivity.class);
                                    intent.putExtra("indexID", item.getSbid()+"");
                                    startActivity(intent);
                                }
                            });
                        }
                    };
                    mGallery.setAdapter(quickPagerAdapter);
                } else {
                    Toast.makeText(getActivity(), "暂无更多数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
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
    //TODO 检测版本更新
    private void updateVersion(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"Index/updateInfo");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","版本更新"+result);
                try {
                    JSONObject json=new JSONObject(result);
                    updateName=json.getString("versionName");
                    updateContent=json.getString("description");
                    updateVersion=json.getString("version");
                    updateUrl=json.getString("url");
                    if (Integer.parseInt(updateVersion)>locationVersion){
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View v = inflater.inflate(R.layout.update, null);
                        dialog.setView(v);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        v.findViewById(R.id.unUpdate).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        TextView textContent= (TextView) v.findViewById(R.id.versionContent);
                        //textContent.setText(updateContent);
                        v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), UpdateService.class);
                                intent.putExtra("apkUrl", updateUrl);
                                Log.d("tag","下载地址"+updateUrl);
                                getActivity().startService(intent);
                                dialog.cancel();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag","版本更新错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
