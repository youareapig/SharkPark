package com.weiye.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.data.TestCurrBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/21.
 */
public class CurriculumGalleryAdapter extends BaseAdapter {
    private List<TestCurrBean> list;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private int selectItem;
    private int count;
    public CurriculumGalleryAdapter(List<TestCurrBean> list, Activity activity) {
        this.list = list;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        count=list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TestCurrBean bean = list.get(i%count);
        view = layoutInflater.inflate(R.layout.curricugalleryitem, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.curricuitem_img);
        TextView textView1 = (TextView) view.findViewById(R.id.curricuitem_text1);
        TextView textView2 = (TextView) view.findViewById(R.id.curricuitem_text2);
        AutoUtils.autoSize(view);
        imageView.setImageResource(bean.getIcon());
        textView1.setText(bean.getText1());
        textView2.setText(bean.getText2());
        if (selectItem == i%count) {
            ViewGroup.MarginLayoutParams params=new ViewGroup.MarginLayoutParams(imageView.getLayoutParams());
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(params);
            layoutParams.height=220;
            layoutParams.width=220;
            imageView.setLayoutParams(layoutParams);
            textView1.setTextSize(TypedValue.COMPLEX_UNIT_PX,50);
            textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX,38);
            textView1.setAlpha(1);
            textView2.setAlpha(1);
            //view.setLayoutParams(new Gallery.LayoutParams(480, 360));
        } else {
            //view.setLayoutParams(new Gallery.LayoutParams(240, 180));
        }
        return view;
    }
}
