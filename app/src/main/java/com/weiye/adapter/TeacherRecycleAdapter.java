package com.weiye.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.TeacherBean;
import com.weiye.data.TestBean;
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
    private List<TeacherBean.RowsBean> list;

    public TeacherRecycleAdapter(List<TeacherBean.RowsBean> list) {
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
        final TeacherBean.RowsBean bean=list.get(position);
        viewHolder.textView.setText(bean.getZSXM());
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getTXLJ(),viewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), TeacherStyleActivity.class);
                intent.putExtra("teacherID",bean.getRYID());
                intent.putExtra("teacherName",bean.getZSXM());
                intent.putExtra("teacherProduct",bean.getMS());
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
