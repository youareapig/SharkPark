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
    private List<HuodongBean.DataBean> list;
    private Activity activity;
    private LayoutInflater inflater;

    public ActivitiesGridAdpter(List<HuodongBean.DataBean> list, Activity activity) {
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
        HuodongBean.DataBean bean=list.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.activitisegriditem, null);
            holder.imageView = (RoundedImageView) view.findViewById(R.id.activities_item_img);
            holder.textView= (TextView) view.findViewById(R.id.activities_item_text);
            holder.huodongPlay= (ImageView) view.findViewById(R.id.huodongplay);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (bean.getIsvideo().equals("0")) {
            ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getBjimg(), holder.imageView);
            holder.huodongPlay.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getBjimg(), holder.imageView);
            holder.huodongPlay.setVisibility(View.VISIBLE);
        }
        holder.textView.setText(bean.getTitle());
        return view;
    }

    private class ViewHolder {
        private RoundedImageView imageView;
        private TextView textView;
        private ImageView huodongPlay;
    }
}
