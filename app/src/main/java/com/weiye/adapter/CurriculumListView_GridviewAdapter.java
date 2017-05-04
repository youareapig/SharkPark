package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.myview.MyGridView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListView_GridviewAdapter extends BaseAdapter{
    private List<String> list=new ArrayList<>();
    private Activity activity;
    private LayoutInflater layoutInflater;

    public CurriculumListView_GridviewAdapter(Activity activity) {
        this.layoutInflater=activity.getLayoutInflater();
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");
        list.add("植物大战僵尸");

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
            view=layoutInflater.inflate(R.layout.curriculumlistviewgridviewitem,null);
            holder.textView= (TextView) view.findViewById(R.id.curriculumlistviewgridviewitem_text);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i));
        return view;
    }
    private class ViewHolder{
        private TextView textView;
    }
}
