package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

/**
 * author: suzeyu on 16/6/27 14:50
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  展示Paint的setColorFilter的使用效果
 */
public class SuGraphicsColorFilterActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        final LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_body);

        //创建自定义view
        CusDrawColorFilterView cusDrawColorMatrixView = new CusDrawColorFilterView(getApplicationContext());
        ll_root.addView(cusDrawColorMatrixView);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
