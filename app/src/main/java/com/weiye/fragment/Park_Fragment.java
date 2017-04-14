package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.weiye.adapter.ActivitiesGridAdpter;
import com.weiye.adapter.SchoolGridAdapter;
import com.weiye.zl.R;
import com.weiye.zl.VedioPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Park_Fragment extends Fragment {
    private GridView gridView;
    private List<Integer> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parkfragment, container, false);
        gridView = (GridView) view.findViewById(R.id.activities);
        setGridView();
        return view;
    }
    //TODO 往期活动视频
    private void setGridView() {
        list = new ArrayList<>();
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        int size = list.size();
        int length = 380;
        DisplayMetrics dm = new DisplayMetrics();
        Log.e("tag","******"+dm);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("tag","----*****----->"+dm);
        float density = dm.density;
        Log.e("tag","-----****---->"+density);
        int gridviewWidth = (int) (size * (length + 4) * density);
        Log.e("tag","-----***---->"+gridviewWidth);
        int itemWidth = (int) (length * density);
        Log.e("tag","-----***---->"+itemWidth);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        //gridView.setHorizontalSpacing(10); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
        gridView.setAdapter(new ActivitiesGridAdpter(list, getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), VedioPlayerActivity.class);
                startActivity(intent);
            }
        });

    }
}
