package com.weiye.manager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.adapter.ApplyDetailsAdapter;
import com.weiye.zl.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ApplyDetailsActivity extends AutoLayoutActivity {
    @BindView(R.id.applyDetailsBack)
    RelativeLayout applyDetailsBack;
    @BindView(R.id.courseName)
    TextView courseName;
    @BindView(R.id.courseListView)
    ListView courseListView;
    private Unbinder unbinder;
    private ApplyDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_details);
        unbinder = ButterKnife.bind(this);
        adapter=new ApplyDetailsAdapter(this);
        courseListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.applyDetailsBack)
    public void onViewClicked() {
        finish();
    }
}
