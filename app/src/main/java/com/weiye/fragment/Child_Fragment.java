package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.IndexBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.third.BaseAdapterHelper;
import com.weiye.third.Gallery;
import com.weiye.third.QuickPagerAdapter;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SubjectActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Child_Fragment extends Fragment {
    private Gallery mGallery;
    private QuickPagerAdapter<IndexBean.RowsBean> quickPagerAdapter;
    private List<IndexBean.RowsBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.childfragment, container, false);
        mGallery= (Gallery) view.findViewById(R.id.myGallery);
        index();
        return view;
    }
    private void index(){
        final CustomProgressDialog customProgressDialog=new CustomProgressDialog(getActivity(),"玩命加载中...",R.drawable.frame);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_LXXXDataService.ashx?op=getTAB_LXXX");
        params.addBodyParameter("start","0");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                IndexBean bean=gson.fromJson(result,IndexBean.class);
                mList=bean.getRows();
                quickPagerAdapter=new QuickPagerAdapter<IndexBean.RowsBean>(getActivity(),R.layout.galleryitem,mList) {
                    @Override
                    protected void convertView(BaseAdapterHelper helper, final IndexBean.RowsBean item) {
                        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+item.getTXLJ(), (ImageView) helper.getView(R.id.galleryitem_img));
                        helper.setText(R.id.galleryitem_title,item.getLXMC());
                        helper.setImageOnClickListener(R.id.galleryitem_img, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(getActivity(),SubjectActivity.class);
                                intent.putExtra("indexID", item.getID()+"");
                                startActivity(intent);
                            }
                        });
                    }
                };
                mGallery.setAdapter(quickPagerAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),"获取首页数据失败",Toast.LENGTH_SHORT).show();
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
