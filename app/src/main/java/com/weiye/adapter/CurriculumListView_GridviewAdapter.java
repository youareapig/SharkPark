package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.KCBBean;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListView_GridviewAdapter extends BaseAdapter {
    private List<KCBBean.DataBean.ChildrenBean.CourseBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    public CurriculumListView_GridviewAdapter(Activity activity, List<KCBBean.DataBean.ChildrenBean.CourseBean> list) {
        this.layoutInflater = activity.getLayoutInflater();
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
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
        KCBBean.DataBean.ChildrenBean.CourseBean bean = list.get(i);
        holder = new ViewHolder();
        if (view == null) {
            view = layoutInflater.inflate(R.layout.curriculumlistviewgridviewitem, null);
            holder.textView = (TextView) view.findViewById(R.id.curriculumlistviewgridviewitem_text);
            holder.textView1= (TextView) view.findViewById(R.id.curriculumlistviewgridviewitem_text1);
            holder.itemGrade= (AutoRelativeLayout) view.findViewById(R.id.itemGrade);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(bean.getConame());
        holder.textView1.setText(bean.getCoage());
        if (bean.getIscheckd()==1) {
            holder.itemGrade.setBackgroundResource(R.drawable.classbook1);
            //holder.textView.setClickable(true);
        } else {
            holder.itemGrade.setBackgroundResource(R.drawable.classbook);
            //holder.textView.setClickable(false);
        }
        return view;
    }



    private class ViewHolder {
        private TextView textView,textView1;
        private AutoRelativeLayout itemGrade;
    }



}
