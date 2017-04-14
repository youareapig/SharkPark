package com.weiye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.weiye.adapter.ListView_1_Adapter;
import com.weiye.adapter.SchoolGridAdapter;
import com.weiye.myview.MyListView;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Shark_Fragment extends Fragment {
    private GridView gridView;
    private List<Integer> list;
    private MyListView mListView;
    private HashMap<String, Object> hashMap1, hashMap2;
    private List<HashMap<String, Object>> hashList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sharkfragment, container, false);
        gridView = (GridView) view.findViewById(R.id.schoolGrid);
        mListView = (MyListView) view.findViewById(R.id.listview1);
        setGridView();
        science();
        return view;
    }

    //TODO 课堂风采图片滚动
    private void setGridView() {
        list = new ArrayList<>();
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.background);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);

        int size = list.size();
        int length = 380;//原来是500
        DisplayMetrics dm = new DisplayMetrics();
        Log.e("tag","--------->"+dm);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("tag","--------->"+dm);
        float density = dm.density;
        Log.e("tag","--------->"+density);
        int gridviewWidth = (int) (size * (length + 4) * density);
        Log.e("tag","--------->"+gridviewWidth);
        int itemWidth = (int) (length * density);
        Log.e("tag","--------->"+itemWidth);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        // gridView.setHorizontalSpacing(10); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
        gridView.setAdapter(new SchoolGridAdapter(list, getActivity()));

    }

    //TODO 科学驿站Listview
    private void science() {
        hashList = new ArrayList<>();
        hashMap1 = new HashMap<>();
        hashMap2 = new HashMap<>();

        hashMap1.put("icon", R.mipmap.shark);
        hashMap1.put("intro", "这是一只大鲨鱼，大鲨鱼啊大鲨鱼！！！！");

        hashMap2.put("icon", R.mipmap.shark);
        hashMap2.put("intro", "这是一只小鲨鱼，小鲨鱼啊小鲨鱼！！！！");

        hashList.add(hashMap1);
        hashList.add(hashMap2);

        mListView.setAdapter(new ListView_1_Adapter(hashList, getActivity()));

    }
}
