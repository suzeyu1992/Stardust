package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

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
        final CusDrawSetXfermode2PorterDuffCombat_2 cusDrawSetXfermode2PorterDuffCombat2 = new CusDrawSetXfermode2PorterDuffCombat_2(getApplicationContext());


        ll_root.addView(cusDrawSetXfermode2PorterDuffCombat2);

//        cusDrawSetXfermode2PorterDuffCombat2.setBtm(BitmapFactory.decodeResource(getResources(),R.mipmap.icon_experiment_cs));


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
