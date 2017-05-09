package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weiye.data.TestBean1;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class FourSchoolGalleryAdapter extends BaseAdapter {
    private List<TestBean1> list;
    private LayoutInflater inflater;
    private int count;
    public FourSchoolGalleryAdapter(List<TestBean1> list, Activity context) {
        this.list = list;
        this.inflater = context.getLayoutInflater();
    }

    @Override
    public int getCount() {
        count=list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i%count);
    }

    @Override
    public long getItemId(int i) {
        return i%count;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TestBean1 bean1 = list.get(i%count);
        view = inflater.inflate(R.layout.fourschoolitem, null);
        TextView textView = (TextView) view.findViewById(R.id.fourschoolitem_text);
        RoundedImageView roundedImageView = (RoundedImageView) view.findViewById(R.id.fourschoolitem_img);
        AutoUtils.autoSize(view);
        textView.setText(bean1.getmString());
        roundedImageView.setImageResource(bean1.getmImage());
        return view;
    }
}
