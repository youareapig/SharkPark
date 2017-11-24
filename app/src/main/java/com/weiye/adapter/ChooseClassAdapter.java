package com.weiye.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weiye.data.BabyBean;
import com.weiye.data.TeacherClassBean;
import com.weiye.zl.MyBabyActivity;
import com.weiye.zl.R;
import com.weiye.zl.SubjectActivity;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by DELL on 2017/8/30.
 */

public class ChooseClassAdapter extends RecyclerView.Adapter {
    private List<TeacherClassBean.DataBean> list;
    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public ChooseClassAdapter(List<TeacherClassBean.DataBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("UserTag", activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.babyitem, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TeacherClassBean.DataBean info = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.babyName.setText(info.getGname());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), SubjectActivity.class);
                editor.putString("ggid",info.getId()+"");
                editor.commit();
                v.getContext().startActivity(intent);
                EventBus.getDefault().post("eventbus");
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
