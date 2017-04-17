package com.weiye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiye.data.TestBean;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class TeacherRecycleAdapter extends RecyclerView.Adapter{
    private List<TestBean> list;

    public TeacherRecycleAdapter(List<TestBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacheritem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        TestBean bean=list.get(position);
        viewHolder.textView.setText(bean.getName());
        viewHolder.imageView.setImageResource(bean.getImg());
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textView = (TextView) itemView.findViewById(R.id.teacheritem_name);
            imageView= (ImageView) itemView.findViewById(R.id.teacheritem_img);
        }
    }
}
