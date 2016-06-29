package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

import com.szy.stardust.R;
import com.szy.stardust.util.UIUtils;

/**
 * author: suzeyu on 16/6/29 12:32
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 对于paint的setXfermode中PorterDuffCombat的实例
 */
public class CusDrawSetXfermode2PorterDuffCombat_1 extends View {

    private  Paint mPaint;
    private  Bitmap mBitmap,dstBmp,srcBmp;
    private  int mCurWidth;            //当前屏幕的宽 pixel
    private  int mCurHeight;           //当前屏幕的高 pixel
    private  DisplayMetrics mCurrentDisplayMetrics;



    public CusDrawSetXfermode2PorterDuffCombat_1(Context context) {
        super(context);
        //进行初始化操作
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);

        //禁用硬件加速
        //这里有一个问题当如果禁用硬件加速的话,  在onMeasure中指定的大小不能超多当前屏幕的width和height的乘积.
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        //获取屏幕宽高
        mCurrentDisplayMetrics = UIUtils.getCurrentDisplayMetrics();
        mCurWidth = mCurrentDisplayMetrics.widthPixels;
        mCurHeight = mCurrentDisplayMetrics.heightPixels;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurWidth, mCurHeight *8/10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //写出当前View的功能
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mCurWidth, mCurHeight, mPaint);
        mPaint.setColor(Color.WHITE);


        //增加twitter图标的描边效果
        addImgStroke(canvas);

        //Src相关模式使用展示 倒镜
        addSrcMode(canvas);
    }

    /**
     * 1 使用src相关模式练习展示
     *
     * @param canvas
     */
    private void addSrcMode(Canvas canvas) {
        float everyOneWidth = mCurWidth / 4f;

        writeTextBody(canvas, new String[]{"利用Src相关模式 实现效果"});
        writeTextBody(canvas, new String[]{"SRC_IN","SRC_ATOP", "SRC_IN","SRC_IN"});

        /**获得需要操作的两张图片**/
        Bitmap bmp_chi = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);

        //开始准备合成圆角头像
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //画出一个圆角矩形
        RectF rectF = new RectF(0, 0, everyOneWidth, everyOneWidth);
        canvas.drawRoundRect(rectF,everyOneWidth/7,everyOneWidth/7,mPaint);
        //设置混合的模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //画出源图
        canvas.drawBitmap(bmp_chi,0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);



        /**开始准备合成圆形头像**/
        layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //画出一个圆形
        canvas.drawCircle(everyOneWidth*3/2,everyOneWidth/2,everyOneWidth/2,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bmp_chi,everyOneWidth*1,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);


        /**开始准备合成扇形头像**/
        layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //画出一个弧形
        RectF rectF_arc = new RectF(everyOneWidth*2,0,everyOneWidth*3,everyOneWidth);
        canvas.drawArc(rectF_arc,30,120,true,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp_chi,everyOneWidth*2,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

        //**开始画一个特别的图形  高仿血轮眼 头像
        layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        Path path = new Path();
        path.moveTo(everyOneWidth*3.20f, everyOneWidth*0.20f);
        path.quadTo(everyOneWidth*3.35f, everyOneWidth*0.30f, everyOneWidth*3.5f, everyOneWidth*0.2f);
        path.quadTo(everyOneWidth*3.65f, everyOneWidth*0.10f, everyOneWidth*3.8f, everyOneWidth*0.2f);

        path.quadTo(everyOneWidth*3.70f ,everyOneWidth*0.35f, everyOneWidth*3.8f, everyOneWidth*0.5f);
        path.quadTo(everyOneWidth*3.90f ,everyOneWidth*0.65f, everyOneWidth*3.8f, everyOneWidth*0.8f);

        path.quadTo(everyOneWidth*3.65f ,everyOneWidth*0.70f, everyOneWidth*3.5f, everyOneWidth*0.8f);
        path.quadTo(everyOneWidth*3.35f ,everyOneWidth*0.90f, everyOneWidth*3.2f, everyOneWidth*0.8f);

        path.quadTo(everyOneWidth*3.30f ,everyOneWidth*0.65f, everyOneWidth*3.2f, everyOneWidth*0.5f);
        path.quadTo(everyOneWidth*3.10f ,everyOneWidth*0.35f, everyOneWidth*3.2f, everyOneWidth*0.2f);
        canvas.drawPath(path,mPaint);
        //设置 模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp_chi,everyOneWidth*2.8f,everyOneWidth*(-0.3f),mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);


        canvas.translate(0,everyOneWidth);
        writeTextBody(canvas,new String[]{"SRC_IN 倒影","SRC_OUT 橡皮擦" , ""});


        /**准备实现倒影效果**/
        //**
        //      利用SRC_IN模式是在相交时利用目标图像的透明度来改变源图像的透明度和饱和度。
        //      所以当目标图像的透明度在0-255之间时，就会把源图像的透明度和颜色值都会变小。
        //
        Bitmap bmp_invert_shade = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_white_invert_shade);

        Matrix matrix = new Matrix();
        matrix.setScale(1f,-1f);
        //生成倒影图bitmap
        int i_invert_width = (int) (everyOneWidth*4/3);
        Bitmap bmp_invert_chi = Bitmap.createBitmap(bmp_chi,0,0, bmp_chi.getWidth(),bmp_chi.getHeight(),matrix, true );
        //放入原图
        canvas.drawBitmap(bmp_chi,null,new Rect(0,0,i_invert_width,i_invert_width),mPaint);
        //开始写入倒影并使用混合模式
        layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,i_invert_width);

        //使用白色透明渐变图片为DTS
        canvas.drawBitmap(bmp_invert_shade,null,new RectF(0,0,i_invert_width,i_invert_width),mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //使用倒影图片为SRC   因为src模式源会根据目标的透明图来改变自身的透明度和饱和度最终显示的屏幕上
        canvas.drawBitmap(bmp_invert_chi,null,new Rect(0,0,i_invert_width,i_invert_width),mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);









    }





    /**
     * 1.利用porterDuff中的混合模式, 实现图片边缘描边
     * 2.使用Mode.Multiply(正片叠底), 此效果可以再两张图片某一方有透明的时候,结果像素就是透明的.
     *  其余的混合模式例如add, lighten, darken, overlay, screen.  都会保留两张图片的非交互区域的原样显示
     *
     * @param canvas 画布
     */
    private void addImgStroke(Canvas canvas) {
        int everyOneWidth = mCurWidth / 3;

        //获得需要操作的两张图片
        Bitmap bmp_twitter_bg = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_graphics_twiter_bg);
        Bitmap bmp_twitter_light = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_graphics_twiter_light);
//        bmp_twitter_bg = Bitmap.createBitmap(bmp_twitter_bg, 0, 0, everyOneWidth / 2, everyOneWidth / 2);
//        bmp_twitter_light = Bitmap.createBitmap(bmp_twitter_light, 0, 0, everyOneWidth , everyOneWidth / 2);

        writeTextBody(canvas, new String[]{"利用混合模式中 Mode.MULTIPLY(正片叠底),实现描边"});
        writeTextBody(canvas, new String[]{"src","dts", "结果图"});
        //首先画出src 和 dts
        canvas.drawBitmap(bmp_twitter_bg, 0,0,mPaint);
        canvas.drawBitmap(bmp_twitter_light, everyOneWidth,0,mPaint);

        //准备利用setXfermode合成结果图
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);


        canvas.drawBitmap(bmp_twitter_bg,everyOneWidth*2,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(bmp_twitter_light,everyOneWidth*2, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);

        canvas.translate(0,bmp_twitter_bg.getHeight()*0.7f);

    }

    /**
     * 在画布的水平线, 平分数组中的字符串,显示在这条水平线上
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
