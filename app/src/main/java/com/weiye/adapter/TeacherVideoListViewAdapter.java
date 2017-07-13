package com.weiye.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.TeacherManageVideoBean;
import com.weiye.data.VipClassVidioBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class TeacherVideoListViewAdapter extends BaseAdapter{
    private List<TeacherManageVideoBean.DataBean.PvBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public TeacherVideoListViewAdapter(List<TeacherManageVideoBean.DataBean.PvBean> list, Activity activity) {
        this.list = list;
        this.layoutInflater=activity.getLayoutInflater();
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
        TeacherManageVideoBean.DataBean.PvBean bean=list.get(i);
        if (view==null){
            view=layoutInflater.inflate(R.layout.subvideolistviewitem,null);
            holder.imageView= (RoundedImageView) view.findViewById(R.id.subvideolistviewItem_img);
            holder.textView= (TextView) view.findViewById(R.id.subvideolistviewItem_text);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(bean.getVtitle());
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getVimg(),holder.imageView);
        Log.d("tag","视频封面"+bean.getVimg());
        return view;
    }
    private class ViewHolder{
        private RoundedImageView imageView;
        private TextView textView;
    }
}
