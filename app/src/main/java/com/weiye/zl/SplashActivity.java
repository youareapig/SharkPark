package com.weiye.zl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import qiu.niorgai.StatusBarCompat;

public class SplashActivity extends AutoLayoutActivity {
    private ImageView splash;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("myshared", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        StatusBarCompat.translucentStatusBar(this, false);
        editor.putString("isdilog","1");
        editor.commit();
        splash= (ImageView) findViewById(R.id.splash);
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            flag=sharedPreferences.getString("flag","other");
                            if (flag.equals("first")){
                                Intent intent = new Intent();
                                intent.setClass(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent();
                                intent.setClass(SplashActivity.this, LeadActivity.class);
                                editor.putString("flag", "first").apply();
                                startActivity(intent);
                                finish();
                            }
                        }
                    },1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
