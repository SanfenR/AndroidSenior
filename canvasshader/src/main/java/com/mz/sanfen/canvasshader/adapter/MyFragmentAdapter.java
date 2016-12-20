package com.mz.sanfen.canvasshader.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.mz.sanfen.canvasshader.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/20.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    List<Fragment> mData = new ArrayList<>();

    private int[] mImageResId = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    public void setData(List<Fragment> mData) {
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getResources().getDrawable(mImageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
