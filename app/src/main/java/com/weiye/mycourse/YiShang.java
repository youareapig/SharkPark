package com.weiye.mycourse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weiye.adapter.YishangAdapter;
import com.weiye.data.YishangBean;
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

public class YiShang extends Fragment {
    private ListView listView;
    private YishangAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String userID;
    private CustomProgressDialog customProgressDialog;
    private TextView textViewNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yishang, container, false);
        listView = (ListView) view.findViewById(R.id.yishangListView);
        textViewNo = (TextView) view.findViewById(R.id.myCourseNo3);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        visit();
        return view;
    }

    private void visit() {
        customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/myCourselst");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "3");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                YishangBean bean = gson.fromJson(result, YishangBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getCourse().size()==0){
                        listView.setVisibility(View.GONE);
                        textViewNo.setVisibility(View.VISIBLE);
                    }else {
                        listView.setVisibility(View.VISIBLE);
                        textViewNo.setVisibility(View.GONE);
                        adapter = new YishangAdapter(bean.getData().getCourse(), getActivity());
                        listView.setAdapter(adapter);
                    }

                } else {
                    listView.setVisibility(View.GONE);
                    textViewNo.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "已上错误");
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
