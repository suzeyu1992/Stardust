package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

/**
 * author: suzeyu on 16/6/27 18:02
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  练习Paint之setXfermode
 */
public class SuGraphicsSetXfermodeActivity extends BaseActivity{
    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        final LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_body);

        //创建自定义view
        //自定义view -- AvoidXfermode的演示
        CusDrawSetXfermodeView cusDrawColorMatrixView = new CusDrawSetXfermodeView(getApplicationContext());

        //自定义view -- PorterDuffXfermode的模式区别展示
        CusDrawSetXfermode2PorterDuffXfermodeView cusDrawSetXfermode2PorterDuffXfermodeView = new CusDrawSetXfermode2PorterDuffXfermodeView(getApplicationContext());

        //自定义view -- PorterDuffXfermode_1的实例演示
        CusDrawSetXfermode2PorterDuffCombat_1 cusDrawSetXfermode2PorterDuffCombat = new CusDrawSetXfermode2PorterDuffCombat_1(getApplicationContext());


        ll_root.addView(cusDrawColorMatrixView);
        ll_root.addView(cusDrawSetXfermode2PorterDuffXfermodeView);
        ll_root.addView(cusDrawSetXfermode2PorterDuffCombat);


    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initLoad() {

    }
}
