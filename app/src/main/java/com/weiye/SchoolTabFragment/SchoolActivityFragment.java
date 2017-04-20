package com.weiye.schoolTabFragment;

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
import android.widget.ScrollView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weiye.adapter.SchoolRecyclerAdapter;
import com.weiye.zl.R;
import com.weiye.zl.SchoolNextActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/20.
 */
public class SchoolActivityFragment extends Fragment{
    private RecyclerView schoolRecycler;
    private List<Integer> list;
    private ScrollView scrollView;
    private RoundedImageView mImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.schoolactivityfragment,container,false);
        schoolRecycler= (RecyclerView) view.findViewById(R.id.schoolRecycler);
        scrollView= (ScrollView) view.findViewById(R.id.mScroll);
        mImage= (RoundedImageView) view.findViewById(R.id.mImage);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SchoolNextActivity.class);
                startActivity(intent);
            }
        });
        list=new ArrayList<>();
        list.add(R.mipmap.gicon);
        list.add(R.mipmap.gicon);
        list.add(R.mipmap.gicon);
        list.add(R.mipmap.gicon);
        list.add(R.mipmap.gicon);
        list.add(R.mipmap.gicon);
        scrollView.smoothScrollTo(0, 20);
        schoolRecycler.setAdapter(new SchoolRecyclerAdapter(list));
        schoolRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false));
        return view;
    }
}
