package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/13.
 */
public class ListView_1_Adapter extends BaseAdapter {
    private List<HashMap<String, Object>> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public ListView_1_Adapter(List<HashMap<String, Object>> list, Activity activity) {
        this.list = list;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
          return   list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        HashMap<String, Object> hashMap = list.get(i);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listviewitem1, null);
            holder.imageView = (RoundedImageView) view.findViewById(R.id.listview1_icon);
            holder.textView = (TextView) view.findViewById(R.id.listview1_intro);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource((Integer) hashMap.get("icon"));
        holder.textView.setText(hashMap.get("intro").toString());
        return view;
    }

    private class ViewHolder {
        private RoundedImageView imageView;
        private TextView textView;
    }
}
