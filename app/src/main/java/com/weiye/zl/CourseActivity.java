package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.adapter.CourseAdpters;
import com.weiye.data.CourseBeans;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.utils.UserLoginDialog1;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import qiu.niorgai.StatusBarCompat;

public class CourseActivity extends AutoLayoutActivity {
    @BindView(R.id.courseClass)
    TextView courseClass;
    @BindView(R.id.courseBack)
    RelativeLayout courseBack;
    @BindView(R.id.chooseDate)
    TextView chooseDate;
    @BindView(R.id.chooseTime)
    TextView chooseTime;
    @BindView(R.id.chooseTimeAndDate)
    LinearLayout chooseTimeAndDate;
    @BindView(R.id.courseGridView)
    GridView courseGridView;
    @BindView(R.id.courseButton)
    TextView courseButton;
    @BindView(R.id.courseWode)
    TextView courseWode;
    @BindView(R.id.isCourse)
    RelativeLayout isCourse;
    @BindView(R.id.noCourse)
    TextView noCourse;
    private Unbinder unbinder;
    private int year, month, day;
    private String indexID, userID, userType, userTag, date, mydate, mytime, s1, s2,userTimes;
    private SharedPreferences sharedPreferences;
    private CourseAdpters adpters;
    private List<CourseBeans.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        makeDate();
        visit(indexID, "", "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeDate();
        visit(indexID, "", "");
    }

    private void visit(String classid, String riqi, String shijian) {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/courseLst");
        params.addBodyParameter("sbid", classid);
        params.addBodyParameter("dates", riqi);
        params.addBodyParameter("datename", shijian);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                CourseBeans beans = gson.fromJson(result, CourseBeans.class);
                list = beans.getData();
                if (beans.getCode() == 1000) {
                    noCourse.setVisibility(View.GONE);
                    isCourse.setVisibility(View.VISIBLE);
                    adpters = new CourseAdpters(CourseActivity.this, list);
                    courseGridView.setAdapter(adpters);
                } else {
                    noCourse.setVisibility(View.VISIBLE);
                    isCourse.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(CourseActivity.this, "服务器故障", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.chooseDate, R.id.chooseTime, R.id.courseButton, R.id.courseBack, R.id.courseWode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chooseDate:
                DatePicker picker1 = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
                picker1.setRange(2017, 2017);
                picker1.setSelectedItem(year, month + 1, day);
                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        //TODO 点击确定监听事件
                        mydate = year + "-" + month + "-" + day;
                        chooseDate.setText(mydate);
                        s1 = chooseDate.getText().toString();
                        chooseTime.setText("上课时间");
                        visit(indexID, s1, "");

                    }
                });
                picker1.show();
                break;
            case R.id.chooseTime:
                OptionPicker picker = new OptionPicker(this, new String[]{
                        "09:30-10:30", "13:30-14:30"
                });
                picker.setOffset(2);
                picker.setTextSize(18);
                picker.setTextColor(Color.BLACK);
                picker.setSelectedIndex(1);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(String option) {
                        mytime = option;
                        chooseTime.setText(mytime);
                        s2 = chooseTime.getText().toString();
                        visit(indexID, s1, s2);
                    }


                });
                picker.show();
                break;
            case R.id.courseButton:
                if (userTag.equals("0")) {
                    new UserLoginDialog1(this).loginDialog();
                } else {
                    if (userTimes.equals("0")){
                        Toast.makeText(CourseActivity.this,"您已经预约！",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(this, SubmitActivity.class);
                        startActivity(intent);
                    }

                }
                break;
            case R.id.courseWode:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("fTag", 3);
                startActivity(intent);
                break;
            case R.id.courseBack:
                finish();
                break;
        }
    }

    private void makeDate() {
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        userTag = sharedPreferences.getString("usertag", "0");
        indexID = sharedPreferences.getString("indexID", "0");
        userTimes = sharedPreferences.getString("usertimes", "1");
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
        date = formatter.format(curDate);
        chooseDate.setText("上课日期");
        chooseTime.setText("上课时间");
        //TODO 通过用户类型判断按钮问题

        Log.d("tag", "用户类型----" + userTag);
        if (userTag.equals("0")) {
            courseWode.setVisibility(View.GONE);
            courseButton.setVisibility(View.VISIBLE);
        } else {
            if (userType.equals("3")) {
                courseWode.setVisibility(View.GONE);
                courseButton.setVisibility(View.VISIBLE);
            } else {
                courseWode.setVisibility(View.GONE);
                courseButton.setVisibility(View.GONE);
            }
        }

    }
}
