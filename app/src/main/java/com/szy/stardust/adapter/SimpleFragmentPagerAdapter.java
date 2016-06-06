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
import android.text.style.ImageSpan;;


import com.szy.stardust.R;
import com.szy.stardust.fm.home.myinfo.HomePagerFragment;
import com.szy.stardust.util.BitmapUtil;

/**
 * author: suzeyu on 16/6/6 18:09
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : viewpager的适配器加载类
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3","tab4"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    private int[] imageResId = {
            R.mipmap.tab_home_main,
            R.mipmap.tab_home_find,
            R.mipmap.tab_home_notifications,
            R.mipmap.tab_home_profile

    };



    @Override
    public Fragment getItem(int position) {
        return HomePagerFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];

        SpannableString sb = new SpannableString(" ");
        if (position!= 0){

            Drawable image = context.getResources().getDrawable(imageResId[position]);
            image.setAlpha(122);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else{
            Drawable image = context.getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());

            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return sb;
    }


}
