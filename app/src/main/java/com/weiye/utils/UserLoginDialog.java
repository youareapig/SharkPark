package com.weiye.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiye.data.LoginBean;
import com.weiye.zl.R;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by DELL on 2017/5/5.
 */
public class UserLoginDialog {
    private String  stringphone, stringpassword, stringlogin, stringpwd, stringispwd;
    private EditText userphone, userpassword, setpassword, ispassword;
    private TextView login, vercode, forgetpassword, regist, longinTitle1, findphone, findpwd, findnext, findvercode;
    private Context context;
    private AlertDialog dialog, dialog1, dialog2;
    private ImageView findexit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public UserLoginDialog(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("UserTag", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //TODO 登陆对话框
    public void loginDialog() {
        dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View layout = inflater.inflate(R.layout.login, null);
        dialog.setView(layout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        userphone = (EditText) layout.findViewById(R.id.loginTel);
        userpassword = (EditText) layout.findViewById(R.id.loginPassword);
        login = (TextView) layout.findViewById(R.id.login);
        vercode = (TextView) layout.findViewById(R.id.vercode);
        forgetpassword = (TextView) layout.findViewById(R.id.forgetpassword);
        //TODO 输入框变化时监听
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                stringphone = userphone.getText().toString();
                if (TextUtils.isEmpty(stringphone)) {
                    login.setEnabled(false);
                    login.setBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    login.setEnabled(true);
                    login.setBackgroundColor(context.getResources().getColor(R.color.blue));
                    if (stringphone.length() == 11) {
                        detectionUser(stringphone);
                    } else {
                        vercode.setVisibility(View.GONE);
                        userpassword.setHint("密码");
                        userpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        login.setText("登陆");
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringpassword = userpassword.getText().toString();
                stringlogin = login.getText().toString();
                stringphone = userphone.getText().toString();
                if (TextUtils.isEmpty(stringpassword)) {
                    Toast.makeText(context, "请输入验证码或密码!", Toast.LENGTH_SHORT).show();
                } else {
                    if (stringlogin.equals("登陆")) {
                        ClassPathResource classPathResource = new ClassPathResource();
                        boolean isPhone = classPathResource.isMobileNO(stringphone);
                        if (isPhone == false) {
                            Toast.makeText(context, "请输入正确的电话号码!", Toast.LENGTH_SHORT).show();
                        } else {
                            userLogin(stringphone, stringpassword);
                        }

                    } else {
                        Log.e("tag", "进行注册");
                        dialog.cancel();
                        registDialog("设置密码");
                    }
                }
            }
        });
        vercode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone1 = classPathResource.isMobileNO(userphone.getText().toString().trim());
                if (isPhone1 == false) {
                    Toast.makeText(context, "请输入正确的电话号码!", Toast.LENGTH_SHORT).show();
                } else {
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(vercode, 10000, 1000);
                    mCountDownTimerUtils.start();
                }

            }
        });
        //TODO 点击退出
        layout.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                forgetpassword();
            }
        });

    }

    //TODO 注册对话框
    private void registDialog(String title) {
        ImageView exit1;
        dialog1 = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View view = inflater.inflate(R.layout.regist, null);
        dialog1.setView(view);
        dialog1.setCanceledOnTouchOutside(false);
        dialog1.show();
        exit1 = (ImageView) view.findViewById(R.id.exit1);
        setpassword = (EditText) view.findViewById(R.id.password);
        ispassword = (EditText) view.findViewById(R.id.ispassword);
        regist = (TextView) view.findViewById(R.id.regist);
        longinTitle1 = (TextView) view.findViewById(R.id.longinTitle1);
        longinTitle1.setText(title);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.cancel();
            }
        });
        setpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                stringpwd = setpassword.getText().toString().trim();
                if (TextUtils.isEmpty(stringpwd)) {
                    regist.setEnabled(false);
                    regist.setBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    regist.setEnabled(true);
                    regist.setBackgroundColor(context.getResources().getColor(R.color.blue));
                }
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringpwd = setpassword.getText().toString().trim();
                stringispwd = ispassword.getText().toString().trim();
                if (TextUtils.isEmpty(stringpwd) || TextUtils.isEmpty(stringispwd)) {
                    Toast.makeText(context, "请输入密码!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!stringpwd.equals(stringispwd)) {
                        Toast.makeText(context, "两次密码不一致，请确认后输入!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (stringpwd.length() < 6) {
                            Toast.makeText(context, "密码不能少于6位", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.v("tag", "注册信息" + stringphone + "       " + stringpwd);
                            //regist(stringphone,stringpwd);
                            //dialog1.cancel();
                            requestRegister(stringphone,stringispwd);
                            dialog1.cancel();
                        }

                    }
                }
            }
        });
    }

    private void forgetpassword() {
        dialog2 = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View view = inflater.inflate(R.layout.forgetpassword, null);
        dialog2.setView(view);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.show();
        findphone = (TextView) view.findViewById(R.id.loginTel1);
        findpwd = (TextView) view.findViewById(R.id.loginPassword1);
        findexit = (ImageView) view.findViewById(R.id.exit3);
        findvercode = (TextView) view.findViewById(R.id.vercode1);
        findnext = (TextView) view.findViewById(R.id.next);
        findexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.cancel();
            }
        });
        findphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone2 = classPathResource.isMobileNO(findphone.getText().toString().trim());
                if (TextUtils.isEmpty(findphone.getText().toString().trim())) {
                    findvercode.setEnabled(false);
                    findvercode.setBackground(context.getResources().getDrawable(R.drawable.vercode));
                    findnext.setEnabled(false);
                    findnext.setBackgroundColor(context.getResources().getColor(R.color.gray));
                } else {
                    if (isPhone2 == false) {
                        findvercode.setEnabled(false);
                        findvercode.setBackground(context.getResources().getDrawable(R.drawable.vercode));
                    } else {
                        findvercode.setEnabled(true);
                        findvercode.setBackground(context.getResources().getDrawable(R.drawable.vercode1));
                    }
                    findnext.setEnabled(true);
                    findnext.setBackgroundColor(context.getResources().getColor(R.color.blue));

                }
            }
        });
        findvercode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(findvercode, 10000, 1000);
                mCountDownTimerUtils.start();
            }
        });
        findnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(findphone.getText().toString().trim()) || TextUtils.isEmpty(findpwd.getText().toString().trim())) {
                    Toast.makeText(context, "请输入验证码!", Toast.LENGTH_SHORT).show();
                } else {

                    dialog2.cancel();
                    registDialog("重设密码");


                }
            }
        });
    }

    //TODO 用户登录请求

    /**
     * 保存登录状态，1 表示登录状态，0 表示未登录状态
     */
    private void userLogin(String phone, String password) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_YHXXDataService.ashx?op=login");
        Log.v("tag", "登录信息：" + phone + password);
        params.addBodyParameter("DLZH", phone);
        params.addBodyParameter("DLMM", password);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("tag", "登录成功" + result);
                Gson gson = new Gson();
                LoginBean bean = gson.fromJson(result, LoginBean.class);
                if (bean.isSuccess() == true) {
                    editor.putString("usertag", "1");
                    editor.putString("userid",bean.getRows().get(0).getID()+"");
                    editor.commit();
                    dialog.cancel();
                } else {
                    Toast.makeText(context, "不存在用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag", "访问出错");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //TODO 检测用户是否存在
    private void detectionUser(String phone) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "TAB_YHXXDataService.ashx?op=check");
        params.addBodyParameter("DLZH", phone);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", result);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json.getBoolean("Success") == true) {
                        vercode.setVisibility(View.GONE);
                        userpassword.setHint("密码");
                        userpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//输入类型为密码
                        login.setText("登陆");
                    } else {
                        vercode.setVisibility(View.VISIBLE);
                        userpassword.setHint("验证码");
                        login.setText("下一步");
                        userpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag", "访问出错");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //TODO 用户注册接口
    private void requestRegister(String phone,String pwd){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"TAB_YHXXDataService.ashx?op=register");
        params.addBodyParameter("YHZH",phone);
        params.addBodyParameter("YHMM",pwd);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("tag","请求成功"+result);
                try {
                    JSONObject json=new JSONObject(result);
                    if (json.getBoolean("Success")==true){
                        editor.putString("usertag", "1");
                        editor.commit();
                        dialog1.cancel();
                        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag","请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
