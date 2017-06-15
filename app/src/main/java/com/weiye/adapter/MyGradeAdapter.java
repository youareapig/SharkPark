package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.MyGradeBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/6/12.
 */
public class MyGradeAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<MyGradeBean> list;
    public MyGradeAdapter(Activity activity,List<MyGradeBean> list) {
        this.layoutInflater=activity.getLayoutInflater();
        this.list=list;
    }

    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if (list==null){
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=new ViewHolder();
        MyGradeBean bean=list.get(i);
       if (view==null){
           view=layoutInflater.inflate(R.layout.gradelistitem,null);
           holder.content= (TextView) view.findViewById(R.id.mGradeItemName);
           holder.date= (TextView) view.findViewById(R.id.mGradeItemDay);
           holder.time= (TextView) view.findViewById(R.id.mGradeItemTime);
           holder.week= (TextView) view.findViewById(R.id.mGradeItemWeek);
           view.setTag(holder);
           AutoUtils.autoSize(view);
       }else {
           holder= (ViewHolder) view.getTag();
       }
        holder.content.setText(bean.getContent());
        holder.date.setText(bean.getDate());
        holder.time.setText(bean.getTime());
        holder.week.setText(bean.getWeek());
        return view;
    }
    private class ViewHolder{
        private TextView date,time,week,content;
    }
}
