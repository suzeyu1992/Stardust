package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.szy.stardust.R;
import com.szy.stardust.util.UIUtils;

/**
 * author: suzeyu on 16/6/29 12:32
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 对于paint的setXfermode中PorterDuffCombat的实例
 *
 *  橡皮擦, 文字水波纹, 心电图.
 */
public class CusDrawSetXfermode2PorterDuffCombat_2 extends View {

    private  Paint mPaint;
    private  Bitmap mBitmap,dstBmp,srcBmp;
    private  int mCurWidth;            //当前屏幕的宽 pixel
    private  int mCurHeight;           //当前屏幕的高 pixel
    private  DisplayMetrics mCurrentDisplayMetrics;

    private int mItemWaveLength = 1000;     //定义水波谁的波长
    private int dx;                         //定义水波纹水平移动的增量

    private Path mPath,mWavePath;           //手势路径     水波纹路径
    private float mPreX,mPreY;
    private  int count;                 //让水波纹从上之下浮动的变量

    public CusDrawSetXfermode2PorterDuffCombat_2(Context context) {
        super(context);
        //进行初始化操作
        mPaint = new Paint();
        mPath = new Path();
        mWavePath = new Path();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);

        //禁用硬件加速
        //这里有一个问题当如果禁用硬件加速的话,  在onMeasure中指定的大小不能超多当前屏幕的width和height的乘积.
//        setLayerType(LAYER_TYPE_SOFTWARE,null);

        //获取屏幕宽高
        mCurrentDisplayMetrics = UIUtils.getCurrentDisplayMetrics();
        mCurWidth = mCurrentDisplayMetrics.widthPixels;
        mCurHeight = mCurrentDisplayMetrics.heightPixels;

        count = 0;

        startAnim();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurWidth, mCurHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //写出当前View的功能
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mCurWidth, mCurHeight, mPaint);

        //画出橡皮擦
        addGestureClear(canvas);

        writeTextBody(canvas, new String[]{"区域波动, DST_IN"});

        mPaint.reset();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);

        Bitmap BmpSRC = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_graphics_textmul_bg,null);
        Bitmap BmpDST = Bitmap.createBitmap(BmpSRC.getWidth(), BmpSRC.getHeight(), Bitmap.Config.ARGB_8888);

        generageWavePath(BmpSRC,canvas);


        //先清空bitmap上的图像,然后再画上Path
        Canvas c = new Canvas(BmpDST);
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        c.drawPath(mWavePath,mPaint);

        canvas.drawBitmap(BmpSRC,0,0,mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(BmpDST,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(BmpSRC,0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    /**
     * 生成此时的Path
     */
    private void generageWavePath(Bitmap bmpSrc, Canvas canvas){
        Log.e("log",dx/2000f+"");
        if (count > 100)
            count = 0;
        mWavePath.reset();
        int originY = bmpSrc.getHeight()/2  +((int)(mCurrentDisplayMetrics.density * -18));       //加上上面的橡皮擦图片和"区域波动"文字的说明
        canvas.drawLine(0,originY, mCurWidth,originY , mPaint);
        int halfWaveLen = mItemWaveLength/2;
//        mWavePath.moveTo(-mItemWaveLength+dx,dx/((float)mItemWaveLength)*originY*2);
        mWavePath.moveTo(-mItemWaveLength+dx,(++count)/100f*originY*2);
        for (int i = -mItemWaveLength;i<=getWidth()+mItemWaveLength;i+=mItemWaveLength){
            mWavePath.rQuadTo(halfWaveLen/2,mCurrentDisplayMetrics.density * -15,halfWaveLen,0);
            mWavePath.rQuadTo(halfWaveLen/2,mCurrentDisplayMetrics.density * 15,halfWaveLen,0);
        }
//        mPath.lineTo(bmpSrc.getWidth(),bmpSrc.getHeight());
//        mPath.lineTo(0,bmpSrc.getHeight());
        mWavePath.lineTo(mCurWidth,mCurHeight);
        mWavePath.lineTo(0,mCurHeight);
        mWavePath.close();
    }

    /**
     * 实现橡皮擦的效果  利用模式: SRC_OUT
     * @param canvas
     */
    private void addGestureClear(Canvas canvas) {

        //在最底层随便写写字
        mPaint.setTextSize(mCurrentDisplayMetrics.density*20);
        mPaint.setColor(Color.GREEN);
        canvas.drawText("丑八怪呀呀呀呀~~~",mCurWidth/50,mCurWidth/3,mPaint);

        //为了设置手势路径生成bitmap可以明显 增大边框
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(45);
//        创建一个空的bitmap用来存放手势
        Bitmap bmp_gesture = Bitmap.createBitmap(mCurWidth/2 , mCurWidth/2 , Config.ARGB_8888);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        //先把手指轨迹画到目标Bitmap上
        Canvas ci = new Canvas(bmp_gesture);
        ci.drawPath(mPath,mPaint);

        canvas.drawBitmap(bmp_gesture,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mBitmap,null,new RectF(0,0,mCurWidth/2,mCurWidth/2),mPaint);

        //复原
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        canvas.translate(0,mCurWidth/1.9f);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY =event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return true;
//        return super.onTouchEvent(event);
    }



    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }



    /**
     * 在画布的水平线 平分数组中的字符串,显示在这条水平线上
     * @param canvas    画布
     * @param bodyStr   要显示的字符串数组
     */
    private void writeTextBody(Canvas canvas, String[] bodyStr){
        //文字的总高
        float textTotal = mCurrentDisplayMetrics.density * 10;
        //文字的基准线
        float textBaseline = mCurrentDisplayMetrics.density * 8 ;

        Paint paintText = new Paint();
        paintText.setTextSize(textTotal);
        paintText.setColor(Color.WHITE);
        for (int x=0; x<bodyStr.length; x++){
            float textWidth = mPaint.measureText(bodyStr[x]);
            if (x==0) {
                canvas.translate(0,textTotal);
                //极限情况   当传入数组就有一个元素
                if (1==bodyStr.length){
                    canvas.drawText(bodyStr[x], mCurWidth/bodyStr.length*(x), textBaseline, paintText);
                    canvas.translate(0,textTotal);

                }else{
                    canvas.drawText(bodyStr[x], mCurWidth/bodyStr.length*(x+0.4f)-textWidth, textBaseline, paintText);

                }

            }else{
                canvas.drawText(bodyStr[x], mCurWidth/bodyStr.length*(x+0.4f)-textWidth, textBaseline, paintText);
                if (x == (bodyStr.length-1)){
                    //说明是最后一个直接移动画布向下
                    canvas.translate(0,textTotal);
                }

            }
        }
    }
}
