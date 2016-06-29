package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.util.Log;
import android.widget.LinearLayout;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

import java.io.IOException;

/**
 * author: suzeyu on 16/6/27 18:02
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  练习Paint之setXfermode 橡皮擦, 水纹汉字, 心电图移动
 */
public class SuGraphicsSetXfermodeHighActivity extends BaseActivity{
    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        final LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_root);


        //自定义view -- PorterDuffXfermode_2的实例演示
        CusDrawSetXfermode2PorterDuffCombat_2 cusDrawSetXfermode2PorterDuffCombat2 = new CusDrawSetXfermode2PorterDuffCombat_2(getApplicationContext());


        ll_root.addView(cusDrawSetXfermode2PorterDuffCombat2);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
