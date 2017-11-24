package com.weiye.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;
import com.weiye.adapter.ChooseBabyAdapter;
import com.weiye.adapter.ChooseClassAdapter;
import com.weiye.data.BabyBean;
import com.weiye.data.TeacherClassBean;
import com.weiye.data.UpHeadBean;
import com.weiye.data.UserInfoBean;
import com.weiye.mycourse.MyCoruseActivity;
import com.weiye.myview.CustomProgressDialog;
import com.weiye.utils.CameraUtil;
import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.ManageActivity;
import com.weiye.zl.MyBabyActivity;
import com.weiye.zl.MyMaterialActivity;
import com.weiye.zl.R;
import com.weiye.zl.SettingActivity;
import com.weiye.zl.SubjectActivity;
import com.weiye.zl.TiShiActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
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
import java.io.FileNotFoundException;
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
    private AutoRelativeLayout online, setting, myCourse, managecourse, myClass;
    private String userID, userType;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AutoLinearLayout main6;
    private ImageView vipImage;
    private Uri imgUrl;
    private String telnumber = "";
    private AlertDialog builder;

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
        managecourse = (AutoRelativeLayout) view.findViewById(R.id.managecourse);
        myClass = (AutoRelativeLayout) view.findViewById(R.id.myClass);
        vipImage = (ImageView) view.findViewById(R.id.vipimage);
        myClass.setOnClickListener(this);
        infomation.setOnClickListener(this);
        myhead.setOnClickListener(this);
        online.setOnClickListener(this);
        setting.setOnClickListener(this);
        myCourse.setOnClickListener(this);
        managecourse.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("UserTag", getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userID = sharedPreferences.getString("userid", "未知");
        userType = sharedPreferences.getString("usertype", "未知");
        EventBus.getDefault().register(this);
        //TODO 用户类型 1 管理员；2 会员；3 非会员；4 老师
        if (userType.equals("1")) {
            myClass.setVisibility(View.GONE);
            myCourse.setVisibility(View.GONE);
            vipImage.setVisibility(View.GONE);
        }
        if (userType.equals("2")) {
            managecourse.setVisibility(View.GONE);
            vipImage.setVisibility(View.VISIBLE);
            myClass.setVisibility(View.GONE);
        }
        if (userType.equals("3")) {
            myClass.setVisibility(View.GONE);
            managecourse.setVisibility(View.GONE);
            vipImage.setVisibility(View.GONE);
        }
        if (userType.equals("4")) {
            managecourse.setVisibility(View.GONE);
            myCourse.setVisibility(View.GONE);
            vipImage.setVisibility(View.GONE);
        }
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
                        MPermissions.requestPermissions(University_Fragment.this, 30, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        builder.cancel();
                    }
                });

                break;
            case R.id.line:
                getPhoneNumber();
                break;
            case R.id.setting:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.myCourse:
                //TODO 会员
                if (userType.equals("2")) {
                    babyvisit();
                } else {
                    Intent intent2 = new Intent(getActivity(), TiShiActivity.class);
                    startActivity(intent2);
                }

                break;
            case R.id.managecourse:
                Intent intent3 = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent3);
                break;
            case R.id.myClass:
                teachervisit();

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case 1:
                if (data != null) {
                    imgUrl = CameraUtil.getTempUri();
                    startActivityForResult(CameraUtil.cropPhoto(data.getData(), imgUrl, 200, 200), 3);
                } else {
                    startActivityForResult(CameraUtil.cropPhoto(imgUrl, imgUrl, 200, 200), 3);
                }
                break;
            case 2:
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                    Log.i("TestFile",
                            "SD card is not avaiable/writeable right now.");
                    return;
                }
                String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                FileOutputStream fileOutputStream = null;
                File file = new File("/sdcard/myImage/");
                file.mkdirs();// 创建文件夹
                String fileName = "/sdcard/myImage/" + name;
                try {
                    fileOutputStream = new FileOutputStream(fileName);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    uploadhead(fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileOutputStream.close();
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                String imgpath = CameraUtil.getPathFromUri(getActivity(), imgUrl);
                uploadhead(imgpath);
                break;
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

    @PermissionGrant(30)
    public void requestPhotoSuccess() {
        CameraUtil.selectPhoto();
        startActivityForResult(Intent.createChooser(CameraUtil.selectPhoto(), "选择照片"), 1);
    }

    @PermissionDenied(30)
    public void requestPhotoFailed() {
        Toast.makeText(getActivity(), "你没有访问相册权限", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(20)
    public void requestCameraFailed() {
        Toast.makeText(getActivity(), "你没有访问相机权限", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(10)
    public void requestCallFailed() {
        Toast.makeText(getActivity(), "你没有拨打电话权限", Toast.LENGTH_SHORT).show();
    }


    //TODO 拨打电话
    private void call() {
        Intent intentTel = new Intent(Intent.ACTION_CALL);
        intentTel.setData(Uri.parse("tel:" + telnumber));
        startActivity(intentTel);
    }

    //TODO 相册上传
    private void uploadhead(String path) {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/editPic");
        params.addBodyParameter("id", userID);
        params.addBodyParameter("headpic", new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UpHeadBean bean = gson.fromJson(result, UpHeadBean.class);
                if (bean.getCode() == 3004) {
                    ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getHeadpic(), myhead);
                    Toast.makeText(getActivity(), "头像更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "头像上传失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(getActivity(), null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        main6.setVisibility(View.GONE);
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/myInfo");
        params.addBodyParameter("id", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("tag","资料"+result);
                main6.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                UserInfoBean bean = gson.fromJson(result, UserInfoBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().getHeadpic() == null) {
                        myhead.setImageResource(R.mipmap.head1);
                    } else {
                        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + bean.getData().getHeadpic(), myhead);
                    }
                    myname.setText(bean.getData().getTel());

                } else {
                    Toast.makeText(getActivity(), "获取用户信息失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "网络不佳，请稍后再试", Toast.LENGTH_SHORT).show();
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

    private void getPhoneNumber() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/about");
        params.addBodyParameter("type", "4");
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1000")) {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                        LayoutInflater inflater1 = getActivity().getLayoutInflater();
                        View v = inflater1.inflate(R.layout.callphone, null);
                        dialog.setView(v);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                        TextView textView = (TextView) v.findViewById(R.id.callnumber);
                        telnumber = jsonObject.getString("data");
                        textView.setText(telnumber);
                        v.findViewById(R.id.calloff).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                        v.findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MPermissions.requestPermissions(University_Fragment.this, 10, Manifest.permission.CALL_PHONE);
                                dialog.cancel();
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "网络不佳，获取电话号码失败，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


    private void babyvisit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/mybaby");
        params.addBodyParameter("uid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BabyBean babyBean = gson.fromJson(result, BabyBean.class);
                if (babyBean.getCode() == 3000) {
                    if (babyBean.getData().size() > 1) {
                        builder = new AlertDialog.Builder(getActivity()).create();
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View layout = inflater.inflate(R.layout.choosebaby,
                                null);
                        builder.setView(layout);
                        builder.show();
                        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(new ChooseBabyAdapter(babyBean.getData(), getActivity()));
                    } else if (babyBean.getData().size() == 1) {
                        Intent intent = new Intent(getActivity(), MyBabyActivity.class);
                        intent.putExtra("babyId", babyBean.getData().get(0).getId() + "");
                        startActivity(intent);
                    }
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

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void teachervisit() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Member/myGrade");
        params.addBodyParameter("uid", userID);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TeacherClassBean bean = gson.fromJson(result, TeacherClassBean.class);
                if (bean.getCode() == 3000) {
                    if (bean.getData().size() > 1) {
                        builder = new AlertDialog.Builder(getActivity()).create();
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View layout = inflater.inflate(R.layout.choosebaby,
                                null);
                        builder.setView(layout);
                        builder.show();
                        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(new ChooseClassAdapter(bean.getData(), getActivity()));
                    } else if (bean.getData().size() == 1) {
                        Intent intent4 = new Intent(getActivity(), SubjectActivity.class);
                        editor.putString("ggid", bean.getData().get(0).getId() + "");
                        editor.commit();
                        startActivity(intent4);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("tag", "错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessage(String s) {
        Log.e("tag", "event执行了" + s);
        builder.cancel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
