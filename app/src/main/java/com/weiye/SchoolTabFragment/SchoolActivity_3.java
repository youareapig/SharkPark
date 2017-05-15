package com.weiye.schoolTabFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.SchoolRecyclerAdapter;
import com.weiye.data.HuodongBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SchoolImageActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/20.
 */
public class SchoolActivity_3 extends Fragment {
    private RecyclerView schoolRecycler;
    private List<HuodongBean.RowsBean> list;
    private ScrollView scrollView;
    private RoundedImageView mImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schoolactivity1, container, false);
        schoolRecycler = (RecyclerView) view.findViewById(R.id.schoolRecycler);
        scrollView = (ScrollView) view.findViewById(R.id.mScroll);
        mImage = (RoundedImageView) view.findViewById(R.id.mImage);
        scrollView.smoothScrollTo(0, 20);
        visit();
        return view;
    }

    private void visit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXHDDataService.ashx?op=getTAB_XXHD");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("ZT", "2");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HuodongBean bean = gson.fromJson(result, HuodongBean.class);
                list = bean.getRows();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + list.get(0).getBJTXLJ(), mImage);
                schoolRecycler.setAdapter(new SchoolRecyclerAdapter(list));
                schoolRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
