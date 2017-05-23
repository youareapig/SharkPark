package com.weiye.zl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weiye.myview.ObservableScrollView;
import com.weiye.utils.SingleModleUrl;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class SchoolVideoActivity extends AutoLayoutActivity implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.schoolvideo_video)
    JCVideoPlayerStandard schoolvideoVideo;
    @BindView(R.id.titleimage)
    RelativeLayout titleimage;
    @BindView(R.id.scrollview2)
    ObservableScrollView scrollview2;
    @BindView(R.id.back1)
    RelativeLayout back1;
    @BindView(R.id.share1)
    RelativeLayout share1;
    @BindView(R.id.title1)
    RelativeLayout title1;
    @BindView(R.id.huodongsp_ms)
    TextView huodongspMs;
    private Unbinder unbinder;
    private String videourl, videoimg, content;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_video);
        unbinder = ButterKnife.bind(this);
        changTitle();
        Intent intent = getIntent();
        videourl = intent.getStringExtra("spdz");
        videoimg = intent.getStringExtra("txdz");
        content = intent.getStringExtra("hdms");
        Log.e("tag","视频地址-------------"+videourl);
        huodongspMs.setText(content);
        schoolvideoVideo.setUp(videourl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "鲨鱼公园");
        ImageLoader.getInstance().displayImage(SingleModleUrl.singleModleUrl().getImgUrl() + videoimg, schoolvideoVideo.thumbImageView);
        schoolvideoVideo.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void changTitle() {
        scrollview2.smoothScrollTo(0, 20);
        title1.setBackgroundColor(Color.argb(0, 0xfd, 0x91, 0x5b));
        ViewTreeObserver observer = titleimage.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleimage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = titleimage.getHeight();
                titleimage.getWidth();
                scrollview2.setScrollViewListener(SchoolVideoActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= height) {
            float scale = (float) y / height;
            float alpha = (255 * scale);
            title1.setBackgroundColor(Color.argb((int) alpha, 49, 189, 240));
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.sharkpark.cn/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("http://www.sharkpark.cn/");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.sharkpark.cn/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("http://www.sharkpark.cn/");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.sharkpark.cn/");
        oks.setImageUrl(SingleModleUrl.singleModleUrl().getImgUrl() + videoimg);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("http://www.sharkpark.cn/");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });

// 启动分享GUI
        oks.show(this);
    }

    @OnClick({R.id.back1, R.id.share1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.share1:
                showShare();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (JCVideoPlayer.backPress()) {
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
