package com.szy.stardust.fm.home;


import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.util.Patterns;

import com.szy.stardust.R;
import com.szy.stardust.adapter.SimpleFragmentPagerAdapter;
import com.szy.stardust.base.BaseActivity;
import com.szy.stardust.data.bean.MyInfoBean;
import com.szy.stardust.util.CheckUtil;
import com.szy.stardust.util.UIUtils;

import java.util.regex.Matcher;

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
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tl_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mDrawables = new Drawable[imageResId.length];
        for (int x=0; x<imageResId.length;x++){
            Drawable drawable = getResources().getDrawable(imageResId[x]);
            mDrawables[x] = drawable;
//            drawable.setBounds(0,0, UIUtils.dip2px(48),UIUtils.dip2px(48));
//            if (x!=0)drawable.setAlpha(122);
//            tabLayout.getTabAt(x).setIcon(drawable);
        }

        mCurrentTab = 0;
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
                             //之前选中icon  变为浅色未选中状态
                            mDrawables[mCurrentTab].setAlpha(122);
                            mDrawables[position].setAlpha(255);

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
