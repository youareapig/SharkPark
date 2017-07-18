package com.weiye.schooltabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.SchoolRecyclerAdapter;
import com.weiye.data.AllHuodongBean;
import com.weiye.data.HuodongBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;
import com.weiye.zl.SchoolWeiChatActivity;
import com.weiye.zl.SchoolHtmlActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by DELL on 2017/4/20.
 */
public class WangQi extends Fragment {
    private RecyclerView schoolRecycler;
    private List<AllHuodongBean.DataBean> list;
    private ScrollView scrollView;
    private RoundedImageView mImage;
    private CustomProgressDialog customProgressDialog;
    private TextView showView,mContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schoolactivity1, container, false);
        schoolRecycler = (RecyclerView) view.findViewById(R.id.schoolRecycler);
        scrollView = (ScrollView) view.findViewById(R.id.mScroll);
        mImage = (RoundedImageView) view.findViewById(R.id.mImage);
        showView = (TextView) view.findViewById(R.id.showview);
        mContent= (TextView) view.findViewById(R.id.mContent);
        scrollView.smoothScrollTo(0, 20);
        visit();

        return view;
    }

    private void visit() {
        customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/activeLst");
        params.addBodyParameter("tp", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                AllHuodongBean bean = gson.fromJson(result, AllHuodongBean.class);
                list = bean.getData();
                if (bean.getCode()==-1000){
                    showView.setVisibility(View.VISIBLE);
                }else if (bean.getCode()==1000){
                    showView.setVisibility(View.GONE);
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+list.get(0).getBjimg(),mImage);
                    mContent.setText(list.get(0).getTitle());
                    schoolRecycler.setAdapter(new SchoolRecyclerAdapter(list));
                    schoolRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
                    mImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = null;
                            if (list.get(0).getIswei().equals("1")) {
                                intent = new Intent(view.getContext(), SchoolWeiChatActivity.class);
                                intent.putExtra("wuxin", list.get(0).getWeurl());
                            } else {
                                intent = new Intent(view.getContext(), SchoolHtmlActivity.class);
                                intent.putExtra("htmls", list.get(0).getContent());
                            }

                            startActivity(intent);
                        }
                    });
                }else {
                    Toast.makeText(getActivity(),"暂无活动详情",Toast.LENGTH_SHORT).show();
                }
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
