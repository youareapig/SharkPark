package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.weiye.adapter.ActivitiesGridAdpter;
import com.weiye.data.HuodongBean;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.AppearanceActivity;
import com.weiye.zl.IntroActivity;
import com.weiye.zl.R;
import com.weiye.zl.SchoolActivity;
import com.weiye.zl.VedioPlayerActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Park_Fragment extends Fragment implements View.OnClickListener {
    private MyListView mListview;
    private List<HuodongBean.RowsBean> list;
    private AutoRelativeLayout sActivity, appearance, intro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parkfragment, container, false);
        mListview = (MyListView) view.findViewById(R.id.parkListView);
        sActivity = (AutoRelativeLayout) view.findViewById(R.id.sActivity);
        appearance = (AutoRelativeLayout) view.findViewById(R.id.appearance);
        intro = (AutoRelativeLayout) view.findViewById(R.id.intro);
        sActivity.setOnClickListener(this);
        appearance.setOnClickListener(this);
        intro.setOnClickListener(this);
        huodongVisit();
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sActivity:
                Intent intent = new Intent(getActivity(), SchoolActivity.class);
                startActivity(intent);
                break;
            case R.id.appearance:
                Intent intent1 = new Intent(getActivity(), AppearanceActivity.class);
                startActivity(intent1);
                break;
            case R.id.intro:
                Intent intent2 = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent2);
                break;
        }
    }
    private void huodongVisit(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_XXHDDataService.ashx?op=getTAB_XXHD");
        params.addBodyParameter("start","0");
        params.addBodyParameter("ZT","0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                HuodongBean bean=gson.fromJson(result,HuodongBean.class);
                list=bean.getRows();
                mListview.setAdapter(new ActivitiesGridAdpter(list, getActivity()));
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
