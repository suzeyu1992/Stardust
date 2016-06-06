package com.szy.stardust.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author: suzeyu on 16/6/6 15:08
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 默认为当前工程所有activity的上基础类
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLoad();
    }

    /**
     * 开始对界面view进行初始化
     */
    protected abstract void initView();

    /**
     * 对需要的view进行监听
     */
    protected abstract void initListener();

    /**
     * 视图层加载之后,准备进行数据显示的初始化
     */
    protected abstract void initLoad();
}
