package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.adapter.TeacherPhotoListViewAdapter;
import com.weiye.adapter.VipPhotoListViewAdapter;
import com.weiye.data.PhotoBean;
import com.weiye.data.TeacherManagePhotoBean;
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
public class TeacherPhotoFragment extends Fragment {
    private ListView listView;
    private List<PhotoBean.DataBean> list;
    private String userID, userType;
    private TextView noPhoto;
    private AutoLinearLayout main20;
    private SharedPreferences sharedPreferences;
    private String gid;

    public TeacherPhotoFragment(String gid) {
        this.gid = gid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacherphotofragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        gid = sharedPreferences.getString("gid", "未知");
        listView = (ListView) view.findViewById(R.id.photofragment_listview);
        noPhoto = (TextView) view.findViewById(R.id.noPhoto);
        main20 = (AutoLinearLayout) view.findViewById(R.id.main20);
        teacherManger_1();
        return view;
    }


    public void teacherManger_1() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/teacherCenter");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "1");
        params.addBodyParameter("gid", gid);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", "调用相册" +gid);
                Log.d("tag", "调用相册数据" + result);
                Gson gson = new Gson();
                final TeacherManagePhotoBean bean = gson.fromJson(result, TeacherManagePhotoBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getPv().size()!=0){
                        noPhoto.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        TeacherPhotoListViewAdapter adapter = new TeacherPhotoListViewAdapter(bean.getData().getPv(), getActivity());
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                List<String> picture = bean.getData().getPv().get(i).getPurl();
                                Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
                                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) picture);
                                startActivity(intent);
                            }
                        });
                    }else {
                        noPhoto.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }

                } else {
                    noPhoto.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "调用相册错误" );
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
