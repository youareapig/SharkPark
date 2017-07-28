package com.weiye.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.IndexBean;
import com.weiye.mycourse.MyCoruseActivity;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.third.BaseAdapterHelper;
import com.weiye.third.Gallery;
import com.weiye.third.QuickPagerAdapter;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.CourseActivity;
import com.weiye.zl.R;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Child_Fragment extends Fragment {
    private Gallery mGallery;
    private QuickPagerAdapter<IndexBean.DataBean> quickPagerAdapter;
    private List<IndexBean.DataBean> mList;
    private CustomProgressDialog customProgressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView textViewTag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment, container, false);
        mGallery = (Gallery) view.findViewById(R.id.myGallery);
        textViewTag = (TextView) view.findViewById(R.id.mytag);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //TODO 23表示Android 6.0
        if (Build.VERSION.SDK_INT >= 23) {
            textViewTag.setVisibility(View.VISIBLE);
        } else {
            textViewTag.setVisibility(View.GONE);
        }
        index();
        return view;
    }

    private void index() {
        customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        mGallery.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/index");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mGallery.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                IndexBean bean = gson.fromJson(result, IndexBean.class);
                mList = bean.getData();
                if (bean.getCode() == 1000) {
                    quickPagerAdapter = new QuickPagerAdapter<IndexBean.DataBean>(getActivity(), R.layout.galleryitem, mList) {
                        @Override
                        protected void convertView(BaseAdapterHelper helper, final IndexBean.DataBean item) {
                            ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + item.getSbpic(), (ImageView) helper.getView(R.id.galleryitem_img));
                            helper.setText(R.id.galleryitem_title, item.getSbtitle());
                            helper.setImageOnClickListener(R.id.galleryitem_img, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), CourseActivity.class);
                                    editor.putString("gradename", item.getSbtitle());
                                    editor.putString("indexID", item.getSbid() + "");
                                    editor.commit();
                                    startActivity(intent);
                                }
                            });
                        }
                    };
                    mGallery.setAdapter(quickPagerAdapter);
                } else {
                    Toast.makeText(getActivity(), "暂无更多数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

}
