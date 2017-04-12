package com.weiye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beiing.roundimage.RoundImageView;
import com.weiye.zl.R;

import java.util.List;

/**
 * Created by DELL on 2017/4/12.
 */
public class RecyclerAdpter extends RecyclerView.Adapter {
    private List<Integer> list;

    public RecyclerAdpter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.imageView.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private RoundImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (RoundImageView) itemView.findViewById(R.id.recycle_item_image);
        }
    }
}
