package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.adapter.VipVideoListViewAdapter;
import com.weiye.data.VipClassVidioBean;
import com.weiye.data.VideoBean;
import com.weiye.myview.MyListView;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.VedioPlayerActivity;
import com.zhy.autolayout.AutoLinearLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 * <p>
 * 避免打包出现错误
 */
@SuppressLint({"NewApi", "ValidFragment"})
public class VideoFragment extends Fragment {
    private MyListView listView;
    private List<VideoBean.DataBean> list;
    private String userID, gID, userType;
    private TextView noShipin;
    private AutoLinearLayout main21;
    private SharedPreferences sharedPreferences;
    private int myevent;

    public VideoFragment(int myevent) {
        this.myevent = myevent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videofragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        gID = sharedPreferences.getString("ggid", "未知");
        listView = (MyListView) view.findViewById(R.id.videofragment_listview);
        noShipin = (TextView) view.findViewById(R.id.noVideo);
        main21 = (AutoLinearLayout) view.findViewById(R.id.main21);
        if (myevent == 10) {
            visitVideo();
        } else {
                vipVisit();
        }
        return view;
    }


    //TODO 公园点击全部数据
    private void visitVideo() {
        main21.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/isphotoVideo");
        params.addBodyParameter("type", "2");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main21.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                VideoBean bean = gson.fromJson(result, VideoBean.class);
                if (bean.getCode() == 1000) {
                    noShipin.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    list = bean.getData();
                    listView.setAdapter(new SubVideoListViewAdapter(list, getActivity()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            VideoBean.DataBean bean1 = (VideoBean.DataBean) adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(getActivity(), VedioPlayerActivity.class);
                            intent.putExtra("videoUrl", bean1.getVurl());
                            startActivity(intent);
                        }
                    });
                } else {
                    noShipin.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
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

            }

            @Override
            public boolean onCache(String result) {
                main21.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                VideoBean bean = gson.fromJson(result, VideoBean.class);
                if (bean.getCode() == 1000) {
                    noShipin.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    list = bean.getData();
                    listView.setAdapter(new SubVideoListViewAdapter(list, getActivity()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            VideoBean.DataBean bean1 = (VideoBean.DataBean) adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(getActivity(), VedioPlayerActivity.class);
                            intent.putExtra("videoUrl", bean1.getVurl());
                            startActivity(intent);
                        }
                    });
                } else {
                    noShipin.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    //TODO 会员点击我的班级数据
    private void vipVisit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/gradeVp");
        params.addBodyParameter("gid", gID);
        params.addBodyParameter("type", "2");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main21.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                final VipClassVidioBean bean = gson.fromJson(result, VipClassVidioBean.class);
                if (bean.getCode() == 3000) {
                    noShipin.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(new VipVideoListViewAdapter(bean.getData(), getActivity()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), VedioPlayerActivity.class);
                            intent.putExtra("videoUrl", bean.getData().get(i).getVurl());
                            startActivity(intent);
                        }
                    });
                } else {
                    noShipin.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
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

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }



}
