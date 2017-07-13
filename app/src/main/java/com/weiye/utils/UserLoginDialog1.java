package com.weiye.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import com.weiye.data.RegistBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.zl.R;
import com.weiye.zl.SubmitActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.jpush.sms.SMSSDK;
import cn.jpush.sms.listener.SmscheckListener;
import cn.jpush.sms.listener.SmscodeListener;

/**
 * Created by DELL on 2017/5/5.
 */
public class UserLoginDialog1 {
    private String stringphone, stringpassword, stringlogin, stringpwd, stringispwd, stringfindphone;
    private EditText userphone, userpassword, setpassword, ispassword;
    private TextView login, vercode, forgetpassword, regist, longinTitle1, findphone, findpwd, findnext, findvercode;
    private Context context;
    private AlertDialog dialog, dialog1, dialog2;
    private ImageView findexit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CustomProgressDialog customProgressDialog;
    public UserLoginDialog1(Context context) {
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
                        SMSSDK.getInstance().checkSmsCodeAsyn(stringphone, stringpassword, new SmscheckListener() {
                            @Override
                            public void checkCodeSuccess(String s) {
                                dialog.cancel();
                                registDialog("设置密码");
                            }

                            @Override
                            public void checkCodeFail(int i, String s) {
                                Toast.makeText(context, "验证码错误，请重新输入" + s, Toast.LENGTH_SHORT).show();
                            }
                        });

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
                    //TODO 极光获取验证码
                    SMSSDK.getInstance().getSmsCodeAsyn(stringphone, "1", new SmscodeListener() {
                        @Override
                        public void getCodeSuccess(String s) {

                        }

                        @Override
                        public void getCodeFail(int i, String s) {

                        }
                    });
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(vercode, 60000, 1000);
                    mCountDownTimerUtils.start();
                    userpassword.requestFocus();
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
                            Log.v("tag", "注册信息" + sharedPreferences.getString("tel", null) + "       " + stringpwd);
                            requestRegister(stringphone, stringispwd);
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
                stringfindphone = findphone.getText().toString().trim();
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone2 = classPathResource.isMobileNO(stringfindphone);
                if (TextUtils.isEmpty(stringfindphone)) {
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
                stringfindphone = findphone.getText().toString().trim();
                ClassPathResource classPathResource = new ClassPathResource();
                boolean isPhone2 = classPathResource.isMobileNO(findphone.getText().toString().trim());
                if (isPhone2 == false) {
                    Toast.makeText(context, "请输入正确的电话号码!", Toast.LENGTH_SHORT).show();
                } else {
                    detectionUser_1(stringfindphone);
                }


            }
        });
        findnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(stringfindphone) || TextUtils.isEmpty(findpwd.getText().toString().trim())) {
                    Toast.makeText(context, "请输入验证码!", Toast.LENGTH_SHORT).show();
                } else {
                    SMSSDK.getInstance().checkSmsCodeAsyn(stringfindphone, findpwd.getText().toString().trim(), new SmscheckListener() {
                        @Override
                        public void checkCodeSuccess(String s) {
                            dialog2.cancel();
                            updatepwdDilog("重设密码");
                        }

                        @Override
                        public void checkCodeFail(int i, String s) {
                            Toast.makeText(context, "验证码错误，请重新输入!", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });
    }

    //TODO 用户登录请求

    /**
     * 保存登录状态，1 表示登录状态，0 表示未登录状态
     */
    private void userLogin(String phone, String password) {
        customProgressDialog = new CustomProgressDialog(context, "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/logo");
        params.addBodyParameter("tel", phone);
        params.addBodyParameter("password", password);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("tag", result);
                Gson gson = new Gson();
                LoginBean bean = gson.fromJson(result, LoginBean.class);
                if (bean.getCode()==3000) {
                    editor.putString("usertag", "1");
                    editor.putString("userid", bean.getData().getId());
                    editor.putString("usertype", bean.getData().getUtype());
                    editor.putString("usertimes", bean.getData().getIsfres());
                    editor.commit();
                    dialog.cancel();
                    if (bean.getData().getUtype().equals("3")){
                        Intent intent = new Intent(context, SubmitActivity.class);
                        context.startActivity(intent);
                    }


                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
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
                customProgressDialog.cancel();
            }
        });
    }

    //TODO 检测用户是否存在
    private void detectionUser(String phone) {
        customProgressDialog = new CustomProgressDialog(context, "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/isRegist");
        params.addBodyParameter("tel", phone);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    if (json.getString("code").equals("-3001")) {
                        vercode.setVisibility(View.GONE);
                        userpassword.setHint("密码");
                        userpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//输入类型为密码
                        login.setText("登陆");
                        forgetpassword.setVisibility(View.VISIBLE);
                    } else {
                        vercode.setVisibility(View.VISIBLE);
                        userpassword.setHint("验证码");
                        login.setText("下一步");
                        userpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        forgetpassword.setVisibility(View.GONE);
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
                customProgressDialog.cancel();
            }
        });
    }

    //Todo 修改密码时检测是否已经注册
    private void detectionUser_1(String phone) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/isRegist");
        params.addBodyParameter("tel", phone);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    if (json.getString("code").equals("-3001")) {
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(findvercode, 60000, 1000);
                        mCountDownTimerUtils.start();
                        SMSSDK.getInstance().getSmsCodeAsyn(stringfindphone, "1", new SmscodeListener() {
                            @Override
                            public void getCodeSuccess(String s) {

                            }

                            @Override
                            public void getCodeFail(int i, String s) {

                            }
                        });
                    } else {
                        Toast.makeText(context, "该电话还未注册", Toast.LENGTH_SHORT).show();
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
    private void requestRegister(String phone, String pwd) {
        customProgressDialog = new CustomProgressDialog(context, "玩命加载中...", R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/regist");
        params.addBodyParameter("tel", phone);
        params.addBodyParameter("password", pwd);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                RegistBean registBean=gson.fromJson(result,RegistBean.class);
                if (registBean.getCode()==3002){
                    editor.putString("usertag", "1");
                    editor.putString("userid", registBean.getData().getId());
                    editor.putString("usertype", registBean.getData().getUtype());
                    editor.putString("usertimes", registBean.getData().getIsfres());
                    editor.commit();
                    dialog1.cancel();
                    if (registBean.getData().getUtype().equals("3")){
                        Intent intent = new Intent(context, SubmitActivity.class);
                        context.startActivity(intent);
                    }
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("tag", "请求失败");
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

    //TODO 忘记密码
    private void updatePwd(String phone, String pwd) {
        Log.e("tag", "修改密码参数" + phone + pwd);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/setPwd");
        params.addBodyParameter("tel", phone);
        params.addBodyParameter("password", pwd);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (jsonObject.getString("code").equals("3003")){
                        dialog1.cancel();
                        loginDialog();
                        Toast.makeText(context, "密码已修改，请重新登录", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "修改密码失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "修改密码失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void updatepwdDilog(String title) {
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
                            Log.v("tag", "注册信息" + stringfindphone);
                            updatePwd(stringfindphone, stringispwd);
                            dialog1.cancel();
                        }

                    }
                }
            }
        });
    }
}
