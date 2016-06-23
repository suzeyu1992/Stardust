package com.szy.stardust.fm.home.insidefrg.first.graphics.bezierripple;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.Settings;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * author: suzeyu on 16/6/23 17:12
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  自定义view水波纹效果
 */
public class CusDrawRippleView extends View{
    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 1000; //定义波长的长度
    public int dx;
    private float mDecrease = 1;          //递减的高度
    int originY = 0;


    public CusDrawRippleView(Context context) {
        super(context);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        if (originY>getHeight()){
            originY = 0;
        }
        //取出波长的一半,  在贝塞尔曲线直接放置在控制点的x位置上
        int halfWaveLen = mItemWaveLength/2;
        originY += 10;
        //移动的屏幕的超出左边一个波长的位置,  保证屏幕的边缘整齐
        mPath.moveTo(-mItemWaveLength+dx,originY);

        //同过整个屏幕的宽度与波长的比值, 来确定画出多少个连续的波纹.
        for (int i = -mItemWaveLength;i<=getWidth()+mItemWaveLength;i+=mItemWaveLength){
            //画出前半个波纹, 显示应该为上半部的波浪
            mPath.rQuadTo(halfWaveLen/2,-50,halfWaveLen,0);
            //画出后半个波纹, 显示应该为下半部的波浪
            mPath.rQuadTo(halfWaveLen/2,50,halfWaveLen,0);
        }

        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }
    long lastTime = System.currentTimeMillis();
    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (System.currentTimeMillis() - lastTime > 16){
                    dx = (int)animation.getAnimatedValue();
                    mDecrease = (((int)animation.getAnimatedValue())/((float)mItemWaveLength));
                    invalidate();
                }

            }
        });
        animator.start();
    }
}
