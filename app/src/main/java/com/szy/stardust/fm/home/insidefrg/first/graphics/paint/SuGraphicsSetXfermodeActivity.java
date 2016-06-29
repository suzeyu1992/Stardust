package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.util.Log;
import android.widget.LinearLayout;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

import java.io.IOException;

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
        CusDrawSetXfermodeView cusDrawColorMatrixView = new CusDrawSetXfermodeView(getApplicationContext());
        CusDrawSetXfermode2PorterDuffXfermodeView cusDrawSetXfermode2PorterDuffXfermodeView = new CusDrawSetXfermode2PorterDuffXfermodeView(getApplicationContext());

        ll_root.addView(cusDrawColorMatrixView);
        ll_root.addView(cusDrawSetXfermode2PorterDuffXfermodeView);


    }

    @Override
    protected void initListener() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder url = new Request.Builder().
                url("http://120.27.37.22:8080/fang/fang/getMyFavorite.do?userId=5");

//        url.addHeader("userId","5");
        okHttpClient.newCall(url.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("su",response.body().string());
            }
        });
    }

    @Override
    protected void initLoad() {

    }
}
