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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.weiye.adapter.ActivitiesGridAdpter;
import com.weiye.data.HuodongBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.AppearanceActivity;
import com.weiye.zl.IntroActivity;
import com.weiye.zl.R;
import com.weiye.zl.SchoolActivity;
import com.weiye.zl.SchoolImageActivity;
import com.weiye.zl.SchoolVideoActivity;
import com.weiye.zl.VedioPlayerActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.w3c.dom.Text;
import org.w3c.dom.ls.LSOutput;
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
    private CustomProgressDialog customProgressDialog;
    private XRefreshView main1;
    private  long lastRefreshTime;
    private TextView showNo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parkfragment, container, false);
        mListview = (MyListView) view.findViewById(R.id.parkListView);
        sActivity = (AutoRelativeLayout) view.findViewById(R.id.sActivity);
        appearance = (AutoRelativeLayout) view.findViewById(R.id.appearance);
        main1= (XRefreshView) view.findViewById(R.id.main1);
        intro = (AutoRelativeLayout) view.findViewById(R.id.intro);
        showNo= (TextView) view.findViewById(R.id.showNO);
        sActivity.setOnClickListener(this);
        appearance.setOnClickListener(this);
        intro.setOnClickListener(this);
        huodongVisit();
        main1.setPullLoadEnable(true);
        main1.setPullRefreshEnable(true);
        main1.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        huodongVisit();
                        main1.stopRefresh();
                        lastRefreshTime = main1.getLastRefreshTime();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        main1.stopLoadMore();
                    }
                }, 2000);
            }

            @Override
            public void onRelease(float direction) {
            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
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

    private void huodongVisit() {
        customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main1.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXHDDataService.ashx?op=getTAB_XXHD");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("ZT", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main1.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                HuodongBean bean = gson.fromJson(result, HuodongBean.class);
                list = bean.getRows();
                if (bean.getTotal()==0){
                    showNo.setVisibility(View.VISIBLE);
                }else {
                    showNo.setVisibility(View.GONE);
                    mListview.setAdapter(new ActivitiesGridAdpter(list, getActivity()));
                }

                mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        HuodongBean.RowsBean bean1 = (HuodongBean.RowsBean) adapterView.getItemAtPosition(i);
                        Intent intent = null;
                        if (bean1.getBJSFSP().equals("0")) {
                            intent = new Intent(getActivity(), SchoolImageActivity.class);
                            intent.putExtra("txdz", bean1.getTXLJ());
                            intent.putExtra("hdms", bean1.getHDMS());
                        } else {
                            intent = new Intent(getActivity(), SchoolVideoActivity.class);
                            intent.putExtra("txdz", bean1.getBJTXLJ());
                            intent.putExtra("spdz", bean1.getTXLJ());
                            intent.putExtra("hdms", bean1.getHDMS());
                        }
                        getActivity().startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
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
