package com.szy.stardust.fm.home;

import android.nfc.Tag;
import android.util.Log;

import com.szy.stardust.R;
import com.szy.stardust.data.bean.DBMainFragment;
import com.szy.stardust.fm.home.insidefrg.first.firstFragment;
import com.szy.stardust.fm.home.insidefrg.fourth.FourthFragment;
import com.szy.stardust.fm.home.insidefrg.second.SecondFragment;
import com.szy.stardust.fm.home.insidefrg.third.ThirdFragment;

import java.util.ArrayList;

/**
 * author: suzeyu on 16/6/6 16:36
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 首页的控制类
 */
public class MainPresenter implements MainContract.Persenter {

    private final MainContract.View mViewIns;               //接收到的view层的对象
    private final ArrayList<DBMainFragment> mDbMainFragments;

    public MainPresenter(MainContract.View mViewIns ){
        this.mViewIns = mViewIns;
        mViewIns.setPresenter(this);

        //对要加载的布局和tab的icon进行数据创建封装
        mDbMainFragments = new ArrayList<>();

        DBMainFragment dbMainFragment1 = new DBMainFragment();
        dbMainFragment1.mFragment = new firstFragment();
        dbMainFragment1.mResource = imageResId[0];
        mDbMainFragments.add(dbMainFragment1);

        DBMainFragment dbMainFragment2 = new DBMainFragment();
        dbMainFragment2.mFragment = new SecondFragment();
        dbMainFragment2.mResource = imageResId[1];
        mDbMainFragments.add(dbMainFragment2);

        DBMainFragment dbMainFragment3 = new DBMainFragment();
        dbMainFragment3.mFragment = new ThirdFragment();
        dbMainFragment3.mResource = imageResId[2];
        mDbMainFragments.add(dbMainFragment3);

        DBMainFragment dbMainFragment4 = new DBMainFragment();
        dbMainFragment4.mFragment = new FourthFragment();
        dbMainFragment4.mResource = imageResId[3];
        mDbMainFragments.add(dbMainFragment4);





    }


    @Override
    public void start() {

    }
    private int[] imageResId = {
            R.mipmap.tab_home_main,
            R.mipmap.tab_home_find,
            R.mipmap.tab_home_notifications,
            R.mipmap.tab_home_profile

    };

    @Override
    public ArrayList<DBMainFragment> initLoadViewData() {
        return mDbMainFragments;
    }
}
