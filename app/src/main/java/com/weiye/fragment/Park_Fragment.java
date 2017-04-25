package com.weiye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ListView;
import android.widget.ScrollView;

import com.weiye.adapter.ActivitiesGridAdpter;
import com.weiye.myview.MyListView;
import com.weiye.zl.AppearanceActivity;
import com.weiye.zl.IntroActivity;
import com.weiye.zl.R;
import com.weiye.zl.SchoolActivity;
import com.weiye.zl.VedioPlayerActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/6.
 */
public class Park_Fragment extends Fragment implements View.OnClickListener {
    private ListView mListview;
    private List<Integer> list;
    private AutoRelativeLayout sActivity, appearance, intro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parkfragment, container, false);
        mListview = (ListView) view.findViewById(R.id.parkListView);
        sActivity = (AutoRelativeLayout) view.findViewById(R.id.sActivity);
        appearance = (AutoRelativeLayout) view.findViewById(R.id.appearance);
        intro = (AutoRelativeLayout) view.findViewById(R.id.intro);
        sActivity.setOnClickListener(this);
        appearance.setOnClickListener(this);
        intro.setOnClickListener(this);
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
        mListview.setAdapter(new ActivitiesGridAdpter(list, getActivity()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sActivity:
                Intent intent = new Intent(getActivity(), SchoolActivity.class);
                startActivity(intent);
                break;
            case R.id.appearance:
                Intent intent1 = new Intent(getActivity(), AppearanceActivity.class);
                startActivity(intent1);
                break;
            case R.id.intro:
                Intent intent2 = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
