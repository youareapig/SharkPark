package com.weiye.listenfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.L;
import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.data.PhotoBean;
import com.weiye.myview.MyListView;
import com.weiye.photoshow.ImagePagerActivity;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;

import org.w3c.dom.Text;
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
    private TextView noPhoto;

    public PhotoFragment(String indexID) {
        this.indexID = indexID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment, container, false);
        listView = (MyListView) view.findViewById(R.id.photofragment_listview);
        noPhoto= (TextView) view.findViewById(R.id.noPhoto);
        visitPhoto();
        return view;
    }

    private void visitPhoto() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/isphotoVideo");
        params.addBodyParameter("type", indexID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag","相册数据"+result);
                Gson gson = new Gson();
                PhotoBean bean = gson.fromJson(result, PhotoBean.class);
                if (bean.getCode()==1000){
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
                }else {
                    noPhoto.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
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
