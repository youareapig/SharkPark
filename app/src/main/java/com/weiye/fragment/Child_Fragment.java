package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.IndexBean;
import com.weiye.data.TestBean;
import com.weiye.data.TestGalleryBean;
import com.weiye.third.BaseAdapterHelper;
import com.weiye.third.Gallery;
import com.weiye.third.QuickPagerAdapter;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SubjectActivity;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Child_Fragment extends Fragment {
    private Gallery mGallery;
    private QuickPagerAdapter<IndexBean.RowsBean> quickPagerAdapter;
    private List<IndexBean.RowsBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment, container, false);
        mGallery= (Gallery) view.findViewById(R.id.myGallery);
        index();
//        mList = new ArrayList<>();
//        mList.add(new TestGalleryBean("天文科学", R.mipmap.gicon));
//        mList.add(new TestGalleryBean("地理科学", R.mipmap.gicon));
//        mList.add(new TestGalleryBean("人文科学", R.mipmap.gicon));
//        mList.add(new TestGalleryBean("科学科学", R.mipmap.gicon));
//        quickPagerAdapter=new QuickPagerAdapter<TestGalleryBean>(getActivity(),R.layout.galleryitem,mList) {
//            @Override
//            protected void convertView(BaseAdapterHelper helper, TestGalleryBean item) {
//
//                helper.setImageResource(R.id.galleryitem_img,item.getmImage());
//                helper.setText(R.id.galleryitem_title,item.getmString());
//                helper.setImageOnClickListener(R.id.galleryitem_img, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(getActivity(),SubjectActivity.class);
//                        startActivity(intent);
//                    }
//                });
//            }
//        };
//        mGallery.setAdapter(quickPagerAdapter);
        return view;
    }
    private void index(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_LXXXDataService.ashx?op=getTAB_LXXX");
        params.addBodyParameter("start","1");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("tag","成功"+result);
                Gson gson=new Gson();
                IndexBean bean=gson.fromJson(result,IndexBean.class);
                mList=bean.getRows();
                quickPagerAdapter=new QuickPagerAdapter<IndexBean.RowsBean>(getActivity(),R.layout.galleryitem,mList) {
                    @Override
                    protected void convertView(BaseAdapterHelper helper, IndexBean.RowsBean item) {
                        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getTestUrl()+item.getTXLJ(), (ImageView) helper.getView(R.id.galleryitem_img));
                        //helper.setImageResource(R.id.galleryitem_img,);
                        helper.setText(R.id.galleryitem_title,item.getLXMC());
                        helper.setImageOnClickListener(R.id.galleryitem_img, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(),SubjectActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                };
                mGallery.setAdapter(quickPagerAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag","失败");
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
