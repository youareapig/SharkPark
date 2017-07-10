package com.weiye.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/13.
 */
public class SpinnerAdpter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<String> list;
    public SpinnerAdpter( Activity activity,List<String> list) {
        this.inflater = activity.getLayoutInflater();
        this.list=list;

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
        if (view == null) {
            view = inflater.inflate(R.layout.spinneritem, null);
            holder.textView = (TextView) view.findViewById(R.id.spinnerText);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(list.get(i));
        return view;
    }

    private class ViewHolder {
        private TextView textView;
    }

}
