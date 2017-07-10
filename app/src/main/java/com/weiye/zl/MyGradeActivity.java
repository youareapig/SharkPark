package com.weiye.zl;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.weiye.adapter.MyGradeAdapter;
import com.weiye.data.MyCorseBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyGradeActivity extends AutoLayoutActivity {
    @BindView(R.id.mGradeBack)
    RelativeLayout mGradeBack;
    @BindView(R.id.mGradeListView)
    SwipeMenuListView mGradeListView;
    @BindView(R.id.showNO1)
    TextView showNO1;
    private Unbinder unbinder;
    private MyGradeAdapter adapter;
    private SharedPreferences sharedPreferences;
    private String userID;
    private List<MyCorseBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        visit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void visit() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "玩命加载中....", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/myCourselst");
        params.addBodyParameter("uid", userID);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final MyCorseBean corseBean = gson.fromJson(result, MyCorseBean.class);
                list = corseBean.getData();
                if (corseBean.getCode() == 3000) {
                    showNO1.setVisibility(View.GONE);
                    adapter = new MyGradeAdapter(MyGradeActivity.this, corseBean.getData());
                    mGradeListView.setAdapter(adapter);
                    SwipeMenuCreator creator=new SwipeMenuCreator() {
                        @Override
                        public void create(SwipeMenu menu) {
                            SwipeMenuItem deleteItem = new SwipeMenuItem(
                                    getApplicationContext());
                            deleteItem.setBackground(R.color.colorAccent);
                            deleteItem.setWidth(150);
                            deleteItem.setTitle("删除");
                            deleteItem.setTitleSize(18);
                            deleteItem.setTitleColor(Color.BLACK);
                            menu.addMenuItem(deleteItem);

                        }
                    };
//                    mGradeListView.setMenuCreator(creator);
//                    mGradeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
//                            Log.d("tag","-------id"+corseBean.getData().get(position).getCoid());
//                            Log.d("tag","-------------id"+list.get(position).getCoid());
//                            final AlertDialog dialog = new AlertDialog.Builder(MyGradeActivity.this).create();
//                            LayoutInflater inflater = getLayoutInflater();
//                            View v = inflater.inflate(R.layout.delete, null);
//                            dialog.setView(v);
//                            dialog.setCanceledOnTouchOutside(false);
//                            dialog.show();
//                            v.findViewById(R.id.sure1).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    delteCorse(list.get(position).getCoid(), position, list.get(position).getCarid());
//                                    dialog.cancel();
//
//                                }
//                            });
//                            v.findViewById(R.id.off1).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    dialog.cancel();
//                                }
//                            });
//                            return true;
//                        }
//                    });

                    mGradeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                            final MyCorseBean.DataBean dataBean = (MyCorseBean.DataBean) adapterView.getItemAtPosition(i);
                            final AlertDialog dialog = new AlertDialog.Builder(MyGradeActivity.this).create();
                            LayoutInflater inflater = getLayoutInflater();
                            View v = inflater.inflate(R.layout.delete, null);
                            dialog.setView(v);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();
                            v.findViewById(R.id.sure1).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.d("tag","----->"+dataBean.getCoid());
                                    delteCorse(dataBean.getCoid(), i, dataBean.getCarid());
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
                } else {
                    showNO1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }
        });
    }

    private void delteCorse(String couseID, final int i, String carID) {
        final CustomProgressDialog customProgressDialog1 = new CustomProgressDialog(this, "正在删除....", R.drawable.frame, R.style.dialog);
        customProgressDialog1.setCanceledOnTouchOutside(false);
        customProgressDialog1.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/offCourse");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("coid", couseID);
        params.addBodyParameter("carid", carID);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject j = new JSONObject(result);
                    if (j.getString("code").equals("3007")) {

                        if (list != null) {
                            list.remove(i);
                            adapter.notifyDataSetChanged();
                        }
                        if (list.size() == 0) {
                            showNO1.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(MyGradeActivity.this, "成功取消预约", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyGradeActivity.this, "取消预约失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MyGradeActivity.this, "取消预约失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog1.cancel();
            }
        });

    }

    @OnClick(R.id.mGradeBack)
    public void onViewClicked() {
        finish();
    }
}
