package com.weiye.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.weiye.data.KCBBean;
import com.weiye.myview.MyGridView;
import com.weiye.zl.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/4/14.
 */
public class CurriculumListViewAdapter extends BaseAdapter {
    private List<KCBBean.DataBean.ChildrenBean> list;
    private List<KCBBean.DataBean> listAll;
    private Context context;
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private callBack callBack;
    private StringBuilder gradeID = new StringBuilder();
    private List<KCBBean.DataBean.ChildrenBean.CourseBean> list2;
    private List<KCBBean.DataBean.ChildrenBean.CourseBean> tagList=new ArrayList<>();
    public CurriculumListViewAdapter(Context context, List<KCBBean.DataBean.ChildrenBean> list,List<KCBBean.DataBean> listAll, callBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.layoutInflater = ((Activity) context).getLayoutInflater();
        this.list = list;
        this.listAll=listAll;

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
        final KCBBean.DataBean.ChildrenBean bean = list.get(i);
        list2=bean.getCourse();
        holder = new ViewHolder();
        if (view == null) {
            view = layoutInflater.inflate(R.layout.curriculistviewitem, null);
            holder.textViewStart = (TextView) view.findViewById(R.id.curriculistview_item_timeStrar);
            holder.myGridView = (MyGridView) view.findViewById(R.id.curriculistview_item_gridview);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewStart.setText(bean.getDatename());
        final CurriculumListView_GridviewAdapter gridviewAdapter = new CurriculumListView_GridviewAdapter((Activity) view.getContext(),list2);
        holder.myGridView.setAdapter(gridviewAdapter);
        holder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               KCBBean.DataBean.ChildrenBean.CourseBean bean2= (KCBBean.DataBean.ChildrenBean.CourseBean) adapterView.getItemAtPosition(i);
                if (bean2.getIscheckd()==0) {
                    bean2.setIscheckd(1);
                } else {
                    bean2.setIscheckd(0);
                }
                gridviewAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private class ViewHolder {
        private TextView textViewStart;
        private MyGridView myGridView;
    }

    public void KCyuyue() {
        if (list != null) {
            gradeID.delete(0, gradeID.length());
            for (int j=0;j<listAll.size();j++){
                for (int k=0;k<listAll.get(j).getChildren().size();k++){
                    List<KCBBean.DataBean.ChildrenBean.CourseBean> l=listAll.get(j).getChildren().get(k).getCourse();
                    for (int y=0;y<l.size();y++){

                        if (l.get(y).getIscheckd()==1){
                            tagList.add(l.get(y));
                            gradeID.append(",");
                            gradeID.append(l.get(y).getCoid());
                        }
                    }
                }
            }
            if (tagList != null & tagList.size() > 0) {
                try {
                    callBack.callBackKCID(gradeID);
                    Log.d("tag","课程id"+gradeID);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "请选择课程", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(context, "请选择课程", Toast.LENGTH_SHORT).show();
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
