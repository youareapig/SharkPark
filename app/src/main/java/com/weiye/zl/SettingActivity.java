package com.weiye.zl;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.updateversion.UpdateService;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingActivity extends AutoLayoutActivity {
    @BindView(R.id.materialBack)
    RelativeLayout materialBack;
    @BindView(R.id.about)
    RelativeLayout about;
    @BindView(R.id.ideal)
    RelativeLayout ideal;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.updateversion)
    RelativeLayout updateversion;
    @BindView(R.id.btnloginout)
    TextView btnloginout;
    @BindView(R.id.version)
    TextView version;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String updateUrl, versionID;
    private int locationVersion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        MyApplication application = (MyApplication) getApplication();
        locationVersion = application.location;
        updateVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.materialBack, R.id.about, R.id.ideal, R.id.updateversion, R.id.btnloginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.materialBack:
                finish();
                break;
            case R.id.about:
                Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.ideal:
                Intent intent1 = new Intent(SettingActivity.this, IdealActivity.class);
                startActivity(intent1);
                break;
            case R.id.updateversion:
                if (Integer.parseInt(versionID) > locationVersion) {
                    final AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this).create();
                    LayoutInflater inflater = getLayoutInflater();
                    View v = inflater.inflate(R.layout.update, null);
                    dialog.setView(v);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                    v.findViewById(R.id.unUpdate).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SettingActivity.this, UpdateService.class);
                            intent.putExtra("apkUrl", updateUrl);
                            startService(intent);
                            dialog.cancel();
                        }
                    });
                } else {
                    final AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this).create();
                    LayoutInflater inflater = getLayoutInflater();
                    View v = inflater.inflate(R.layout.bestversion, null);
                    dialog.setView(v);
                    dialog.show();
                    Window window=dialog.getWindow();
                    WindowManager.LayoutParams layoutParams=window.getAttributes();
                    layoutParams.alpha=0.6f;
                    layoutParams.width=650;
                    window.setAttributes(layoutParams);
                    dialog.setCanceledOnTouchOutside(true);

                }


                break;
            case R.id.btnloginout:
                final AlertDialog dialog = new AlertDialog.Builder(this).create();
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.oncesure, null);
                dialog.setView(v);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                v.findViewById(R.id.off).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                v.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editor.putString("usertag", "0");
                        editor.putString("userid", "0");
                        editor.putString("usertype", "0");
                        editor.commit();
                        Intent intent2 = new Intent(SettingActivity.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        dialog.cancel();
                    }
                });
                break;
        }
    }

    //TODO 检测版本更新
    private void updateVersion() {
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/updateInfo");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    version.setText(json.get("versionName").toString());
                    updateUrl = json.getString("url");
                    versionID = json.getString("version");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag", "版本更新错误");
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
