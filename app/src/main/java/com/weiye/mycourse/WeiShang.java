package com.weiye.mycourse;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.weiye.adapter.WeishangAdapter;
import com.weiye.data.WeishangBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 2017/7/5.
 */

public class WeiShang extends Fragment {
    private ListView swipeMenuListView;
    private WeishangAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String userID,babyID;
    private CustomProgressDialog customProgressDialog;
    private TextView textViewNo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weishang, container, false);
        swipeMenuListView = (ListView) view.findViewById(R.id.weishangListView);
        textViewNo= (TextView) view.findViewById(R.id.myCourseNo1);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        babyID = sharedPreferences.getString("babyId", "未知");
        visit();
        return view;
    }


    private void visit() {
        customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/myCourselst");
        params.addBodyParameter("bid", babyID);
        params.addBodyParameter("tp", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                WeishangBean bean = gson.fromJson(result, WeishangBean.class);
                if (bean.getCode() == 3000) {
                    swipeMenuListView.setVisibility(View.VISIBLE);
                    textViewNo.setVisibility(View.GONE);
                    adapter = new WeishangAdapter(getActivity(), bean.getData());
                    swipeMenuListView.setAdapter(adapter);
                } else {
                    swipeMenuListView.setVisibility(View.GONE);
                    textViewNo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
