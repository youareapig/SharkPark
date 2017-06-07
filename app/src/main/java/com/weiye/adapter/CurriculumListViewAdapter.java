package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiye.data.KCBBean;
import com.weiye.data.TestBean;
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
    private List<KCBBean.RowsBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    public CurriculumListViewAdapter(Activity activity,List<KCBBean.RowsBean> list) {
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
        KCBBean.RowsBean bean=list.get(i);
        holder=new ViewHolder();
        if (view==null){
            view=layoutInflater.inflate(R.layout.curriculistviewitem,null);
            holder.textViewStart= (TextView) view.findViewById(R.id.curriculistview_item_timeStrar);
            holder.textViewEnd= (TextView) view.findViewById(R.id.curriculistview_item_timeEnd);
            holder.myGridView= (MyGridView) view.findViewById(R.id.curriculistview_item_gridview);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textViewStart.setText(myString(bean.getKSSJ()));
        holder.textViewEnd.setText(myString(bean.getJSSJ()));

        final CurriculumListView_GridviewAdapter gridviewAdapter=new CurriculumListView_GridviewAdapter((Activity) view.getContext());
        holder.myGridView.setAdapter(gridviewAdapter);
        holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TestBean bean= (TestBean) adapterView.getItemAtPosition(i);
                if (bean.isaBoolean()){
                    bean.setaBoolean(false);
                }else {
                    bean.setaBoolean(true);
                }
                gridviewAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    private class ViewHolder{
        private TextView textViewStart,textViewEnd;
        private MyGridView myGridView;
    }
    //TODO 拆分字符串
    private String myString(String string){
        String[] s=string.split(" ");
        return s[1];
    }
}
