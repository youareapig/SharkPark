package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.YishangBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class YishangAdapter extends BaseAdapter{
    private List<YishangBean.DataBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public YishangAdapter(List<YishangBean.DataBean> list, Activity activity) {
        this.list=list;
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
        YishangBean.DataBean bean=list.get(position);
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
        holder.content.setText(bean.getConame());
        holder.time.setText(bean.getDatename());
        holder.month.setText(myString(bean.getDates(),1));
        holder.date.setText(myString(bean.getDates(),2));
        return convertView;
    }
    private class ViewHolder{
        private TextView date,time,content,month;
    }
    //TODO 拆分字符串
    private String myString(String string,int n) {
        String[] s = string.split("-");
        return s[n];
    }
}
