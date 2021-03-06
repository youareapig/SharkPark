package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weiye.data.CourseBeans;
import com.weiye.data.KCBBean;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CourseAdpters extends BaseAdapter {
    private List<CourseBeans.DataBeanX.DataBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    public CourseAdpters(Activity activity, List<CourseBeans.DataBeanX.DataBean> list) {
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
        CourseBeans.DataBeanX.DataBean bean = list.get(i);
        holder = new ViewHolder();
        if (view == null) {
            view = layoutInflater.inflate(R.layout.manageapplyitem, null);
            holder.textView = (TextView) view.findViewById(R.id.manageapplyitemName);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(bean.getConame());

        return view;
    }



    private class ViewHolder {
        private TextView textView;
    }



}
