package com.weiye.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.SubjectBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.TeacherStyleActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DELL on 2017/4/14.
 */
public class TeacherRecycleAdapter extends RecyclerView.Adapter{
    private List<SubjectBean.DataBean.TeacherBean> list;

    public TeacherRecycleAdapter(List<SubjectBean.DataBean.TeacherBean> list) {
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
        final SubjectBean.DataBean.TeacherBean bean=list.get(position);
        viewHolder.textView.setText(bean.getNickname());
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getHeadpic(),viewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), TeacherStyleActivity.class);
                intent.putExtra("teacherID",bean.getTid());
                view.getContext().startActivity(intent);
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
        private TextView textView;
        private CircleImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            textView = (TextView) itemView.findViewById(R.id.teacheritem_name);
            imageView= (CircleImageView) itemView.findViewById(R.id.teacheritem_img);
        }
    }
}
