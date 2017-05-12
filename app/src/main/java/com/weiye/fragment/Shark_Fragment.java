package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.BannerAdapter;
import com.weiye.adapter.GalleryAdapter;
import com.weiye.adapter.ListView_1_Adapter;
import com.weiye.data.KTFCBean;
import com.weiye.data.InfoBean;
import com.weiye.data.SubjectStationBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.FourSchoolActivity;
import com.weiye.zl.R;
import com.weiye.zl.ScienceStationActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Shark_Fragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private MyListView mListView;
    private Gallery gallery;
    private ViewPager viewPager;
    private ImageView[] indexTips, indexBannerImage;
    private List<InfoBean.RowsBean> bannerList;
    private ViewGroup mView;
    private Handler handler;
    private TextView classAll;
    private List<KTFCBean.RowsBean> listbean;
    private XRefreshView refreshView;
    private  long lastRefreshTime;
    private RoundedImageView kexueyizhan_Img;
    private List<SubjectStationBean.RowsBean> myList;
    private XScrollView myScroll;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sharkfragment, container, false);
        mListView = (MyListView) view.findViewById(R.id.listview1);
        gallery = (Gallery) view.findViewById(R.id.gallery);
        viewPager = (ViewPager) view.findViewById(R.id.banner);
        mView = (ViewGroup) view.findViewById(R.id.bannerGroup);
        classAll = (TextView) view.findViewById(R.id.classAll);
        refreshView= (XRefreshView) view.findViewById(R.id.refresh);
        kexueyizhan_Img= (RoundedImageView) view.findViewById(R.id.kexueyizhan_Img);
        myScroll= (XScrollView) view.findViewById(R.id.myscroll);
        classAll.setOnClickListener(this);
        visitClass();
        getBanner();
        subjectStation();
        //TODO 刷新
        refreshView.setPullLoadEnable(true);
        refreshView.setPullRefreshEnable(true);
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        visitClass();
                        getBanner();
                        refreshView.stopRefresh();
                        lastRefreshTime = refreshView.getLastRefreshTime();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        refreshView.stopLoadMore();
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % indexBannerImage.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < indexTips.length; i++) {
            if (i == selectItems) {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
            } else {
                indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.classAll:
                Intent intent = new Intent(getActivity(), FourSchoolActivity.class);
                startActivity(intent);
                break;
        }

    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }
    //TODO 课堂风采图片滚动
    private void visitClass() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_KTFCDataService.ashx?op=getTAB_KTFC");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX","0");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                KTFCBean ktfcBean = gson.fromJson(result, KTFCBean.class);
                listbean = ktfcBean.getRows();
                gallery.setSpacing(40);
                gallery.setAdapter(new GalleryAdapter(listbean, getActivity()));
                gallery.setSelection(1, true);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),"获取课堂风采数据失败",Toast.LENGTH_SHORT).show();
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
    //TODO banner图片
    private void getBanner(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_LXXXDataService.ashx?op=getTAB_LXSPTXXX");
        params.addBodyParameter("LX","3");
        params.addBodyParameter("start","0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","Banner"+result);
                Gson gson=new Gson();
                InfoBean bean=gson.fromJson(result,InfoBean.class);
                bannerList=bean.getRows();
                indexTips = new ImageView[bannerList.size()];
                for (int i = 0; i < indexTips.length; i++) {
                    ImageView imageView = new ImageView(getActivity());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
                    imageView.setLayoutParams(layoutParams);
                    indexTips[i] = imageView;
                    if (i == 0) {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_check);
                    } else {
                        indexTips[i].setBackgroundResource(R.drawable.viewpage_goods);

                    }

                    mView.addView(imageView);
                }
                indexBannerImage = new ImageView[bannerList.size()];
                for (int i = 0; i < indexBannerImage.length; i++) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    indexBannerImage[i] = imageView;
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bannerList.get(i).getTXLJ(),imageView);
                }
                viewPager.setOnPageChangeListener(Shark_Fragment.this);
                viewPager.setAdapter(new BannerAdapter(indexBannerImage));
                handler = new Handler() {
                    int bannerNo = 0;

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (viewPager.getCurrentItem() == indexBannerImage.length - 1) {
                            bannerNo = 0;
                        } else {
                            bannerNo = viewPager.getCurrentItem() + 1;
                        }
                        viewPager.setCurrentItem(bannerNo, true);
                    }
                };
                new MyThread().start();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),"获取Banner数据失败",Toast.LENGTH_SHORT).show();
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
    private void subjectStation(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_KTFCDataService.ashx?op=getTAB_KTFC");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("LX","1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","科学驿站"+result);
                Gson gson=new Gson();
                SubjectStationBean bean=gson.fromJson(result,SubjectStationBean.class);
                myList=bean.getRows();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getRows().get(0).getTXLJ(),kexueyizhan_Img);

                mListView.setAdapter(new ListView_1_Adapter(myList, getActivity()));
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        SubjectStationBean.RowsBean rowBean= (SubjectStationBean.RowsBean) adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                        intent.putExtra("img",rowBean.getBJTXLJ());
                        intent.putExtra("title",rowBean.getFCMC());
                        intent.putExtra("time",rowBean.getKSSJ());
                        intent.putExtra("content",rowBean.getFCMS());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag","科学驿站失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                myScroll.smoothScrollTo(0,20);

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
