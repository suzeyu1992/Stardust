package com.szy.stardust.fm.home.insidefrg.first.graphics.colormatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.szy.stardust.R;

/**
 * author: suzeyu on 16/6/24 15:24
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 自定义View 练习Paint的colorMatrix
 */
public class CusDrawColorMatrixView extends View{

    private final Paint mPaint;
    private final Bitmap mBitmap;

    public CusDrawColorMatrixView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //利用ARGB来设置颜色
        mPaint.setARGB(0xff,0xc8,0x64,0x64);    //对应255,200,100,100

        //获得位图
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(2000,3500);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 通过设置颜色矩阵改变颜色通道输出
        //easySetColorMatirx(canvas);
        canvas.translate(0,50);
        writeBody(canvas,"原图",0);
        writeBody(canvas,"渲染绿色通道",1);
        canvas.translate(0,50);

        //绘制原始位图
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);

        canvas.translate(750,0);

        // 生成绿色矩阵 在对角线上的数值为0,1,当0时,那么这个色彩就完全不显示,所以可以控制成单色通道的效果
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,          //R     red
                0, 1, 0, 0, 0,          //G     green
                0, 0, 0, 0, 0,          //B     blue
                0, 0, 0, 1, 0,          //A     alpha
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);

        //生成一个色彩矩阵 增大蓝色的饱和度
        ColorMatrix colorMatrixBlue = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,          //R     red
                0, 1, 0, 0, 0,          //G     green
                0, 0, 1, 0, 50,         //B     blue
                0, 0, 0, 1, 0,          //A     alpha
        });
        canvas.translate(-750,750);
        writeBody(canvas,"对蓝色通道进行加亮",0);
        writeBody(canvas,"色彩反转",1);
        canvas.translate(0,50);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixBlue));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);


        //生成一个色彩翻转矩阵,  就是求出每个色彩的补值即可
        ColorMatrix colorMatrixReverse = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,          //R     red
                0, -1, 0, 0, 255,          //G     green
                0, 0, -1, 0, 255,          //B     blue
                0, 0, 0, 1, 0,            //A     alpha
        });
        canvas.translate(750,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixReverse));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);


        canvas.translate(-750,750);
        writeBody(canvas,"色彩缩放",0);
        writeBody(canvas,"色彩投射运算 黑白效果",1);
        canvas.translate(0,50);
        //生成一个色彩缩放矩阵,
        ColorMatrix colorMatrixScale = new ColorMatrix(new float[]{
                1.2f, 0   , 0   , 0   , 0,          //R     red
                0   , 1.2f, 0   , 0   , 0,          //G     green
                0   , 0   , 1.2f, 0   , 0,          //B     blue
                0   , 0   , 0   , 1.2f, 0,            //A     alpha
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixScale));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);

        canvas.translate(750,0);
        //生成一个黑白色矩阵
        ColorMatrix colorMatrixBlackAndWhite = new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0,       0,    0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixBlackAndWhite));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);

        canvas.translate(-750,750);
        writeBody(canvas,"色彩投射运算 色彩反色 r->g",0);
        writeBody(canvas,"色彩投射运算 变旧照片",1);
        canvas.translate(0,50);

        //生成一个红色转绿色的矩阵      这里是红绿反色  类似的还有蓝绿 红蓝反色等
        ColorMatrix colorMatrixRed2Green = new ColorMatrix(new float[]{
                0,1,0,0,0,
                1,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixRed2Green));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);


        canvas.translate(750,0);
        //生成一个变旧的矩阵      同样是投射原理应用
        ColorMatrix colorMatrixOldColor = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrixOldColor));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,700,700),mPaint);


    }


    /**
     * 简单的颜色矩阵改变
     * @param canvas
     */
    private void easySetColorMatirx(Canvas canvas) {
        //绘制第一个矩形
        canvas.drawRect(0,0,300,300,mPaint);

        //平移画布圆点   使圆点在上一个矩形右侧的50pixel
        canvas.translate(350,0);

        //生成颜色矩阵  为单色为绿色的通道 无透明
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,          //R     red
                0, 1, 0, 0, 0,          //G     green
                0, 0, 0, 0, 0,          //B     blue
                0, 0, 0, 1, 0,          //A     alpha
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawRect(0,0,300,300,mPaint);
    }

    private void setPaintPrimary(){
        mPaint.reset();
        mPaint.setARGB(255,255,0,0);
        mPaint.setAntiAlias(true);

    }

    private void writeBody(Canvas canvas, String bodyStr, int i){
        Paint paintText = new Paint();
        paintText.setTextSize(50);
        paintText.setColor(Color.BLACK);
        if (i==0) {
            canvas.drawText(bodyStr, 0, 40, paintText);
        }else{
            canvas.drawText(bodyStr, 750, 40, paintText);

        }
    }

}
