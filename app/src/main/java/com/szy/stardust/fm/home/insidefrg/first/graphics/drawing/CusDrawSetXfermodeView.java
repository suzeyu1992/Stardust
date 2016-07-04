package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import com.szy.stardust.R;
import com.szy.stardust.util.UIUtils;

/**
 * author: suzeyu on 16/6/27 18:06
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 练习paint中的setXfermode中Avoid模式
 */
public class CusDrawSetXfermodeView extends View {

    private final Paint mPaint;
    private final Bitmap mBitmap;
    private final int mCurWidth;            //当前屏幕的宽 pixel
    private final int mCurHeight;           //当前屏幕的高 pixel
    private final DisplayMetrics mCurrentDisplayMetrics;

    public CusDrawSetXfermodeView(Context context) {
        super(context);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);
        //禁用硬件加速
        //这里有一个问题当如果禁用硬件加速的话,  在onMeasure中指定的大小不能超多当前屏幕的width和height的乘积.
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);


        //获取屏幕宽高
        mCurrentDisplayMetrics = UIUtils.getCurrentDisplayMetrics();
        mCurWidth = mCurrentDisplayMetrics.widthPixels;
        mCurHeight = mCurrentDisplayMetrics.heightPixels;


    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurWidth,mBitmap.getHeight()/mBitmap.getWidth()*(mCurWidth/2)+200);
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // setXfermode之AvoidXfermode练习
        mPracticeAvoidXfermode(canvas);







    }



    /**
     * 对AvoidXfermode的练习
     * setXfermode叫做图像混合模式  派生三个子类, 为AvoidXfermode, PixelXorXfermode, PorterDuffXfermode.   此方法练习第一个
     * 从硬件加速不支持的函数列表中，我们可以看到AvoidXfermode，PixelXorXfermode是完全不支持的，而PorterDuffXfermode是部分不支持的。
     * @param canvas
     */
    private void mPracticeAvoidXfermode(Canvas canvas) {
        int width = mCurWidth/2;
        int height = width * mBitmap.getHeight()/mBitmap.getWidth();



        //描写说明文字
        writeBody(canvas,"原图",0);
        writeBody(canvas,"AvoidXfermode使用",1);
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(width,0);

        //使用离屏绘制
        //新建图层
        int layerId = canvas.saveLayer(0, 0, width, height+50, mPaint, Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.GREEN);

        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);
        //使用paint 的setXfermode 改变某一区域的颜色
        mPaint.setXfermode(new AvoidXfermode(Color.RED,150,AvoidXfermode.Mode.TARGET));
        //canvas.translate(width,0);
        canvas.drawRect(0,0,width,height,mPaint);

        //还原图层
        canvas.restoreToCount(layerId);
        canvas.translate(-width,height);


    }

    private void writeBody(Canvas canvas, String bodyStr, int i){
        //文字的总高
        float textTotal = mCurrentDisplayMetrics.density * 10;
        //文字的基准线
        float textBaseline = mCurrentDisplayMetrics.density * 8 ;
        Paint paintText = new Paint();
        paintText.setTextSize(textTotal);
        paintText.setColor(Color.BLACK);
        if (i==0) {
            canvas.translate(0,50);
            canvas.drawText(bodyStr, 0, textBaseline, paintText);
        }else{
            canvas.drawText(bodyStr, mCurWidth/2, textBaseline, paintText);
            canvas.translate(0,textTotal);

        }
    }
}
