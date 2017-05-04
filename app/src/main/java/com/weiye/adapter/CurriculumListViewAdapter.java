package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiye.myview.MyGridView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListViewAdapter extends BaseAdapter{
    private List<String> list=new ArrayList<>();
    private Activity activity;
    private LayoutInflater layoutInflater;

    public CurriculumListViewAdapter(Activity activity) {
        this.layoutInflater=activity.getLayoutInflater();
        list.add("7:00-8:00");
        list.add("8:00-9:00");
        list.add("9:00-10:00");
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
        if (view==null){
            view=layoutInflater.inflate(R.layout.curriculistviewitem,null);
            holder.textView= (TextView) view.findViewById(R.id.curriculistview_item_time);
            holder.myGridView= (MyGridView) view.findViewById(R.id.curriculistview_item_gridview);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i));
        holder.myGridView.setAdapter(new CurriculumListView_GridviewAdapter((Activity) view.getContext()));
        return view;
    }
    private class ViewHolder{
        private TextView textView;
        private MyGridView myGridView;
    }
}
