package com.szy.stardust.fm.home.insidefrg.first.graphics.drawtext;

import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

import java.io.IOException;

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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
