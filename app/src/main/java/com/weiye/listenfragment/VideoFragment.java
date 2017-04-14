package com.weiye.listenfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.myview.MyListView;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class VideoFragment extends Fragment{
    private MyListView listView;
    private List<HashMap<String,Object>> list;
    private HashMap<String,Object> h1,h2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videofragment, container, false);
        listView= (MyListView) view.findViewById(R.id.videofragment_listview);
        list=new ArrayList<>();
        h1=new HashMap<>();
        h2=new HashMap<>();
        h1.put("icon",R.mipmap.aaa);
        h1.put("str","空军飞机卡就开放接口尽快发机房");

        h2.put("icon",R.mipmap.aaa);
        h2.put("str","考虑的法律框架都试过了科技实力就看过");
        list.add(h1);
        list.add(h2);
        listView.setAdapter(new SubVideoListViewAdapter(list,getActivity()));
        return view;
    }
}
