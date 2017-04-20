package com.weiye.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiye.data.TestBean;
import com.weiye.zl.R;
import com.weiye.zl.TeacherStyleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class SchoolRecyclerAdapter extends RecyclerView.Adapter{
    private List<Integer> list;

    public SchoolRecyclerAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolrecycleritem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.imageView.setImageResource(list.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.schoolrecycleritem_img);
        }
    }
}
