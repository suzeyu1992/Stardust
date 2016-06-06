package com.szy.stardust.fm.home;

/**
 * author: suzeyu on 16/6/6 16:36
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 首页的控制类
 */
public class MainPresenter implements MainContract.Persenter {

    private final MainContract.View mViewIns;               //接收到的view层的对象

    public MainPresenter(MainContract.View mViewIns ){
        this.mViewIns = mViewIns;
        mViewIns.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
