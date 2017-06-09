package com.weiye.schoolTabFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.SchoolRecyclerAdapter;
import com.weiye.data.HuodongBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SchoolImageActivity;
import com.weiye.zl.SchoolVideoActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/20.
 */
public class SchoolActivity_1 extends Fragment {
    private RecyclerView schoolRecycler;
    private List<HuodongBean.RowsBean> list;
    private ScrollView scrollView;
    private RoundedImageView mImage;
    private CustomProgressDialog customProgressDialog;
    private ImageView videoLogo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schoolactivity1, container, false);
        schoolRecycler = (RecyclerView) view.findViewById(R.id.schoolRecycler);
        scrollView = (ScrollView) view.findViewById(R.id.mScroll);
        mImage = (RoundedImageView) view.findViewById(R.id.mImage);
        videoLogo= (ImageView) view.findViewById(R.id.videoplayerlogo);
        scrollView.smoothScrollTo(0, 20);
        visit();

        return view;
    }

    private void visit() {
        customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_XXHDDataService.ashx?op=getTAB_XXHD");
        params.addBodyParameter("start", "0");
        params.addBodyParameter("ZT", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag","---------------------"+result);
                Gson gson = new Gson();
                HuodongBean bean = gson.fromJson(result, HuodongBean.class);
                list = bean.getRows();
                if (list.get(0).getBJSFSP().equals("0")) {
                    videoLogo.setVisibility(View.GONE);
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+list.get(0).getTXLJ(),mImage);
                } else {
                    videoLogo.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+list.get(0).getBJTXLJ(),mImage);
                }

                schoolRecycler.setAdapter(new SchoolRecyclerAdapter(list));
                schoolRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
                mImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = null;
                        if (list.get(0).getBJSFSP().equals("0")) {
                            intent = new Intent(view.getContext(), SchoolImageActivity.class);
                            intent.putExtra("txdz", list.get(0).getTXLJ());
                            intent.putExtra("hdms", list.get(0).getHDMS());
                        } else {
                            intent = new Intent(view.getContext(), SchoolVideoActivity.class);
                            intent.putExtra("txdz", list.get(0).getTXLJ());
                            intent.putExtra("spdz", list.get(0).getBJTXLJ());
                            intent.putExtra("hdms", list.get(0).getHDMS());
                        }
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
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
