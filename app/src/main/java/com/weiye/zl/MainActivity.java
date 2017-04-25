package com.weiye.zl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weiye.fragment.Child_Fragment;
import com.weiye.fragment.Park_Fragment;
import com.weiye.fragment.Shark_Fragment;
import com.weiye.fragment.University_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qiu.niorgai.StatusBarCompat;


public class MainActivity extends AppCompatActivity {
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
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StatusBarCompat.translucentStatusBar(this, false);
        unbinder = ButterKnife.bind(this);
        list = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
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
                textA.setTextColor(getResources().getColor(R.color.no));
                textB.setTextColor(getResources().getColor(R.color.no));
                textC.setTextColor(getResources().getColor(R.color.no));
                textD.setTextColor(getResources().getColor(R.color.yes));
                currentIndex = 3;
                showFragment();
                break;
        }
    }
}
