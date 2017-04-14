package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class SubPhotoListViewAdapter extends BaseAdapter{
    private List<HashMap<String,Object>> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public SubPhotoListViewAdapter(List<HashMap<String, Object>> list, Activity activity) {
        this.list = list;
        this.layoutInflater=activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list!=null){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=new ViewHolder();
        HashMap hashMap=list.get(i);
        if (view==null){
            view=layoutInflater.inflate(R.layout.subphotolistviewitem,null);
            holder.imageView= (ImageView) view.findViewById(R.id.subphotolistviewItem_img);
            holder.textView= (TextView) view.findViewById(R.id.subphtolistviewItem_text);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource((Integer) hashMap.get("icon"));
        holder.textView.setText(hashMap.get("str").toString());
        return view;
    }
    private class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
