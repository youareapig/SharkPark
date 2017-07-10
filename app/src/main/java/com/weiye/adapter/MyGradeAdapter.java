package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.MyCorseBean;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2017/6/12.
 */
public class MyGradeAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<MyCorseBean.DataBean> list;
    public MyGradeAdapter(Activity activity,List<MyCorseBean.DataBean> list) {
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
        MyCorseBean.DataBean bean=list.get(i);
       if (view==null){
           view=layoutInflater.inflate(R.layout.gradelistitem,null);
           holder.content= (TextView) view.findViewById(R.id.mGradeItemName);
           holder.date= (TextView) view.findViewById(R.id.mGradeItemDay);
           holder.time= (TextView) view.findViewById(R.id.mGradeItemTime);
           holder.week= (TextView) view.findViewById(R.id.mGradeItemWeek);
           holder.bg= (AutoRelativeLayout) view.findViewById(R.id.myclassbg);
           holder.ri= (TextView) view.findViewById(R.id.ri);
           view.setTag(holder);
           AutoUtils.autoSize(view);
       }else {
           holder= (ViewHolder) view.getTag();
       }
        holder.content.setText(bean.getConame());
        holder.date.setText(myString(bean.getDates()));
        holder.time.setText(bean.getDatename());
        holder.week.setText(bean.getWeek());
        if (bean.getIspast()==0){
            holder.bg.setBackgroundResource(R.drawable.classbook2);
            holder.date.setTextColor(view.getContext().getResources().getColor(R.color.hui));
            holder.time.setTextColor(view.getContext().getResources().getColor(R.color.hui));
            holder.week.setTextColor(view.getContext().getResources().getColor(R.color.hui));
            holder.content.setTextColor(view.getContext().getResources().getColor(R.color.hui));
            holder.ri.setTextColor(view.getContext().getResources().getColor(R.color.hui));
        }else {
            holder.bg.setBackgroundResource(R.drawable.classbook);
            holder.date.setTextColor(view.getContext().getResources().getColor(R.color.yes));
            holder.time.setTextColor(view.getContext().getResources().getColor(R.color.yes));
            holder.week.setTextColor(view.getContext().getResources().getColor(R.color.yes));
            holder.content.setTextColor(view.getContext().getResources().getColor(R.color.yes));
            holder.ri.setTextColor(view.getContext().getResources().getColor(R.color.yes));
        }

        return view;
    }
    private class ViewHolder{
        private TextView date,time,week,content,ri;
        private AutoRelativeLayout bg;
    }
    private String myString(String string) {
        String[] s = string.split("-");
        return s[2];
    }


}
