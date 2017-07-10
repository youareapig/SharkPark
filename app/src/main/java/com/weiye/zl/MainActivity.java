package com.weiye.zl;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weiye.fragment.Child_Fragment;
import com.weiye.fragment.Park_Fragment;
import com.weiye.fragment.Shark_Fragment;
import com.weiye.fragment.University_Fragment;
import com.weiye.updateversion.UpdateService;
import com.weiye.utils.ExamInternet;
import com.weiye.utils.SingleModleUrl;
import com.weiye.utils.UserLoginDialog;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionGrant;

import org.json.JSONException;
import org.json.JSONObject;
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


public class MainActivity extends AutoLayoutActivity {
    @BindView(R.id.mainFragment)
    LinearLayout mainFragment;
    @BindView(R.id.child)
    RelativeLayout child;
    @BindView(R.id.shark)
    RelativeLayout shark;
    @BindView(R.id.park)
    RelativeLayout park;
    @BindView(R.id.university)
    RelativeLayout university;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private Fragment fragment = new Fragment();
    private List<Fragment> list;
    private int currentIndex;
    private static boolean isExit = false;
    private SharedPreferences sharedPreferences;
    private String updateUrl,updateName,updateContent,updateVersion;
    private int locationVersion = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 检查是否打开网络
        if (new ExamInternet().isConn(this) == false) {
            Intent intent = new Intent(MainActivity.this, SettingInternetActivity.class);
            startActivity(intent);
        }
        unbinder = ButterKnife.bind(this);
        MyApplication application= (MyApplication)getApplication();
        locationVersion=application.location;
        MPermissions.requestPermissions(MainActivity.this, 50, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE);
        //updateVersion();
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        Intent intent = getIntent();
        currentIndex=intent.getIntExtra("fTag", 1);
        Log.d("tag","主页默认"+currentIndex);
        if (currentIndex==3){
            textA.setTextColor(getResources().getColor(R.color.no));
            textB.setTextColor(getResources().getColor(R.color.no));
            textC.setTextColor(getResources().getColor(R.color.no));
            textD.setTextColor(getResources().getColor(R.color.yes));
        }
        list = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 1);
            list.removeAll(list);
            list.add(fragmentManager.findFragmentByTag(0 + ""));
            list.add(fragmentManager.findFragmentByTag(1 + ""));
            list.add(fragmentManager.findFragmentByTag(2 + ""));
            list.add(fragmentManager.findFragmentByTag(3 + ""));
            restoreFragment();

        } else {

            list.add(new Child_Fragment());
            list.add(new Shark_Fragment());
            list.add(new Park_Fragment());
            list.add(new University_Fragment());
            showFragment();
        }
    }

    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!list.get(currentIndex).isAdded()) {
            transaction
                    .hide(fragment)
                    .add(R.id.mainFragment, list.get(currentIndex), "" + currentIndex);  //TODO 默认选中

        } else {
            transaction
                    .hide(fragment)
                    .show(list.get(currentIndex));
        }

        fragment = list.get(currentIndex);

        transaction.commit();

    }

    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            if (i == currentIndex) {
                mBeginTreansaction.show(list.get(i));
            } else {
                mBeginTreansaction.hide(list.get(i));
            }

        }
        mBeginTreansaction.commit();
        fragment = list.get(currentIndex);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @PermissionGrant(50)
    public void requestReadSuccess() {
       updateVersion();
    }

    @OnClick({R.id.child, R.id.shark, R.id.park, R.id.university})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.child:
                textA.setTextColor(getResources().getColor(R.color.yes));
                textB.setTextColor(getResources().getColor(R.color.no));
                textC.setTextColor(getResources().getColor(R.color.no));
                textD.setTextColor(getResources().getColor(R.color.no));
                currentIndex = 0;
                showFragment();

                break;
            case R.id.shark:
                textA.setTextColor(getResources().getColor(R.color.no));
                textB.setTextColor(getResources().getColor(R.color.yes));
                textC.setTextColor(getResources().getColor(R.color.no));
                textD.setTextColor(getResources().getColor(R.color.no));
                currentIndex = 1;
                showFragment();
                break;
            case R.id.park:
                textA.setTextColor(getResources().getColor(R.color.no));
                textB.setTextColor(getResources().getColor(R.color.no));
                textC.setTextColor(getResources().getColor(R.color.yes));
                textD.setTextColor(getResources().getColor(R.color.no));
                currentIndex = 2;
                showFragment();
                break;
            case R.id.university:
                String tag = sharedPreferences.getString("usertag", "0");
                Log.v("aa", tag);
                if (tag.equals("1")) {
                    textA.setTextColor(getResources().getColor(R.color.no));
                    textB.setTextColor(getResources().getColor(R.color.no));
                    textC.setTextColor(getResources().getColor(R.color.no));
                    textD.setTextColor(getResources().getColor(R.color.yes));
                    currentIndex = 3;
                    showFragment();
                } else {
                    new UserLoginDialog(this).loginDialog();
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
    //TODO 检测版本更新
    private void updateVersion(){
        RequestParams params=new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl()+"Index/updateInfo");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json=new JSONObject(result);
                    updateName=json.getString("versionName");
                    updateContent=json.getString("description");
                    updateVersion=json.getString("version");
                    updateUrl=json.getString("url");
                    if (Integer.parseInt(updateVersion)>locationVersion){
                        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                        LayoutInflater inflater = getLayoutInflater();
                        View v = inflater.inflate(R.layout.update, null);
                        dialog.setView(v);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        v.findViewById(R.id.unUpdate).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        TextView textContent= (TextView) v.findViewById(R.id.versionContent);
                        //textContent.setText(updateContent);
                        v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                intent.putExtra("apkUrl", updateUrl);
                                startService(intent);
                                dialog.cancel();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("tag","版本更新错误");
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
