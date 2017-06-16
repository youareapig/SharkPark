package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.utils.L;
import com.weiye.data.KCBBean;
import com.weiye.data.TestBean;
import com.weiye.myview.MyGridView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListViewAdapter extends BaseAdapter {
    private List<KCBBean.RowsBeanX.RowsBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private callBack callBack;
    private List<String> idList = new ArrayList<>();
    private List<KCBBean.RowsBeanX.RowsBean> tagList = new ArrayList<>();
    private StringBuilder gradeID = new StringBuilder();

    public CurriculumListViewAdapter(Context context, List<KCBBean.RowsBeanX.RowsBean> list, callBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.list = list;

    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final KCBBean.RowsBeanX.RowsBean bean = list.get(i);
        holder = new ViewHolder();
        if (view == null) {
            view = layoutInflater.inflate(R.layout.curriculistviewitem, null);
            holder.textViewStart = (TextView) view.findViewById(R.id.curriculistview_item_timeStrar);
            holder.textViewEnd = (TextView) view.findViewById(R.id.curriculistview_item_timeEnd);
            holder.myGridView = (MyGridView) view.findViewById(R.id.curriculistview_item_gridview);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewStart.setText(myString(bean.getKSSJ()));
        holder.textViewEnd.setText(myString(bean.getJSSJ()));
        final CurriculumListView_GridviewAdapter gridviewAdapter = new CurriculumListView_GridviewAdapter((Activity) view.getContext(), list);
        holder.myGridView.setAdapter(gridviewAdapter);
        holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                KCBBean.RowsBeanX.RowsBean bean1 = (KCBBean.RowsBeanX.RowsBean) adapterView.getItemAtPosition(i);
                if (bean1.getSTATUS().equals("0")) {
                    bean1.setSTATUS("1");
                } else {
                    bean1.setSTATUS("0");
                }
                gridviewAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private class ViewHolder {
        private TextView textViewStart, textViewEnd;
        private MyGridView myGridView;
    }

    public void KCyuyue() {
        if (list != null) {
            gradeID.delete(0, gradeID.length());

            for (KCBBean.RowsBeanX.RowsBean rowsBean : list) {
                if (rowsBean.getSTATUS().equals("0")) {
                    tagList.add(rowsBean);
                    gradeID.append(",");
                    gradeID.append(rowsBean.getID());
                }
            }
            if (tagList != null & tagList.size() > 0) {
                callBack.callBackKCID(gradeID);
            } else {
                Toast.makeText(context, "没有选中", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public interface callBack {
        void callBackKCID(StringBuilder gradeID);
    }

    //TODO 拆分字符串
    private String myString(String string) {
        String[] s = string.split(" ");
        return s[1];
    }
}
