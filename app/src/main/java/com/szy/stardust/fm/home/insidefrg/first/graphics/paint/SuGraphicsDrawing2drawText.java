package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.support.v7.app.ActionBar;
import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;
import com.szy.stardust.fm.home.insidefrg.first.graphics.drawtext.CusDrawTextView;

/**
 * author: suzeyu on 16/6/20 12:18
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 自定义控件绘图(1)--- drawText练习
 */
public class SuGraphicsDrawing2drawText extends BaseActivity{
    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        ActionBar supportActionBar = getSupportActionBar();
//        supportActionBar.hide();
        supportActionBar.setTitle("绘图(1)_drawText练习");

        LinearLayout ll_body = (LinearLayout) findViewById(R.id.ll_root);
        ll_body.removeAllViews();
        ll_body.addView(new CusDrawTextView(getApplicationContext()));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
