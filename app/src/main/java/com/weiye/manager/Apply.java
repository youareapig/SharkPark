package com.weiye.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.weiye.adapter.ApplyAdapter;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DELL on 2017/7/5.
 */

public class Apply extends Fragment {
    private GridView gridView;
    private ApplyAdapter applyAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager, container, false);
        gridView = (GridView) view.findViewById(R.id.managerGrid);
        applyAdapter=new ApplyAdapter(getActivity());
        gridView.setAdapter(applyAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ApplyDetailsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
