package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiye.data.TestGalleryBean;
import com.weiye.third.BaseAdapterHelper;
import com.weiye.third.Gallery;
import com.weiye.third.QuickPagerAdapter;
import com.weiye.zl.R;
import com.weiye.zl.SubjectActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Child_Fragment extends Fragment {
    private Gallery mGallery;
    private QuickPagerAdapter<TestGalleryBean> quickPagerAdapter;
    private List<TestGalleryBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment, container, false);
        mGallery= (Gallery) view.findViewById(R.id.myGallery);
        mList = new ArrayList<>();
        mList.add(new TestGalleryBean("天文", R.mipmap.gicon));
        mList.add(new TestGalleryBean("地理", R.mipmap.gicon));
        mList.add(new TestGalleryBean("人文", R.mipmap.gicon));
        mList.add(new TestGalleryBean("科学", R.mipmap.gicon));
        quickPagerAdapter=new QuickPagerAdapter<TestGalleryBean>(getActivity(),R.layout.galleryitem,mList) {
            @Override
            protected void convertView(BaseAdapterHelper helper, TestGalleryBean item) {

                helper.setImageResource(R.id.galleryitem_img,item.getmImage());
                helper.setText(R.id.galleryitem_title,item.getmString());
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
        return view;
    }

}
