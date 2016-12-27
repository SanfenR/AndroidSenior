package com.mz.sanfen.canvasshader;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mz.sanfen.canvasshader.adapter.MyFragmentAdapter;
import com.mz.sanfen.canvasshader.fragment.BitmapFragment;
import com.mz.sanfen.canvasshader.fragment.LinearFragment;
import com.mz.sanfen.canvasshader.fragment.RadialFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager frame_pager;

    List<Fragment> mData;

    MyFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        frame_pager = (ViewPager) findViewById(R.id.frame_pager);

        initView();
    }





    private void initView() {

        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(), this);

        mData = new ArrayList<>();
        mData.add(BitmapFragment.newInstance());
        mData.add(LinearFragment.newInstance());
        mData.add(RadialFragment.newInstance());

        mAdapter.setData(mData);

        frame_pager.setAdapter(mAdapter);
        tab_layout.setupWithViewPager(frame_pager);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
    }
}
