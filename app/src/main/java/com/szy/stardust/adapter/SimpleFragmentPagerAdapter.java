package com.szy.stardust.adapter;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;;


import com.szy.stardust.R;
import com.szy.stardust.data.bean.DBMainFragment;
import com.szy.stardust.fm.home.myinfo.HomePagerFragment;
import com.szy.stardust.util.BitmapUtil;

import java.util.ArrayList;

/**
 * author: suzeyu on 16/6/6 18:09
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 一级页面的最外部viewpager的适配器加载类
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<DBMainFragment> mMainFragments;         //要装载的数据集合
    private final Context mContext;

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context, ArrayList<DBMainFragment> mainFragments) {
        super(fm);
        this.mMainFragments = mainFragments;
        this.mContext = context;
    }




    @Override
    public Fragment getItem(int position) {
        return mMainFragments.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mMainFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];

        SpannableString sb = new SpannableString(" ");
        if (position!= 0){
            Drawable image = mContext.getResources().getDrawable(mMainFragments.get(position).mResource);
            image.setAlpha(70);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            Drawable image = mContext.getResources().getDrawable(mMainFragments.get(position).mResource);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());

            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return sb;
    }


}
