package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.ParkBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/14.
 */
public class GalleryAdapter extends BaseAdapter{
    private List<ParkBean.DataBeanX.PicBean> list;
    private Activity activity;
    private int count;
    private LayoutInflater layoutInflater;
    public GalleryAdapter(List<ParkBean.DataBeanX.PicBean> list, Activity activity) {
        this.list = list;
        this.layoutInflater=activity.getLayoutInflater();
        this.activity=activity;
    }

    @Override
    public int getCount() {
        count=list.size();
        if (list!=null){
            return Integer.MAX_VALUE;
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
        ParkBean.DataBeanX.PicBean bean=list.get(i%list.size());
        view=layoutInflater.inflate(R.layout.ktfc_item,null);
        TextView textView= (TextView) view.findViewById(R.id.ktfc_item_text);
        ImageView imageView= (ImageView) view.findViewById(R.id.ktfc_item_img);
        AutoUtils.autoSize(view);
        Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getUrl())
                .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                .placeholder(R.mipmap.hui2)
                .error(R.mipmap.hui2)
                .into(imageView);
        return view;
    }
}
