package com.weiye.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.weiye.adapter.AuditionAdapter;
import com.weiye.zl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/7/5.
 */

public class Audition extends Fragment {
    private ListView listView;
    private AuditionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager1, container, false);
        listView = (ListView) view.findViewById(R.id.managerShiting);
        adapter = new AuditionAdapter(getActivity());
        listView.setAdapter(adapter);
        return view;
    }
}
