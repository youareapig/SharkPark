package com.weiye.listenfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.data.InfoBean;
import com.weiye.data.PhotoBean;
import com.weiye.myview.MyListView;
import com.weiye.photoshow.ImagePagerActivity;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class PhotoFragment extends Fragment {
    private MyListView listView;
    private List<InfoBean.RowsBean> list;
    private List<String> photoList=new ArrayList<>();
    private String indexID;
    public PhotoFragment(String indexID) {
        this.indexID = indexID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment, container, false);
        listView = (MyListView) view.findViewById(R.id.photofragment_listview);
        visitPhoto();
        return view;
    }

    private void visitPhoto() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID", indexID);
        params.addBodyParameter("SFSP", "1");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                InfoBean bean=gson.fromJson(result,InfoBean.class);
                list=bean.getRows();
                listView.setAdapter(new SubPhotoListViewAdapter(list, getActivity()));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        InfoBean.RowsBean bean1= (InfoBean.RowsBean) adapterView.getItemAtPosition(i);
                        photoList.clear();
                        getPhoto(bean1.getID());
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "相册访问失败");
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
    private void getPhoto(String ID){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LXID",ID);
        params.addBodyParameter("SFSP", "1");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX", "4");
        Log.d("tag","照片查看器参数说明"+ID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","照片查看器"+result);
                Gson gson=new Gson();
                PhotoBean infoBean=gson.fromJson(result,PhotoBean.class);
                for (int i=0;i<infoBean.getRows().size();i++){
                    photoList.add(infoBean.getRows().get(i).getTXLJ());
                }
                Intent intent=new Intent(getActivity(), ImagePagerActivity.class);
                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) photoList);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag","照片查看器失败");
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
