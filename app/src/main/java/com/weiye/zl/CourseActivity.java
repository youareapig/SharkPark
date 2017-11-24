package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
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
    @BindView(R.id.layout_local)
    LinearLayout layoutLocal;
    @BindView(R.id.restart)
    TextView restart;
    @BindView(R.id.layout_restart)
    RelativeLayout layoutRestart;
    private Unbinder unbinder;
    private int year, month, day;
    private String indexID, userID, userType, userTag, date, mydate, mytime, s1, s2, userTimes, gradename;
    private SharedPreferences sharedPreferences;
    private CourseAdpters adpters;
    private List<CourseBeans.DataBeanX.DataBean> list;
    private String[] arry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
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
                layoutLocal.setVisibility(View.VISIBLE);
                layoutRestart.setVisibility(View.GONE);
                Gson gson = new Gson();
                CourseBeans beans = gson.fromJson(result, CourseBeans.class);
                list = beans.getData().getData();
                arry = new String[beans.getData().getTime().size()];
                for (int i = 0; i < beans.getData().getTime().size(); i++) {
                    arry[i] = beans.getData().getTime().get(i).getDatename();
                }
                if (beans.getCode() == 1000) {
                    if (beans.getData().getData()!=null) {
                        noCourse.setVisibility(View.GONE);
                        isCourse.setVisibility(View.VISIBLE);
                        adpters = new CourseAdpters(CourseActivity.this, list);
                        courseGridView.setAdapter(adpters);
                    } else {
                        noCourse.setVisibility(View.VISIBLE);
                        isCourse.setVisibility(View.GONE);
                    }
                } else {
                    noCourse.setVisibility(View.VISIBLE);
                    isCourse.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                layoutLocal.setVisibility(View.GONE);
                layoutRestart.setVisibility(View.VISIBLE);
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
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.chooseDate, R.id.chooseTime, R.id.courseButton, R.id.courseBack, R.id.courseWode,R.id.restart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chooseDate:
                final DatePicker picker1 = new DatePicker(this);
                picker1.setCanLoop(false);
                picker1.setWheelModeEnable(true);
                picker1.setTopPadding(15);
                picker1.setRangeStart(1900, 8, 29);
                picker1.setRangeEnd(2100, 1, 11);
                picker1.setSelectedItem(year, month+1, day);
                picker1.setWeightEnable(true);
                LineConfig config1 = new LineConfig();
                config1.setColor(Color.parseColor("#000000"));//线颜色
                config1.setAlpha(140);//线透明度
                picker1.setSelectedTextColor(Color.BLACK);
                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        mydate = year + "-" + month + "-" + day;
                        chooseDate.setText(mydate);
                        s1 = chooseDate.getText().toString();
                        chooseTime.setText("上课时间");
                        visit(indexID, s1, "");
                    }
                });
                picker1.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker1.setTitleText(year + "-" + picker1.getSelectedMonth() + "-" + picker1.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker1.setTitleText(picker1.getSelectedYear() + "-" + month + "-" + picker1.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker1.setTitleText(picker1.getSelectedYear() + "-" + picker1.getSelectedMonth() + "-" + day);
                    }
                });
                picker1.show();
                break;
            case R.id.chooseTime:
                try {
                    SinglePicker<String> picker = new SinglePicker<>(this, arry);
                    picker.setCanLoop(true);//不禁用循环
                    picker.setTopBackgroundColor(Color.parseColor("#ffffff"));
                    picker.setTopHeight(50);
                    picker.setWeightEnable(true);
                    picker.setWeightWidth(1);
                    picker.setHeight(600);
                    picker.setTopLineColor(Color.parseColor("#eeeeee"));
                    picker.setTopLineHeight(1);
                    picker.setTitleTextColor(Color.BLACK);
                    picker.setTitleTextSize(12);
                    picker.setCancelTextColor(Color.parseColor("#000000"));
                    picker.setCancelTextSize(13);
                    picker.setSubmitTextColor(Color.parseColor("#000000"));
                    picker.setSubmitTextSize(13);
                    picker.setSelectedTextColor(Color.parseColor("#000000"));
                    picker.setUnSelectedTextColor(Color.parseColor("#888888"));
                    LineConfig config = new LineConfig();
                    config.setColor(Color.parseColor("#000000"));//线颜色
                    config.setAlpha(140);//线透明度
                    config.setRatio((float) (1.0 / 8.0));//线比率
                    picker.setLineConfig(config);
                    picker.setItemWidth(200);
                    picker.setBackgroundColor(Color.parseColor("#ffffff"));
                    picker.setSelectedIndex(0);
                    picker.setOnItemPickListener(new OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            mytime = item;
                            chooseTime.setText(mytime);
                            s2 = chooseTime.getText().toString();
                            visit(indexID, s1, s2);
                        }
                    });
                    picker.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CourseActivity.this, "暂无课程安排", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.courseButton:
                if (userTag.equals("0")) {
                    new UserLoginDialog1(this).loginDialog();
                } else {
                    if (userType.equals("1") || userType.equals("4")) {
                        Intent intent = new Intent(this, SubmitActivity.class);
                        startActivity(intent);
                    } else {
                        if (userTimes.equals("0")) {
                            Toast.makeText(CourseActivity.this, "您已经预约！", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(this, SubmitActivity.class);
                            startActivity(intent);
                        }
                    }

                }
                break;
            case R.id.courseWode:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("fTag", 3);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.courseBack:
                finish();
                break;
            case R.id.restart:
                visit(indexID, "", "");
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
        gradename = sharedPreferences.getString("gradename", "未知");
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
        date = formatter.format(curDate);
        chooseDate.setText("上课日期");
        chooseTime.setText("上课时间");
        courseClass.setText(gradename);

    }

}
