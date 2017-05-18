package com.weiye.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.TestBean;
import com.weiye.myview.MyGridView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListView_GridviewAdapter extends BaseAdapter{
    private List<TestBean> list=new ArrayList<>();
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private TestBean testBean1,testBean2,testBean3,testBean4;
    public CurriculumListView_GridviewAdapter(Activity activity) {
        this.layoutInflater=activity.getLayoutInflater();
        testBean1=new TestBean("植物大战僵尸",true);
        testBean2=new TestBean("植物大战僵尸",false);
        testBean3=new TestBean("植物大战僵尸",false);
        testBean4=new TestBean("植物大战僵尸",false);
        list.add(testBean1);
        list.add(testBean2);
        list.add(testBean3);
        list.add(testBean4);
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
        holder=new ViewHolder();
        TestBean bean=list.get(i);
        if (view==null){
            view=layoutInflater.inflate(R.layout.curriculumlistviewgridviewitem,null);
            holder.textView= (TextView) view.findViewById(R.id.curriculumlistviewgridviewitem_text);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(bean.getString());
        if (bean.isaBoolean()){
            holder.textView.setBackgroundResource(R.drawable.classbook1);
        }else {
            holder.textView.setBackgroundResource(R.drawable.classbook);
        }
        return view;
    }
    private class ViewHolder{
        private TextView textView;
    }

}
