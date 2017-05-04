package com.weiye.zl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.weiye.adapter.LeaderAdapter;
import com.weiye.lead.Five;
import com.weiye.lead.Four;
import com.weiye.lead.One;
import com.weiye.lead.Three;
import com.weiye.lead.Two;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;

public class LeadActivity extends AutoLayoutActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.leadviewpager)
    ViewPager leadviewpager;
    @BindView(R.id.rbt1)
    RadioButton rbt1;
    @BindView(R.id.lead_radiogroup)
    RadioGroup leadRadiogroup;
    private Unbinder unbinder;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add(new One());
        list.add(new Two());
        list.add(new Three());
        list.add(new Four());
        list.add(new Five());
        FragmentManager fm = this.getSupportFragmentManager();
        leadviewpager.setAdapter(new LeaderAdapter(fm, list));
        leadviewpager.addOnPageChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) leadRadiogroup.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
