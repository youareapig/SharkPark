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
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private String base64, base64_1, fileName, stringphone, stringpassword;
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

    private void loginDialog() {
        final EditText userphone, userpassword;
        final TextView login, vercode;
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
        stringphone = userphone.getText().toString();
        stringpassword = userpassword.getText().toString();
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("tag1", "改变之前" + charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("tag1", "正在改变" + charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("tag1", "改变之后" + editable);
                if (TextUtils.isEmpty(editable)) {
                    login.setEnabled(false);
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                } else {
                    login.setEnabled(true);
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                    if (editable.length() == 11) {
                        Log.e("tag", "验证电话");
                        if (editable.equals("15983302246")) {
                            Log.e("tag", "电话存在");
                            vercode.setVisibility(View.GONE);
                            userpassword.setHint("密码");
                            userpassword.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        } else if (!editable.equals("15983302246")){
                            Log.e("tag", "电话不存在");
                            vercode.setVisibility(View.VISIBLE);
                            userpassword.setHint("验证码");
                            userpassword.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                    }
                }
            }
        });
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    login.setEnabled(false);
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                } else {
                    login.setEnabled(true);
                    login.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                }
            }
        });
        if (TextUtils.isEmpty(stringphone) || TextUtils.isEmpty(stringpassword)) {
            login.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
            login.setEnabled(false);
        } else {
            login.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            login.setEnabled(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "登陆");
            }
        });
        //TODO 点击退出
        layout.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }
}
