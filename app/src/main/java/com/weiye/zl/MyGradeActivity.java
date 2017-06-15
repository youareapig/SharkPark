package com.weiye.zl;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.weiye.adapter.MyGradeAdapter;
import com.weiye.data.MyGradeBean;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyGradeActivity extends AutoLayoutActivity {
    @BindView(R.id.mGradeBack)
    RelativeLayout mGradeBack;
    @BindView(R.id.mGradeListView)
    ListView mGradeListView;
    private Unbinder unbinder;
    private MyGradeBean bean1, bean2, bean3;
    private List<MyGradeBean> list;
    private MyGradeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);
        unbinder = ButterKnife.bind(this);
        list = new ArrayList<>();
        bean1 = new MyGradeBean("9:00-10:00", "周三", "22", "植物大战僵尸");
        bean2 = new MyGradeBean("10:00-11:00", "周四", "23", "植物大战僵尸1");
        bean3 = new MyGradeBean("11:00-12:00", "周五", "12", "植物大战僵尸2");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        adapter = new MyGradeAdapter(this, list);
        mGradeListView.setAdapter(adapter);
        mGradeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog dialog = new AlertDialog.Builder(MyGradeActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.delete, null);
                dialog.setView(v);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                v.findViewById(R.id.sure1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        list.remove(i);
                        adapter.notifyDataSetChanged();
                        dialog.cancel();

                    }
                });
                v.findViewById(R.id.off1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.mGradeBack)
    public void onViewClicked() {
        finish();
    }
}
