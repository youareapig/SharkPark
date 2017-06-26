package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.data.PhotoBean;
import com.weiye.myview.MyListView;
import com.weiye.photoshow.ImagePagerActivity;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
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
    private List<String> photoList = new ArrayList<>();
    private String indexID;


    public PhotoFragment(String indexID) {
        this.indexID = indexID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment, container, false);
        listView = (MyListView) view.findViewById(R.id.photofragment_listview);
        visitPhoto();
        return view;
    }

    private void visitPhoto() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/subPhoto");
        params.addBodyParameter("sbid", indexID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                PhotoBean bean = gson.fromJson(result, PhotoBean.class);
                if (bean.getCode()==1000){
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
                }else {
                    Toast.makeText(getActivity(), "暂无相册", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "获取相册失败", Toast.LENGTH_SHORT).show();
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
