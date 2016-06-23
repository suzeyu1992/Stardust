package com.szy.stardust.fm.home.insidefrg.first.graphics.bezierripple;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

/**
 * author: suzeyu on 16/6/23 15:20
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  利用贝塞尔曲线 实现手势轨迹界面
 */
public class SuGraphicsGestureTracksActivity extends BaseActivity {
    private int mType = 1;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        final LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_root);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("GestureTracks");

        ll_root.removeAllViews();

        final CusDrawGestureTracks cusDrawGestureTracks = new CusDrawGestureTracks(getApplicationContext());
        final CusDrawRippleView cusDrawRippleView = new CusDrawRippleView(getApplicationContext());
        cusDrawRippleView.startAnim();
        //创建一个重置按钮
        Button button = new Button(getApplicationContext());
        button.setText("reset");
        ll_root.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cusDrawGestureTracks.reset();
            }
        });

        Button bt_replace = new Button(getApplicationContext());
        bt_replace.setText("replace");
        ll_root.addView(bt_replace);
        bt_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType==1){
                    mType = 2;
                    //切换到水波纹中
                    ll_root.removeView(cusDrawGestureTracks);
                    ll_root.addView(cusDrawRippleView);
                    //cusDrawRippleView.startAnim();

                }else{
                    mType = 1;
                    //切换到手势轨迹绘制
                    ll_root.removeView(cusDrawRippleView);
                    ll_root.addView(cusDrawGestureTracks);
                }
            }
        });

        ll_root.addView(cusDrawGestureTracks);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {

    }
}
