package com.weiye.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weiye.data.BabyBean;
import com.weiye.zl.MyBabyActivity;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by DELL on 2017/8/30.
 */

public class ChooseBabyAdapter extends RecyclerView.Adapter {
    private List<BabyBean.DataBean> list;
    private Activity activity;

    public ChooseBabyAdapter(List<BabyBean.DataBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.babyitem, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BabyBean.DataBean info = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.babyName.setText(info.getTruename());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), MyBabyActivity.class);
                intent.putExtra("babyId",info.getId()+"");
                v.getContext().startActivity(intent);
                EventBus.getDefault().post("关闭对话框");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView babyName;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            babyName = (TextView) itemView.findViewById(R.id.babyname);
        }
    }
}
