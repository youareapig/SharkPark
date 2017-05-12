package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.KTFCBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class GalleryAdapter extends BaseAdapter{
    private List<KTFCBean.RowsBean> list;
    private Activity context;
    private int count;
    private LayoutInflater layoutInflater;
    public GalleryAdapter(List<KTFCBean.RowsBean> list, Activity context) {
        this.list = list;
        this.layoutInflater=context.getLayoutInflater();
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
        KTFCBean.RowsBean bean=list.get(i%count);
        view=layoutInflater.inflate(R.layout.ktfc_item,null);
        TextView textView= (TextView) view.findViewById(R.id.ktfc_item_text);
        RoundedImageView imageView= (RoundedImageView) view.findViewById(R.id.ktfc_item_img);
        AutoUtils.autoSize(view);
        textView.setText(bean.getFCMS());
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getTXLJ(),imageView);
        return view;
    }
}
