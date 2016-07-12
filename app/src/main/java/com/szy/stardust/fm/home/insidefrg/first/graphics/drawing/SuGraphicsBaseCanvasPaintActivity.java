package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.view.View;
import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

/**
 * author: suzeyu on 16/7/4 11:03
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 关于canvas和paint简单实用和常用图形的绘制
 */
public class SuGraphicsBaseCanvasPaintActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        LinearLayout ll_body = (LinearLayout) findViewById(R.id.ll_body);

        CusDrawBaseCanvasPaintView cusDrawBaseCanvasPaintView = new CusDrawBaseCanvasPaintView(getApplicationContext());
        ll_body.addView(cusDrawBaseCanvasPaintView);

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
