package com.weiye.mycourse;


import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.YiChangAdapter;
import com.weiye.data.YichangBean;
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

public class YiChang extends Fragment {
    private ListView listView;
    private YiChangAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String userID;
    private CustomProgressDialog customProgressDialog;
    private TextView textViewNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yichang, container, false);
        listView = (ListView) view.findViewById(R.id.yichangListView);
        textViewNo = (TextView) view.findViewById(R.id.myCourseNo2);
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
                final YichangBean bean = gson.fromJson(result, YichangBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getCourse().size()==0){
                        textViewNo.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }else {
                        textViewNo.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        adapter = new YiChangAdapter(getActivity(), bean.getData().getCourse());
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                View v = inflater.inflate(R.layout.yichangdilog, null);
                                dialog.setView(v);
                                dialog.setCanceledOnTouchOutside(true);
                                dialog.show();
                                Window window=dialog.getWindow();
                                WindowManager.LayoutParams layoutParams=window.getAttributes();
                                //TODO 设置对话框的大小时，一定要在dialog.show();的后面，否则没用
                                layoutParams.alpha=0.6f;
                                layoutParams.width=800;
                                window.setAttributes(layoutParams);
                                TextView textView = (TextView) v.findViewById(R.id.yichang);
                                textView.setText(bean.getData().getCourse().get(position).getInfo());
                            }
                        });
                    }

                } else {
                    textViewNo.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
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
