package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.ParkBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.AppearanceActivity;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/13.
 */
public class ListView_1_Adapter extends BaseAdapter {
    private List<ParkBean.DataBeanX.DataBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public ListView_1_Adapter(List<ParkBean.DataBeanX.DataBean> list, Activity activity) {
        this.list = list;
        this.activity=activity;
        this.layoutInflater = activity.getLayoutInflater();
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
        ViewHolder holder = new ViewHolder();
        ParkBean.DataBeanX.DataBean bean=list.get(i);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listviewitem1, null);
            holder.imageView = (ImageView) view.findViewById(R.id.listview1_icon);
            holder.textView = (TextView) view.findViewById(R.id.listview1_intro);
            holder.textView_time = (TextView) view.findViewById(R.id.listview1_time);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getPic())
                .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,20,0))
                .placeholder(R.mipmap.hui1)
                .error(R.mipmap.hui1)
                .into(holder.imageView);
        holder.textView.setText(bean.getTitle());
        holder.textView_time.setText(bean.getAddtime());
        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView textView, textView_time;
    }
}
