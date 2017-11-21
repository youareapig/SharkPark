package com.weiye.zl;
/**
 * 首页的二级界面
 */

import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.adapter.ManagerAdapter;
import com.weiye.adapter.SpinnerAdpter;
import com.weiye.data.TeacherManagerBean;
import com.weiye.listenfragment.TeacherPhotoFragment;
import com.weiye.listenfragment.TeacherVideoFragment;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.myview.CustomViewPager;
import com.weiye.myview.NoScrollViewPager;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class TeacherManageActivity extends AutoLayoutActivity {


    @BindView(R.id.teachermanagerTitle)
    TextView teachermanagerTitle;
    @BindView(R.id.teachermanagerJt)
    ImageView teachermanagerJt;
    @BindView(R.id.back7)
    RelativeLayout back7;
    @BindView(R.id.title_subject)
    RelativeLayout titleSubject;
    @BindView(R.id.teachermanagerBg)
    ImageView teachermanagerBg;
    @BindView(R.id.teachermanagerContent)
    TextView teachermanagerContent;
    @BindView(R.id.mytitle)
    FrameLayout mytitle;
    @BindView(R.id.teachermanagerTab)
    XTabLayout teachermanagerTab;
    @BindView(R.id.teachermanagerPager)
    CustomViewPager teachermanagerPager;
    @BindView(R.id.main2)
    FrameLayout main2;
    @BindView(R.id.teachermanagerDom)
    RelativeLayout teachermanagerDom;
    private Unbinder unbinder;
    private int height;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private List<Fragment> list;
    private List<String> slist;
    private int currentIndex = 0;
    private String userType, userID;
    private SharedPreferences sharedPreferences;
    private CustomProgressDialog customProgressDialog;
    private ListView popListView;
    private PopupWindow popupWindow;
    private FragmentManager fragmentManager;
    private SharedPreferences.Editor editor;
    private String gid;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachermanager);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("gid", "0");
        editor.commit();
        userType = sharedPreferences.getString("usertype", "未知");
        userID = sharedPreferences.getString("userid", "未知");
        gid = sharedPreferences.getString("gid", "未知");

        slist = new ArrayList<>();
        slist.add("视频");
        slist.add("相册");
        list = new ArrayList<>();
        list.add(new TeacherVideoFragment("0"));
        list.add(new TeacherPhotoFragment("0"));
        //TODO 给TabLayout添加分割线
        LinearLayout linearLayout = (LinearLayout) teachermanagerTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tabline));
        linearLayout.setDividerPadding(60);

        teachermanagerTab.setupWithViewPager(teachermanagerPager);
        fragmentManager = getSupportFragmentManager();
        teachermanagerPager.setAdapter(new ManagerAdapter(fragmentManager, slist, list));
        teachermanagerTab.getTabAt(0).select();
        teachermanagerTab.getTabAt(1).select();
        teachermanagerPager.setCurrentItem(0);
        teacherManager_first();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void teacherManager_first() {
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/teacherCenter");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "1");
        params.addBodyParameter("gid", "0");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final TeacherManagerBean bean = gson.fromJson(result, TeacherManagerBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getGrade() != null) {


                        teachermanagerTitle.setText(bean.getData().getGrade().get(0).getGname());
                        teachermanagerContent.setText(bean.getData().getGrade().get(0).getInform());
                        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getGrade().get(0).getGpic(), teachermanagerBg);

                        View customView = getLayoutInflater().inflate(R.layout.mypop,
                                null, false);
                        popListView = (ListView) customView.findViewById(R.id.popListView);
                        popupWindow = new PopupWindow(customView, 1080, 760);
                        popupWindow.setBackgroundDrawable(null);
                        popupWindow.setOutsideTouchable(false);
                        popupWindow.setFocusable(false);
                        popListView.setAdapter(new SpinnerAdpter(TeacherManageActivity.this, bean.getData().getGrade()));

                        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                aBoolean = true;
                                teachermanagerJt.setImageResource(R.mipmap.dwon);
                                popupWindow.dismiss();
                                teachermanagerTitle.setText(bean.getData().getGrade().get(position).getGname());
                                teacherManger(bean.getData().getGrade().get(position).getGid(), position);

                            }
                        });

                    } else {
                        Toast.makeText(TeacherManageActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TeacherManageActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void teacherManger(final String classID, final int position) {
        customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/teacherCenter");
        params.addBodyParameter("uid", userID);
        params.addBodyParameter("tp", "1");
        params.addBodyParameter("gid", classID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                final TeacherManagerBean bean = gson.fromJson(result, TeacherManagerBean.class);
                if (bean.getCode() == 3000) {
                    teachermanagerTitle.setText(bean.getData().getGrade().get(position).getGname());
                    teachermanagerContent.setText(bean.getData().getGrade().get(position).getInform());
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getGrade().get(position).getGpic(), teachermanagerBg);
                    list.clear();
                    list.add(new TeacherVideoFragment(classID));
                    list.add(new TeacherPhotoFragment(classID));
                    editor.putString("gid", classID);
                    editor.commit();
                    teachermanagerTab.setupWithViewPager(teachermanagerPager);
                    fragmentManager = getSupportFragmentManager();
                    teachermanagerPager.setAdapter(new ManagerAdapter(fragmentManager, slist, list));
                    teachermanagerPager.setCurrentItem(0);
                }else {
                    Toast.makeText(TeacherManageActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TeacherManageActivity.this, "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


    @OnClick({R.id.back7, R.id.teachermanagerDom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teachermanagerDom:
                if (aBoolean == true) {
                    popupWindow.showAsDropDown(view, 0, 45);
                    teachermanagerJt.setImageResource(R.mipmap.up);
                    aBoolean = false;
                } else {
                    popupWindow.dismiss();
                    teachermanagerJt.setImageResource(R.mipmap.dwon);
                    aBoolean = true;
                }
                break;
            case R.id.back7:
                finish();
                break;
        }
    }
}
