package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.ManagerBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class AuditionAdapter extends BaseAdapter{
    private List<ManagerBean.DataBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public AuditionAdapter(Activity activity,List<ManagerBean.DataBean> list) {
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
        ManagerBean.DataBean bean=list.get(position);
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.auditionitem,null);
            holder.name= (TextView) convertView.findViewById(R.id.auditionItemName);
            holder.sex= (TextView) convertView.findViewById(R.id.auditionItemSex);
            holder.age= (TextView) convertView.findViewById(R.id.auditionItemAge);
            holder.tel= (TextView) convertView.findViewById(R.id.auditionItemTel);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(bean.getBabyname());
        holder.tel.setText(bean.getPhone());
        holder.age.setText(bean.getOage());
        holder.sex.setText(bean.getSex());
        return convertView;
    }
    private class ViewHolder{
        private TextView name,sex,age,tel;
    }
}
