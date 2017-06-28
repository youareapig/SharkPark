package com.weiye.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.data.UserInfoBean;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.MyGradeActivity;
import com.weiye.zl.MyMaterialActivity;
import com.weiye.zl.R;
import com.weiye.zl.RestartActivity;
import com.weiye.zl.SettingActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DELL on 2017/4/6.
 */
public class University_Fragment extends Fragment implements View.OnClickListener {
    private TextView infomation, myname;
    private CircleImageView myhead;
    private Uri uri;
    private ContentResolver contentResolver;
    private FileOutputStream[] fileOutputStream = {null};
    private Bitmap bitmap, bitmap1;
    private String base64, base64_1, fileName;
    private AutoRelativeLayout online, setting, myCourse;
    private String userID;
    private SharedPreferences sharedPreferences;
    private AutoLinearLayout main6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.universityfragment, container, false);
        infomation = (TextView) view.findViewById(R.id.makeInfomation);
        myhead = (CircleImageView) view.findViewById(R.id.myhead);
        online = (AutoRelativeLayout) view.findViewById(R.id.line);
        setting = (AutoRelativeLayout) view.findViewById(R.id.setting);
        myCourse = (AutoRelativeLayout) view.findViewById(R.id.myCourse);
        myname = (TextView) view.findViewById(R.id.myname);
        main6 = (AutoLinearLayout) view.findViewById(R.id.main6);
        infomation.setOnClickListener(this);
        myhead.setOnClickListener(this);
        online.setOnClickListener(this);
        setting.setOnClickListener(this);
        myCourse.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
        getUserInfo();
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
                        MPermissions.requestPermissions(University_Fragment.this, 20, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
                MPermissions.requestPermissions(University_Fragment.this, 10, Manifest.permission.CALL_PHONE);
                break;
            case R.id.setting:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.myCourse:
                Intent intent2=new Intent(getActivity(), MyGradeActivity.class);
                startActivity(intent2);
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

            uploadhead(base64);
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
            uploadhead1(base64_1);
        }
    }

    //TODO 将位图转换成base64编码
    private String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.PNG, 40, bos);//参数100表示不压缩  
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }


    //TODO android 6.0后高危权限的申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(10)
    public void requestCallSuccess() {
        call();
    }

    @PermissionGrant(20)
    public void requestCameraSuccess() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 2);
    }


    //TODO 拨打电话
    private void call() {
        Intent intentTel = new Intent(Intent.ACTION_CALL);
        intentTel.setData(Uri.parse("tel:" + "028-18181818"));
        startActivity(intentTel);
    }

    //TODO 相册上传
    private void uploadhead(final String base) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/editPic");
        params.addBodyParameter("id", userID);
        params.addBodyParameter("headpic", "data:image/png;base64,"+base);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (jsonObject.getString("code").equals("3004")){
                        myhead.setImageBitmap(bitmap);
                        Toast.makeText(getActivity(), "头像更新成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "头像上传失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "头像上传失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    //TODO 照相上传
    private void uploadhead1(String base) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/editPic");
        params.addBodyParameter("id", userID);
        params.addBodyParameter("headpic", "data:image/png;base64,"+base);
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    if (jsonObject.getString("code").equals("3004")){
                        Toast.makeText(getActivity(), "头像更新成功", Toast.LENGTH_SHORT).show();
                        try {
                            fileOutputStream[0] = new FileOutputStream(fileName);
                            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream[0]);
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "头像上传失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    //TODO 获取用户信息
    private void getUserInfo() {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity(), "玩命加载中...", R.drawable.frame,R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main6.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "User/myInfo");
        params.addBodyParameter("id", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                main6.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
                if (bean.getCode()==3000){
                    myname.setText(bean.getData().getTel());
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl()+bean.getData().getHeadpic(),myhead);
                }else {
                    Toast.makeText(getActivity(),"获取用户信息失败",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(),"获取用户信息失败",Toast.LENGTH_SHORT).show();
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


}
