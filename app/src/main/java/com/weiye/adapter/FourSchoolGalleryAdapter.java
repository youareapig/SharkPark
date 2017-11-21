package com.weiye.adapter;

import android.app.Activity;
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
import com.weiye.data.IndexBean;
import com.weiye.data.Park_1Bean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/14.
 */
public class FourSchoolGalleryAdapter extends BaseAdapter {
    private List<Park_1Bean.DataBean> list;
    private LayoutInflater inflater;
    private int count;
    private Activity activity;
    public FourSchoolGalleryAdapter(List<Park_1Bean.DataBean> list, Activity context) {
        this.list = list;
        this.inflater = context.getLayoutInflater();
        this.activity=context;
    }

    @Override
    public int getCount() {
        count=list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i%count);
    }

    @Override
    public long getItemId(int i) {
        return i%count;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Park_1Bean.DataBean bean=list.get(i%count);
        view = inflater.inflate(R.layout.fourschoolitem, null);
        TextView textView = (TextView) view.findViewById(R.id.fourschoolitem_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.fourschoolitem_img);
        AutoUtils.autoSize(view);
        textView.setText(bean.getUname());
        Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getHeadpic())
                .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                .placeholder(R.mipmap.hui5)
                .error(R.mipmap.hui5)
                .into(imageView);
        return view;
    }
}
