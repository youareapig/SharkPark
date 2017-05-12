package com.weiye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.HuodongBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/13.
 */
public class ActivitiesGridAdpter extends BaseAdapter {
    private List<HuodongBean.RowsBean> list;
    private Activity activity;
    private LayoutInflater inflater;

    public ActivitiesGridAdpter(List<HuodongBean.RowsBean> list, Activity activity) {
        this.list = list;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        HuodongBean.RowsBean bean=list.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.activitisegriditem, null);
            holder.imageView = (RoundedImageView) view.findViewById(R.id.activities_item_img);
            holder.textView= (TextView) view.findViewById(R.id.activities_item_text);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getTXLJ(),holder.imageView);
        holder.textView.setText(bean.getHDMS());
        return view;
    }

    private class ViewHolder {
        private RoundedImageView imageView;
        private TextView textView;
    }
}
