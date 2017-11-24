package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.adapter.VipPhotoListViewAdapter;
import com.weiye.data.PhotoBean;
import com.weiye.data.VipClassPhotoBean;
import com.weiye.myview.MyListView;
import com.weiye.photoshow.ImagePagerActivity;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoLinearLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
@SuppressLint({"NewApi", "ValidFragment"})
public class PhotoFragment extends Fragment {
    private MyListView listView;
    private List<PhotoBean.DataBean> list;
    private String userID, userType;
    private TextView noPhoto;
    private AutoLinearLayout main20;
    private SharedPreferences sharedPreferences;
    private int myevent;
    private String gid;

    public PhotoFragment(int myevent) {
        this.myevent = myevent;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        gid = sharedPreferences.getString("ggid", "未知");

        listView = (MyListView) view.findViewById(R.id.photofragment_listview);
        noPhoto = (TextView) view.findViewById(R.id.noPhoto);
        main20 = (AutoLinearLayout) view.findViewById(R.id.main20);
        if (myevent == 10) {
            visitPhoto();
        } else {
                vipVisit();

        }

        return view;
    }

    //TODO 公园点击全部数据
    private void visitPhoto() {
        main20.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/isphotoVideo");
        params.addBodyParameter("type", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main20.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                PhotoBean bean = gson.fromJson(result, PhotoBean.class);
                if (bean.getCode() == 1000) {
                    noPhoto.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    list = bean.getData();
                    listView.setAdapter(new SubPhotoListViewAdapter(list, getActivity()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            PhotoBean.DataBean dataBean = (PhotoBean.DataBean) adapterView.getItemAtPosition(i);
                            List<String> picture = dataBean.getPurl();
                            Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
                            intent.putStringArrayListExtra("photoarr", (ArrayList<String>) picture);
                            startActivity(intent);
                        }
                    });
                } else {
                    noPhoto.setVisibility(View.VISIBLE);
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
                main20.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                PhotoBean bean = gson.fromJson(result, PhotoBean.class);
                if (bean.getCode() == 1000) {
                    noPhoto.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    list = bean.getData();
                    listView.setAdapter(new SubPhotoListViewAdapter(list, getActivity()));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            PhotoBean.DataBean dataBean = (PhotoBean.DataBean) adapterView.getItemAtPosition(i);
                            List<String> picture = dataBean.getPurl();
                            Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
                            intent.putStringArrayListExtra("photoarr", (ArrayList<String>) picture);
                            startActivity(intent);
                        }
                    });
                } else {
                    noPhoto.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    //TODO 会员点击我的班级数据
    private void vipVisit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/gradeVp");
        params.addBodyParameter("gid", gid);
        params.addBodyParameter("type", "1");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main20.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                final VipClassPhotoBean bean = gson.fromJson(result, VipClassPhotoBean.class);
                if (bean.getCode() == 3000) {
                        noPhoto.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        listView.setAdapter(new VipPhotoListViewAdapter(bean.getData(), getActivity()));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                List<String> picture = bean.getData().get(i).getPurl();
                                Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
                                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) picture);
                                startActivity(intent);
                            }
                        });

                } else {
                    noPhoto.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
