package com.weiye.zl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weiye.photoshow.ImagePagerActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class AppearanceActivity extends AutoLayoutActivity {
    @BindView(R.id.back2)
    RelativeLayout back2;
    @BindView(R.id.schoolappearance)
    RoundedImageView schoolappearance;
    private Unbinder unbinder;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        unbinder = ButterKnife.bind(this);
        list=new ArrayList<>();
        list.add("http://p1.image.hiapk.com/uploads/allimg/131015/23-131015152128.jpg");
        list.add("http://p1.image.hiapk.com/uploads/allimg/131015/23-131015152131.jpg");
        list.add("http://p3.image.hiapk.com/uploads/allimg/131015/23-131015152132.jpg");
        list.add("http://www.deskcar.com/desktop/fengjing/200895150214/21.jpg");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.back2, R.id.schoolappearance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back2:
                finish();
                break;
            case R.id.schoolappearance:
                Intent intent=new Intent(AppearanceActivity.this, ImagePagerActivity.class);
                intent.putStringArrayListExtra("photoarr", (ArrayList<String>) list);
                startActivity(intent);
                break;
        }
    }
}
