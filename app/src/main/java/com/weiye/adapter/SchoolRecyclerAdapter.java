package com.weiye.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.AllHuodongBean;
import com.weiye.data.HuodongBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SchoolWeiChatActivity;
import com.weiye.zl.SchoolHtmlActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class SchoolRecyclerAdapter extends RecyclerView.Adapter {
    private List<AllHuodongBean.DataBean> list;

    public SchoolRecyclerAdapter(List<AllHuodongBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolrecycleritem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final AllHuodongBean.DataBean bean = list.get(position);
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getBjimg(), viewHolder.imageView);
        viewHolder.textView.setText(bean.getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = null;
                if (bean.getIswei().equals("1")) {
                    intent = new Intent(view.getContext(), SchoolWeiChatActivity.class);
                    intent.putExtra("wuxin", bean.getWeurl());
                } else {
                    intent = new Intent(view.getContext(), SchoolHtmlActivity.class);
                    intent.putExtra("htmls", bean.getContent());
                }
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.schoolrecycleritem_img);
            textView = (TextView) itemView.findViewById(R.id.schoolrecycleritem_content);
        }
    }
}
