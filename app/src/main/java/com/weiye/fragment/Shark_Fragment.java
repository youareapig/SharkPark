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
import com.weiye.data.ParkBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.FourSchoolActivity;
import com.weiye.zl.R;
import com.weiye.zl.ScienceStationActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Shark_Fragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private MyListView mListView;
    private Gallery gallery;
    private ViewPager viewPager;
    private ImageView[] indexTips, indexBannerImage;
    private List<ParkBean.DataBean.BannerBean> bannerList;
    private ViewGroup mView;
    private Handler handler;
    private TextView classAll;
    private XRefreshView refreshView;
    private long lastRefreshTime;
    private RoundedImageView kexueyizhan_Img;
    private XScrollView myScroll;
    private int num = 1;
    private CustomProgressDialog customProgressDialog;
    private MyThread myThread;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sharkfragment, container, false);
        mListView = (MyListView) view.findViewById(R.id.listview1);
        gallery = (Gallery) view.findViewById(R.id.gallery);
        viewPager = (ViewPager) view.findViewById(R.id.banner);
        mView = (ViewGroup) view.findViewById(R.id.bannerGroup);
        classAll = (TextView) view.findViewById(R.id.classAll);
        refreshView = (XRefreshView) view.findViewById(R.id.refresh);
        kexueyizhan_Img = (RoundedImageView) view.findViewById(R.id.kexueyizhan_Img);
        myScroll = (XScrollView) view.findViewById(R.id.myscroll);
        myThread = new MyThread();
        classAll.setOnClickListener(this);
        getBanner();
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
                        setRefresh();
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
                        num++;
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

    //TODO banner图片
    private void getBanner() {
        customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        refreshView.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/parkLst");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                refreshView.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                ParkBean bean = gson.fromJson(result, ParkBean.class);
                //TODO banner
                bannerList = bean.getData().getBanner();
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
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bannerList.get(i).getUrl(), imageView);
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
                myThread.start();
                //TODO 课堂风采
                List<String> pictureList = bean.getData().getPic();
                gallery.setSpacing(40);
                gallery.setAdapter(new GalleryAdapter(pictureList, getActivity()));
                gallery.setSelection(1, true);
                //TODO 科学驿站
                final List<ParkBean.DataBean.InfoBean> keList = bean.getData().getInfo();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + keList.get(0).getPic(), kexueyizhan_Img);

                mListView.setAdapter(new ListView_1_Adapter(keList, getActivity()));
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ParkBean.DataBean.InfoBean infoBean = (ParkBean.DataBean.InfoBean) adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                        intent.putExtra("id", infoBean.getId());
                        startActivity(intent);
                    }
                });
                kexueyizhan_Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                        intent.putExtra("id", keList.get(0).getId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
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

    //TODO 刷新是不刷新banner，否则出现无限开启线程
    private void setRefresh() {
        customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        refreshView.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/parkLst");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ParkBean bean = gson.fromJson(result, ParkBean.class);
                //TODO 课堂风采
                List<String> pictureList = bean.getData().getPic();
                gallery.setSpacing(40);
                gallery.setAdapter(new GalleryAdapter(pictureList, getActivity()));
                gallery.setSelection(1, true);
                //TODO 科学驿站
                final List<ParkBean.DataBean.InfoBean> keList = bean.getData().getInfo();
                ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + keList.get(0).getPic(), kexueyizhan_Img);

                mListView.setAdapter(new ListView_1_Adapter(keList, getActivity()));
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ParkBean.DataBean.InfoBean infoBean = (ParkBean.DataBean.InfoBean) adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                        intent.putExtra("id", infoBean.getId());
                        startActivity(intent);
                    }
                });
                kexueyizhan_Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                        intent.putExtra("id", keList.get(0).getId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                refreshView.setVisibility(View.VISIBLE);
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

}
