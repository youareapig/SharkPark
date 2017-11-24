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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.BannerAdapter;
import com.weiye.adapter.GalleryAdapter;
import com.weiye.adapter.ListView_1_Adapter;
import com.weiye.data.ParkBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.MyListView;
import com.weiye.myview.MyScrollView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.FourSchoolActivity;
import com.weiye.zl.R;
import com.weiye.zl.ScienceStationActivity;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by DELL on 2017/4/6.
 */
public class Shark_Fragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private MyListView mListView;
    private Gallery gallery;
    private ViewPager viewPager;
    private ImageView[] indexTips, indexBannerImage;
    private List<ParkBean.DataBeanX.BannerBean> bannerList;
    private ViewGroup mView;
    private Handler handler;
    private TextView classAll,kexueyizhan_text;
    private long lastRefreshTime;
    private ImageView kexueyizhan_Img;
    private int num = 1;
    private CustomProgressDialog customProgressDialog;
    private MyThread myThread;
    private AutoRelativeLayout kexueyizhan_top;
    private MyScrollView myScrollView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sharkfragment, container, false);
        mListView = (MyListView) view.findViewById(R.id.listview1);
        gallery = (Gallery) view.findViewById(R.id.gallery);
        viewPager = (ViewPager) view.findViewById(R.id.banner);
        mView = (ViewGroup) view.findViewById(R.id.bannerGroup);
        classAll = (TextView) view.findViewById(R.id.classAll);
        kexueyizhan_text= (TextView) view.findViewById(R.id.kexueyizhan_text);
        kexueyizhan_Img = (ImageView) view.findViewById(R.id.kexueyizhan_Img);
        kexueyizhan_top= (AutoRelativeLayout) view.findViewById(R.id.kexueyizhan_top);
        myScrollView= (MyScrollView) view.findViewById(R.id.myscroll);
        myThread = new MyThread();
        classAll.setOnClickListener(this);
        getBanner();
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
                intent.putExtra("myevent",10);
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
        myScrollView.setVisibility(View.GONE);
        customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/park");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final ParkBean bean = gson.fromJson(result, ParkBean.class);
                //TODO banner
                if (bean.getCode()==1000) {
                    myScrollView.setVisibility(View.VISIBLE);
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
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        indexBannerImage[i] = imageView;
                        Glide.with(getActivity())
                                .load(SingleModleUrl.singleModleUrl().getImgUrl() + bannerList.get(i).getUrl())
                                .centerCrop()
                                .placeholder(R.mipmap.hui)
                                .error(R.mipmap.hui).into(imageView);
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
                    List<ParkBean.DataBeanX.PicBean> pictureList = bean.getData().getPic();
                    gallery.setSpacing(40);
                    gallery.setAdapter(new GalleryAdapter(pictureList, getActivity()));
                    gallery.setSelection(1, true);
                    //TODO 科学驿站

                    if (bean.getData().getIsup() != null) {
                        Glide.with(getActivity()).load(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getIsup().getPic())
                                .bitmapTransform(new CenterCrop(getActivity()), new RoundedCornersTransformation(getActivity(), 8, 0))
                                .placeholder(R.mipmap.hui)
                                .error(R.mipmap.hui)
                                .into(kexueyizhan_Img);
                        kexueyizhan_text.setText(bean.getData().getIsup().getTitle());
                    } else {
                    }
                    if (bean.getData().getData().size() != 0) {
                        final List<ParkBean.DataBeanX.DataBean> keList = bean.getData().getData();
                        mListView.setAdapter(new ListView_1_Adapter(keList, getActivity()));
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ParkBean.DataBeanX.DataBean infoBean = (ParkBean.DataBeanX.DataBean) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                                intent.putExtra("id", infoBean.getId() + "");
                                startActivity(intent);
                            }
                        });
                    }
                    kexueyizhan_Img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), ScienceStationActivity.class);
                            intent.putExtra("id", bean.getData().getIsup().getId() + "");
                            startActivity(intent);
                        }
                    });
                }else {
                    Toast.makeText(getActivity(), "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
