package com.weiye.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class GalleryAdapter extends BaseAdapter{
    private List<Integer> list;
    private Context context;
    private int count;

    public GalleryAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        count=list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RoundedImageView imageView=new RoundedImageView(context);
        imageView.setCornerRadius(20);
        imageView.setImageResource(list.get(i%count));
        imageView.setId(list.get(i%count));
        imageView.setLayoutParams(new Gallery.LayoutParams(960,500));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
