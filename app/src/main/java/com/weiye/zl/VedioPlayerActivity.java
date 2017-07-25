package com.weiye.zl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.weiye.utils.SingleModleUrl;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import qiu.niorgai.StatusBarCompat;

public class VedioPlayerActivity extends AppCompatActivity {
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_player);
        StatusBarCompat.translucentStatusBar(this, false);
        Intent intent=getIntent();
        videoUrl=intent.getStringExtra("videoUrl");
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp(SingleModleUrl.singleModleUrl().getImgUrl()+videoUrl,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "鲨鱼公园");
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
