package com.szy.stardust.fm.home;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

import com.szy.stardust.R;
import com.szy.stardust.adapter.SimpleFragmentPagerAdapter;
import com.szy.stardust.base.BaseActivity;
import com.szy.stardust.data.bean.DBMainFragment;
import com.szy.stardust.data.bean.MyInfoBean;
import com.szy.stardust.util.CheckUtil;
import com.szy.stardust.util.UIUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainContract.View {

    public  final String TAG = this.getClass().getSimpleName();
    private MainContract.Persenter mPresenter;                  //控制层的mPresenter
    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private int mCurrentTab;
    private Drawable[] mDrawables;
    private int[] imageResId = {
            R.mipmap.tab_home_main,
            R.mipmap.tab_home_find,
            R.mipmap.tab_home_notifications,
            R.mipmap.tab_home_profile

    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();




        mPresenter = new MainPresenter(this);
        ArrayList<DBMainFragment> dbMainFragments = mPresenter.initLoadViewData();

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext(),dbMainFragments);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tl_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mDrawables = new Drawable[imageResId.length];
        for (int x=0; x<imageResId.length;x++){
            SpannableString sb = new SpannableString(" ");
            Drawable image = getResources().getDrawable(imageResId[x]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mDrawables[x] = image;

        }

        mCurrentTab = 0;


        float density = getResources().getDisplayMetrics().density;
        float densityDpi = getResources().getDisplayMetrics().densityDpi;
        Snackbar.make(viewPager,density+"--"+densityDpi,Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.d(TAG,"select---"+position);
                        if (position != mCurrentTab){
                            SpannableString sb = new SpannableString(" ");

                            //之前选中icon  变为浅色未选中状态
                            mDrawables[mCurrentTab].setAlpha(70);
                            ImageSpan imageSpan = new ImageSpan(mDrawables[mCurrentTab], ImageSpan.ALIGN_BOTTOM);
                            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tabLayout.getTabAt(mCurrentTab).setText(sb);

                            SpannableString sb1 = new SpannableString(" ");
                            //改变选中时的tab   有浅到深   122--255
                            mDrawables[position].setAlpha(255);
                            ImageSpan imageSpan1 = new ImageSpan(mDrawables[position], ImageSpan.ALIGN_BOTTOM);
                            sb1.setSpan(imageSpan1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tabLayout.getTabAt(position).setText(sb1);

                            //刷新判断条件
                            mCurrentTab = position;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                Log.e(TAG,"onTabSelected="+position);
//                if (position != mCurrentTab){
//                    //之前选中icon  变为浅色未选中状态
//                    tabLayout.getTabAt(mCurrentTab).getIcon().setAlpha(122);
//                    tab.getIcon().setAlpha(255);
//
//                    mCurrentTab = position;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                Log.e(TAG,"onTabUnselected");
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                Log.e(TAG,"onTabReselected");
//
//            }
//        });
    }

    @Override
    protected void initLoad() {
        mPresenter.start();
    }

    @Override
    public void clickPager(int pagerIndex) {

    }

    @Override
    public void setMyInfo(boolean isLogin, MyInfoBean myinfo) {

    }

    @Override
    public void setPresenter(MainContract.Persenter presenter) {
        mPresenter = CheckUtil.checkNotNull(presenter);
    }


}
