package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.adapter.TeacherVideoListViewAdapter;
import com.weiye.adapter.VipVideoListViewAdapter;
import com.weiye.data.TeacherManageVideoBean;
import com.weiye.data.VideoBean;
import com.weiye.data.VipClassVidioBean;
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
public class TeacherVideoFragment extends Fragment {
    private ListView listView;
    private List<VideoBean.DataBean> list;
    private String  userID,gID,userType;
    private TextView noShipin;
    private AutoLinearLayout main21;
    private SharedPreferences sharedPreferences;

    public TeacherVideoFragment(String gID) {
        this.gID = gID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teachervideofragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        gID = sharedPreferences.getString("gid", "未知");

        listView = (ListView) view.findViewById(R.id.videofragment_listview);
        noShipin = (TextView) view.findViewById(R.id.noVideo);
        main21 = (AutoLinearLayout) view.findViewById(R.id.main21);
        Log.d("tag","重新加载了视频-------------"+gID);
        teacherManger();
        return view;
    }


    //TODO 老师班级
    private void teacherManger() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/teacherCenter");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "2");
        params.addBodyParameter("gid", gID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final TeacherManageVideoBean bean = gson.fromJson(result, TeacherManageVideoBean.class);
                if (bean.getCode() == 3000) {
                    noShipin.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    TeacherVideoListViewAdapter adapter=new TeacherVideoListViewAdapter(bean.getData().getPv(), getActivity());
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), VedioPlayerActivity.class);
                            intent.putExtra("videoUrl", bean.getData().getPv().get(i).getVurl());
                            startActivity(intent);
                        }
                    });
                }else {
                    noShipin.setVisibility(View.VISIBLE);
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

    public void teacherManger_1(String userID,String gid) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/teacherCenter");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "2");
        params.addBodyParameter("gid", gid);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final TeacherManageVideoBean bean = gson.fromJson(result, TeacherManageVideoBean.class);
                if (bean.getCode() == 3000) {
                    noShipin.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    TeacherVideoListViewAdapter adapter=new TeacherVideoListViewAdapter(bean.getData().getPv(), getActivity());
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), VedioPlayerActivity.class);
                            intent.putExtra("videoUrl", bean.getData().getPv().get(i).getVurl());
                            startActivity(intent);
                        }
                    });
                }else {
                    noShipin.setVisibility(View.VISIBLE);
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
