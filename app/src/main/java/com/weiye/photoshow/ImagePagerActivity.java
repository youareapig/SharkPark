package com.weiye.photoshow;




import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.weiye.utils.SingleModleUrl;
import com.weiye.zl.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerActivity extends FragmentActivity {
    private static final String STATE_POSITION = "STATE_POSITION";
    private HackyViewPager pager;
    private TextView textView;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        pager= (HackyViewPager) findViewById(R.id.pager);
        textView= (TextView) findViewById(R.id.indictor);
        Intent intent=getIntent();
        list=intent.getStringArrayListExtra("photoarr");
        for (int i=0;i<list.size();i++){
            Log.d("tag","图片地址"+list.get(i));
        }
        pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(),list));
        CharSequence text=getString(R.string.viewpager_indicator,1,pager.getAdapter().getCount());
        textView.setText(text);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CharSequence text=getString(R.string.viewpager_indicator,position+1,pager.getAdapter().getCount());textView.setText(text);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private List<String> mList;
        public ImagePagerAdapter(FragmentManager fm, List<String> mList) {
            super(fm);
            this.mList=mList;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(SingleModleUrl.singleModleUrl().getImgUrl()+mList.get(position));
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

}
