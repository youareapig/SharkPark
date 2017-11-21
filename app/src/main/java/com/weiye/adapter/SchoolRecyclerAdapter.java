package com.weiye.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.AllHuodongBean;
import com.weiye.data.HuodongBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SchoolWeiChatActivity;
import com.weiye.zl.SchoolHtmlActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/14.
 */
public class SchoolRecyclerAdapter extends RecyclerView.Adapter {
    private List<AllHuodongBean.DataBean> list;
    private Activity activity;
    public SchoolRecyclerAdapter(List<AllHuodongBean.DataBean> list,Activity activity) {
        this.list = list;
        this.activity=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolrecycleritem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final AllHuodongBean.DataBean bean = list.get(position+1);
        Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getBjimg())
                .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                .placeholder(R.mipmap.hui3)
                .error(R.mipmap.hui3)
                .into(viewHolder.imageView);
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
            return list.size()-1;
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
