package com.weiye.mycourse;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weiye.adapter.YiChangAdapter;
import com.weiye.zl.R;

/**
 * Created by DELL on 2017/7/5.
 */

public class YiChang extends Fragment {
    private ListView listView;
    private YiChangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yichang, container, false);
        listView= (ListView) view.findViewById(R.id.yichangListView);
        init();
        return view;
    }
    private void init(){
        adapter=new YiChangAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                LayoutInflater inflater =getActivity().getLayoutInflater();
                View v = inflater.inflate(R.layout.yichangdilog, null);
                dialog.setView(v);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                v.findViewById(R.id.out).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
    }
}
