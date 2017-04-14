package com.weiye.listenfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiye.adapter.SubPhotoListViewAdapter;
import com.weiye.adapter.SubVideoListViewAdapter;
import com.weiye.myview.MyListView;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class PhotoFragment extends Fragment {
    private MyListView listView;
    private List<HashMap<String, Object>> list;
    private HashMap<String, Object> h1, h2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photofragment, container, false);
        listView= (MyListView) view.findViewById(R.id.photofragment_listview);
        list=new ArrayList<>();
        h1=new HashMap<>();
        h2=new HashMap<>();
        h1.put("icon",R.mipmap.aaa);
        h1.put("str","就仨房空手道解放了十几个");

        h2.put("icon",R.mipmap.aaa);
        h2.put("str","分手快乐jfk垃圾啊是");
        list.add(h1);
        list.add(h2);
        listView.setAdapter(new SubPhotoListViewAdapter(list,getActivity()));
        return view;
    }
}
