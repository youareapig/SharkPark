package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.data.InfoBean;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.VedioPlayerActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 *
 *避免打包出现错误
 */
@SuppressLint({"NewApi","ValidFragment"})
public class VideoFragment extends Fragment{
    private MyListView listView;
    private List<InfoBean.RowsBean> list;
    private String indexID;


    public VideoFragment(String indexID) {
        this.indexID=indexID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videofragment, container, false);
        visitVideo();
        listView= (MyListView) view.findViewById(R.id.videofragment_listview);
        return view;
    }
    private void visitVideo(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID",indexID);
        params.addBodyParameter("SFSP","0");
        params.addBodyParameter("start","0");
        params.addBodyParameter("LX","0");
        Log.d("tag","id"+indexID);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                InfoBean bean=gson.fromJson(result,InfoBean.class);
                list=bean.getRows();
                listView.setAdapter(new SubVideoListViewAdapter(list,getActivity()));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        InfoBean.RowsBean bean1= (InfoBean.RowsBean) adapterView.getItemAtPosition(i);
                        Intent intent=new Intent(getActivity(), VedioPlayerActivity.class);
                        intent.putExtra("videoUrl",bean1.getSPLJ());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag","视频出错");
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
