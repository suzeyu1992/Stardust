package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

import com.szy.stardust.R;
import com.szy.stardust.util.UIUtils;

/**
 * author: suzeyu on 16/6/27 18:06
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 练习paint中的setXfermode中的porterDuffXfermode
 */
public class CusDrawSetXfermode2PorterDuffXfermodeView extends View {

    private final Paint mPaint;
    private final Bitmap mBitmap,dstBmp,srcBmp;
    private final int mCurWidth;            //当前屏幕的宽 pixel
    private final int mCurHeight;           //当前屏幕的高 pixel
    private int width ;
    private int height;
    private final DisplayMetrics mCurrentDisplayMetrics;

    public CusDrawSetXfermode2PorterDuffXfermodeView(Context context) {
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

        width = mCurWidth/8;
        height = width;
        dstBmp = makeDst(width,height);
        srcBmp = makeSrc(width,height);


    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurWidth,(int)(mCurHeight));
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);





        mPracticePorterDuffXfermode(canvas);




    }

    private void mPracticePorterDuffXfermode(Canvas canvas) {
        //文字的总高
        float textTotal = mCurrentDisplayMetrics.density * 10;
        //文字的基准线
        float textBaseline = mCurrentDisplayMetrics.density * 8 ;
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(textTotal);
        canvas.drawText("setXfermode 中 PorterDuffXfermode的演示",5,textBaseline,mPaint);
//        canvas.drawBitmap(dstBmp, 0, 0, mPaint);        //黄色圆形
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(srcBmp, width/2, height/2, mPaint);       //蓝色矩形
//        mPaint.setXfermode(null);


        //写出一行文字说明
        writeBody(canvas,new String[]{"Clear","Src","Dst","SrcOver"},0);
        // 画出一行porterDuff.mode不同类型的效果
        drawLineBtm(canvas,new PorterDuff.Mode[]{PorterDuff.Mode.CLEAR, PorterDuff.Mode.SRC, PorterDuff.Mode.DST, PorterDuff.Mode.SRC_OVER});

        //写出一行文字说明
        writeBody(canvas,new String[]{"DstOver","SrcIn","DstIn","SrcOut"},0);
        // 画出一行porterDuff.mode不同类型的效果
        drawLineBtm(canvas,new PorterDuff.Mode[]{PorterDuff.Mode.DST_OVER, PorterDuff.Mode.SRC_IN, PorterDuff.Mode.DST_IN, PorterDuff.Mode.SRC_OUT});

        //写出一行文字说明
        writeBody(canvas,new String[]{"DstOut","SrcATop","DstATop","Xor"},0);
        // 画出一行porterDuff.mode不同类型的效果
        drawLineBtm(canvas,new PorterDuff.Mode[]{PorterDuff.Mode.DST_OUT, PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.XOR});

        //写出一行文字说明
        writeBody(canvas,new String[]{"Darken","Lighten","Multiply","Screen"},0);
        // 画出一行porterDuff.mode不同类型的效果
        drawLineBtm(canvas,new PorterDuff.Mode[]{PorterDuff.Mode.DARKEN, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY, PorterDuff.Mode.SCREEN});

        //写出一行文字说明
        writeBody(canvas,new String[]{"Add"},0);
        // 画出一行porterDuff.mode不同类型的效果
        drawLineBtm(canvas,new PorterDuff.Mode[]{PorterDuff.Mode.ADD});
    }

    /**
     * 接收一个porterDuff.mode的数组,  根据数组个数来适配屏幕一个水平面依次画出
     * @param canvas
     * @param modes
     */
    private void drawLineBtm(Canvas canvas, PorterDuff.Mode[] modes){
        if (modes.length == 0)
            return;

        int length = modes.length;

        mPaint.setStyle(Paint.Style.STROKE);
        for (int x=0; x<length; x++){
            canvas.drawRect(mCurWidth/4*x,0,mCurWidth/4*(x+0.8f),mCurWidth/4*0.8f,mPaint );
            canvas.drawBitmap(dstBmp, mCurWidth/4*x, 0, mPaint);        //黄色圆形
            mPaint.setXfermode(new PorterDuffXfermode(modes[x]));
            canvas.drawBitmap(srcBmp, mCurWidth/4*x + width/2, height/2, mPaint);       //蓝色矩形
            mPaint.setXfermode(null);
        }

        canvas.translate(0,mCurWidth/4);

    }


    /**
     * 制作源图 画一个矩形 src 蓝色 source
     * @param w
     * @param h
     * @return
     */
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF66AAFF);
        c.drawRect(0, 0,w,h, p);
        return bm;
    }

    /**
     * 制作目标图 画一个圆 dst 黄色 destination
     * @param w
     * @param h
     * @return
     */
    static Bitmap makeDst(int w, int h){
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0,0,w,h),paint);
        return bitmap;
    }


    private void writeBody(Canvas canvas, String[] bodyStr, int i){
        //文字的总高
        float textTotal = mCurrentDisplayMetrics.density * 10;
        //文字的基准线
        float textBaseline = mCurrentDisplayMetrics.density * 8 ;

        Paint paintText = new Paint();
        paintText.setTextSize(textTotal);
        paintText.setColor(Color.BLACK);
        for (int x=0; x<bodyStr.length; x++){
            if (x==0) {
                canvas.translate(0,textTotal);
                canvas.drawText(bodyStr[x], 0, textBaseline, paintText);
                //极限情况   当传入数组就有一个元素
                if (1==bodyStr.length)
                    canvas.translate(0,textTotal);

            }else{
                canvas.drawText(bodyStr[x], mCurWidth/bodyStr.length*x, textBaseline, paintText);
                if (x == (bodyStr.length-1)){
                    //说明是最后一个直接移动画布向下
                    canvas.translate(0,textTotal);
                }

            }
        }


    }
}
