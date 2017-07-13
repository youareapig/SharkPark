package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.YichangBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class YiChangAdapter extends BaseAdapter{
    private List<YichangBean.DataBean.CourseBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public YiChangAdapter(Activity activity,List<YichangBean.DataBean.CourseBean> list) {
        this.layoutInflater=activity.getLayoutInflater();
        this.list=list;

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
        YichangBean.DataBean.CourseBean bean=list.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.yichangitem,null);
            holder.date= (TextView) convertView.findViewById(R.id.yichangItemDay);
            holder.time= (TextView) convertView.findViewById(R.id.yichangItemTime);
            holder.content= (TextView) convertView.findViewById(R.id.yichangItemName);
            holder.month= (TextView) convertView.findViewById(R.id.yichangItemMonth);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.content.setText(bean.getConame());
        return convertView;
    }
    private class ViewHolder{
        private TextView date,time,content,month;
    }
}
