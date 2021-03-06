package com.weiye.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.HuodongBean;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.HashMap;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by DELL on 2017/4/13.
 */
public class ActivitiesGridAdpter extends BaseAdapter {
    private List<HuodongBean.DataBean> list;
    private Activity activity;
    private LayoutInflater inflater;

    public ActivitiesGridAdpter(List<HuodongBean.DataBean> list, Activity activity) {
        this.list = list;
        this.activity=activity;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        HuodongBean.DataBean bean = list.get(i);
        if (view == null) {
            view = inflater.inflate(R.layout.activitisegriditem, null);
            holder.imageView = (ImageView) view.findViewById(R.id.activities_item_img);
            holder.textView = (TextView) view.findViewById(R.id.activities_item_text);
            holder.huodongPlay = (ImageView) view.findViewById(R.id.huodongplay);
            holder.textViewNumber= (TextView) view.findViewById(R.id.number2);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (bean.getIsvideo().equals("0")) {
            Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getPhotos().get(0))
                    .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                    .placeholder(R.mipmap.hui)
                    .error(R.mipmap.hui)
                    .into(holder.imageView);
            holder.huodongPlay.setVisibility(View.GONE);
            holder.textViewNumber.setText(bean.getPhotos().size()+"");
        } else {
            //Bitmap bitmap = createVideoThumbnail(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getVurl(), 1000, 500);
            Glide.with(activity).load(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getBjimg())
                    .bitmapTransform(new CenterCrop(activity),new RoundedCornersTransformation(activity,8,0))
                    .placeholder(R.mipmap.hui)
                    .error(R.mipmap.hui)
                    .into(holder.imageView);
            //holder.imageView.setImageBitmap(bitmap);
            holder.huodongPlay.setVisibility(View.VISIBLE);
        }
        holder.textView.setText(bean.getTitle());
        return view;
    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView textView,textViewNumber;
        private ImageView huodongPlay;
    }

    //TODO 抓取视频的第一帧作为封面
    private Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
        } catch (RuntimeException ex) {
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
}
