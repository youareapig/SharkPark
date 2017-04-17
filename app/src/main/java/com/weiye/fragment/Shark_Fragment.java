package com.weiye.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.BannerAdapter;
import com.weiye.adapter.GalleryAdapter;
import com.weiye.adapter.ListView_1_Adapter;
import com.weiye.myview.MyListView;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Shark_Fragment extends Fragment implements ViewPager.OnPageChangeListener {
    private List<Integer> list;
    private MyListView mListView;
    private HashMap<String, Object> hashMap1, hashMap2;
    private List<HashMap<String, Object>> hashList;
    private Gallery gallery;
    private ViewPager viewPager;
    private ImageView[] indexTips, indexBannerImage;
    private List<Integer> bannerList;
    private ViewGroup mView;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sharkfragment, container, false);
        mListView = (MyListView) view.findViewById(R.id.listview1);
        gallery = (Gallery) view.findViewById(R.id.gallery);
        viewPager = (ViewPager) view.findViewById(R.id.banner);
        mView = (ViewGroup) view.findViewById(R.id.bannerGroup);
        setGridView();
        science();
        setBanner();
        return view;
    }
    //TODO Banner
    private void setBanner() {
        bannerList = new ArrayList<>();
        bannerList.add(R.mipmap.aaa);
        bannerList.add(R.mipmap.aaaa);

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
            imageView.setImageResource(list.get(i));
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

    //TODO 课堂风采图片滚动
    private void setGridView() {
        list = new ArrayList<>();
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.background);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        gallery.setSpacing(40);
        gallery.setAdapter(new GalleryAdapter(list, getActivity()));
        gallery.setSelection(1, true);

    }

    //TODO 科学驿站Listview
    private void science() {
        hashList = new ArrayList<>();
        hashMap1 = new HashMap<>();
        hashMap2 = new HashMap<>();

        hashMap1.put("icon", R.mipmap.shark);
        hashMap1.put("intro", "这是一只大鲨鱼，大鲨鱼啊大鲨鱼！！！！");

        hashMap2.put("icon", R.mipmap.shark);
        hashMap2.put("intro", "这是一只小鲨鱼，小鲨鱼啊小鲨鱼！！！！");

        hashList.add(hashMap1);
        hashList.add(hashMap2);

        mListView.setAdapter(new ListView_1_Adapter(hashList, getActivity()));

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
}
