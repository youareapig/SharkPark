package com.weiye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiye.adapter.RecyclerAdpter;
import com.weiye.utils.SpacesItemDecoration;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Park_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Integer> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.parkfragment,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycle);
        list=new ArrayList<>();
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.aaa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new SpacesItemDecoration(8));//设置间距
        recyclerView.setAdapter(new RecyclerAdpter(list));
        return view;
    }
}
