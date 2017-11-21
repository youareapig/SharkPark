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
import com.weiye.data.PhotoBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/14.
 */
public class SubPhotoListViewAdapter extends BaseAdapter{
    private List<PhotoBean.DataBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public SubPhotoListViewAdapter(List<PhotoBean.DataBean> list, Activity activity) {
        this.list = list;
        this.layoutInflater=activity.getLayoutInflater();
        this.activity=activity;
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
        PhotoBean.DataBean rowsBean=list.get(i);
        if (view==null){
            view=layoutInflater.inflate(R.layout.subphotolistviewitem,null);
            holder.imageView= (ImageView) view.findViewById(R.id.subphotolistviewItem_img);
            holder.textView= (TextView) view.findViewById(R.id.subphtolistviewItem_text);
            holder.textViewnum= (TextView) view.findViewById(R.id.subphtolistviewItem_number);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+rowsBean.getPurl().get(0))
                .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                .placeholder(R.mipmap.hui)
                .error(R.mipmap.hui)
                .into(holder.imageView);
        holder.textView.setText(rowsBean.getTitle());
        holder.textViewnum.setText(rowsBean.getPurl().size()+"");
        return view;
    }
    private class ViewHolder{
        private ImageView imageView;
        private TextView textView,textViewnum;
    }
}
