package com.weiye.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weiye.utils.ClassPathResource;
import com.weiye.utils.CountDownTimerUtils;
import com.weiye.zl.MyMaterialActivity;
import com.weiye.zl.R;
import com.weiye.zl.SettingActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DELL on 2017/4/6.
 */
public class University_Fragment extends Fragment implements View.OnClickListener {
    private TextView infomation;
    private CircleImageView myhead;
    private Uri uri;
    private ContentResolver contentResolver;
    private FileOutputStream[] fileOutputStream = {null};
    private Bitmap bitmap, bitmap1;
    private String base64, base64_1, fileName, stringphone, stringpassword, stringlogin, stringpwd, stringispwd;
    private AutoRelativeLayout online, setting, myCourse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.universityfragment, container, false);
        infomation = (TextView) view.findViewById(R.id.makeInfomation);
        myhead = (CircleImageView) view.findViewById(R.id.myhead);
        online = (AutoRelativeLayout) view.findViewById(R.id.line);
        setting = (AutoRelativeLayout) view.findViewById(R.id.setting);
        myCourse = (AutoRelativeLayout) view.findViewById(R.id.myCourse);
        infomation.setOnClickListener(this);
        myhead.setOnClickListener(this);
        online.setOnClickListener(this);
        setting.setOnClickListener(this);
        myCourse.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.makeInfomation:
                Intent intent = new Intent(getActivity(), MyMaterialActivity.class);
                startActivity(intent);
                break;
            case R.id.myhead:
                //TODO 实现点击拍照或者选择后对话框消失
                final AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View layout = inflater.inflate(R.layout.layout_camera_control,
                        null);
                builder.setView(layout);
                builder.show();
                layout.findViewById(R.id.photograph).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 2);
                        builder.cancel();
                    }
                });
                layout.findViewById(R.id.photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                        builder.cancel();
                    }
                });

                break;
            case R.id.line:
                Intent intentTel = new Intent(Intent.ACTION_CALL);
                intentTel.setData(Uri.parse("tel:" + "028-18181818"));
                startActivity(intentTel);
                break;
            case R.id.setting:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.myCourse:
                loginDialog();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == 1) {
            uri = data.getData();
            contentResolver = getActivity().getContentResolver();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumns, null, null, null);
            cursor.moveToFirst();
            try {
                bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                base64 = Bitmap2StrByBase64(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myhead.setImageBitmap(bitmap);
        } else if (requestCode == 2) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            bitmap1 = (Bitmap) bundle.get("data");
            File file = new File("/sdcard/myImage/");
            file.mkdirs();// 创建文件夹
            fileName = "/sdcard/myImage/" + name;
            base64_1 = Bitmap2StrByBase64(bitmap1);
            try {
                fileOutputStream[0] = new FileOutputStream(fileName);
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream[0]);
                myhead.setImageBitmap(bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream[0].flush();
                    fileOutputStream[0].close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //TODO 将位图转换成base64编码
    private String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩  
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //TODO 登陆对话框
    private void loginDialog() {
        final EditText userphone, userpassword;
        final TextView login, vercode,forgetpassword;
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View layout = inflater.inflate(R.layout.login, null);
        dialog.setView(layout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        userphone = (EditText) layout.findViewById(R.id.loginTel);
        userpassword = (EditText) layout.findViewById(R.id.loginPassword);
        login = (TextView) layout.findViewById(R.id.login);
        vercode = (TextView) layout.findViewById(R.id.vercode);
        forgetpassword= (TextView) layout.findViewById(R.id.forgetpassword);
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
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                } else {
                    login.setEnabled(true);
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                    if (stringphone.length() == 11) {
                        if (stringphone.equals("15983302246")) {
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
                    Toast.makeText(getActivity(), "请输入验证码或密码!", Toast.LENGTH_SHORT).show();
                } else {
                    if (stringlogin.equals("登陆")) {
                        ClassPathResource classPathResource = new ClassPathResource();
                        boolean isPhone = classPathResource.isMobileNO(stringphone);
                        if (isPhone == false) {
                            Toast.makeText(getActivity(), "请输入正确的电话号码!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("tag", "登陆成功");
                            dialog.cancel();
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
                    Toast.makeText(getActivity(), "请输入正确的电话号码!", Toast.LENGTH_SHORT).show();
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
        final EditText setpassword, ispassword;
        final TextView regist,longinTitle1;
        final AlertDialog dialog1 = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.regist, null);
        dialog1.setView(view);
        dialog1.setCanceledOnTouchOutside(false);
        dialog1.show();
        exit1 = (ImageView) view.findViewById(R.id.exit1);
        setpassword = (EditText) view.findViewById(R.id.password);
        ispassword = (EditText) view.findViewById(R.id.ispassword);
        regist = (TextView) view.findViewById(R.id.regist);
        longinTitle1= (TextView) view.findViewById(R.id.longinTitle1);
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
                    regist.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                } else {
                    regist.setEnabled(true);
                    regist.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                }
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringpwd = setpassword.getText().toString().trim();
                stringispwd = ispassword.getText().toString().trim();
                if (TextUtils.isEmpty(stringpwd) || TextUtils.isEmpty(stringispwd)) {
                    Toast.makeText(getActivity(), "请输入密码!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!stringpwd.equals(stringispwd)) {
                        Toast.makeText(getActivity(), "两次密码不一致，请确认后输入!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (stringpwd.length() < 6) {
                            Toast.makeText(getActivity(), "密码不能少于6位", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("tag", "注册成功直接登陆");
                            dialog1.cancel();
                        }

                    }
                }
            }
        });
    }
    private void forgetpassword(){
        final TextView findphone,findpwd,findnext,findvercode;
        final ImageView findexit;
        final AlertDialog dialog2 = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.forgetpassword, null);
        dialog2.setView(view);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.show();
        findphone= (TextView) view.findViewById(R.id.loginTel1);
        findpwd= (TextView) view.findViewById(R.id.loginPassword1);
        findexit= (ImageView) view.findViewById(R.id.exit3);
        findvercode= (TextView) view.findViewById(R.id.vercode1);
        findnext= (TextView) view.findViewById(R.id.next);
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
                if (TextUtils.isEmpty(findphone.getText().toString().trim())){
                    findvercode.setEnabled(false);
                    findvercode.setBackground(getActivity().getResources().getDrawable(R.drawable.vercode));
                    findnext.setEnabled(false);
                    findnext.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                }else {
                    if (isPhone2==false){
                        findvercode.setEnabled(false);
                        findvercode.setBackground(getActivity().getResources().getDrawable(R.drawable.vercode));
                    }else {
                        findvercode.setEnabled(true);
                        findvercode.setBackground(getActivity().getResources().getDrawable(R.drawable.vercode1));
                    }
                    findnext.setEnabled(true);
                    findnext.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));

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
                if (TextUtils.isEmpty(findphone.getText().toString().trim())||TextUtils.isEmpty(findpwd.getText().toString().trim())){
                    Toast.makeText(getActivity(), "请输入验证码!", Toast.LENGTH_SHORT).show();
                }else {

                        dialog2.cancel();
                        registDialog("重设密码");


                }
            }
        });
    }
}
