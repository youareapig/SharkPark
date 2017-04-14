package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/13.
 */
public class SchoolGridAdapter extends BaseAdapter {
    private List<Integer> list;
    private Activity activity;
    private LayoutInflater inflater;

    public SchoolGridAdapter(List<Integer> list,Activity activity) {
        this.list = list;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = inflater.inflate(R.layout.schoolgriditem, null);
            holder.imageView = (ImageView) view.findViewById(R.id.schoolgird_item_img);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(list.get(i));
        holder.imageView.setImageResource(list.get(i));
        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
    }
}
