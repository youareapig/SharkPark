package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class YishangAdapter extends BaseAdapter{
    private List<String> list=new ArrayList<>();
    private Activity activity;
    private LayoutInflater layoutInflater;

    public YishangAdapter(Activity activity) {
        this.layoutInflater=activity.getLayoutInflater();
        list.add("已上1");
        list.add("已上2");
        list.add("已上3");
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list!=null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.yishangitem,null);
            holder.date= (TextView) convertView.findViewById(R.id.yishangItemDay);
            holder.time= (TextView) convertView.findViewById(R.id.yishangItemTime);
            holder.content= (TextView) convertView.findViewById(R.id.yishangItemName);
            holder.month= (TextView) convertView.findViewById(R.id.yishangItemMonth);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.content.setText(list.get(position));
        return convertView;
    }
    private class ViewHolder{
        private TextView date,time,content,month;
    }
}
