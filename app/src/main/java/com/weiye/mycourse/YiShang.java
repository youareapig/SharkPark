package com.weiye.mycourse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weiye.adapter.YishangAdapter;
import com.weiye.zl.R;

/**
 * Created by DELL on 2017/7/5.
 */

public class YiShang extends Fragment {
    private ListView listView;
    private YishangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yishang, container, false);
        listView= (ListView) view.findViewById(R.id.yishangListView);
        init();
        return view;
    }
    private void init(){
        adapter=new YishangAdapter(getActivity());
        listView.setAdapter(adapter);
    }
}
