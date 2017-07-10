package com.weiye.mycourse;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.weiye.adapter.WeishangAdapter;
import com.weiye.zl.R;

/**
 * Created by DELL on 2017/7/5.
 */

public class WeiShang extends Fragment {
    private SwipeMenuListView swipeMenuListView;
    private WeishangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weishang, container, false);
        swipeMenuListView= (SwipeMenuListView) view.findViewById(R.id.weishangListView);
        init();
        return view;
    }
    private void init(){
        SwipeMenuCreator creator=new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                deleteItem.setBackground(R.color.red);
                deleteItem.setWidth(240);
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);

            }
        };
        adapter=new WeishangAdapter(getActivity());
        swipeMenuListView.setAdapter(adapter);
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Toast.makeText(getActivity(),"模拟删除成功",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
