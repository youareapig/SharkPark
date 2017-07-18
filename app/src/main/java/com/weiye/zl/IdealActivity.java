package com.weiye.zl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.weiye.myview.CustomProgressDialog;
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
import cn.jpush.sms.SMSSDK;
import cn.jpush.sms.listener.SmscodeListener;
import qiu.niorgai.StatusBarCompat;

public class IdealActivity extends AutoLayoutActivity {
    @BindView(R.id.IdealBack)
    RelativeLayout IdealBack;
    @BindView(R.id.inputIdeal)
    EditText inputIdeal;
    @BindView(R.id.submit)
    Button submit;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal);
        unbinder = ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("UserTag", MODE_PRIVATE);
        userID = sharedPreferences.getString("userid", "未知");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.IdealBack, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IdealBack:
                finish();
                break;
            case R.id.submit:
                String myIdeal = inputIdeal.getText().toString().trim();
                if (TextUtils.isEmpty(myIdeal)) {
                    Toast.makeText(this, "意见不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    init(myIdeal);
                }
                break;
        }
    }

    private void init(String string) {
        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, null, R.drawable.frame, R.style.dialog);
        customProgressDialog.setCanceledOnTouchOutside(false);
        customProgressDialog.show();
        RequestParams params = new RequestParams(SingleModleUrl.singleModleUrl().getTestUrl() + "Index/addMessage");
        params.addBodyParameter("id", userID);
        params.addBodyParameter("content", string);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("code").equals("1001")) {
                        inputIdeal.setText(null);
                        Toast.makeText(IdealActivity.this, "谢谢您的反馈", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(IdealActivity.this, "留言未能提交,请稍后再试", Toast.LENGTH_SHORT).show();
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
                customProgressDialog.cancel();
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
