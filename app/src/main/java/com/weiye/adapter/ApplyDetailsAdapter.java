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

public class ApplyDetailsAdapter extends BaseAdapter{
    private List<String> list=new ArrayList<>();
    private Activity activity;
    private LayoutInflater layoutInflater;

    public ApplyDetailsAdapter(Activity activity) {
        this.layoutInflater=activity.getLayoutInflater();
        list.add("黄欧1");
        list.add("黄欧2");
        list.add("黄欧3");
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
            convertView=layoutInflater.inflate(R.layout.applydetailsitem,null);
            holder.month= (TextView) convertView.findViewById(R.id.applydetailsItemMonth);
            holder.day= (TextView) convertView.findViewById(R.id.applydetailsItemDay);
            holder.time= (TextView) convertView.findViewById(R.id.applydetailsItemTime);
            holder.name= (TextView) convertView.findViewById(R.id.applydetailsItemName);
            holder.age= (TextView) convertView.findViewById(R.id.applydetailsItemAge);
            holder.sex= (TextView) convertView.findViewById(R.id.applydetailsItemSex);
            holder.tel= (TextView) convertView.findViewById(R.id.applydetailsItemTel);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position));
        return convertView;
    }
    private class ViewHolder{
        private TextView month,day,time,name,age,sex,tel;
    }
}
