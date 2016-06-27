package com.szy.stardust.fm.home.insidefrg.first.graphics.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import com.szy.stardust.R;
import com.szy.stardust.util.UIUtils;

/**
 * author: suzeyu on 16/6/27 14:55
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 自定义view   对Paint的setColorFilter练习
 *
 * 可以掌握知识:
 * 按钮点击时，动态加深图片色彩（简易方法）
 * 可以学到针对不同主题动态设置不同色彩图片的方法(setTint())
 */
public class CusDrawColorFilterView extends View{

    private final Paint mPaint;
    private final Bitmap mBitmap;
    private final int mCurWidth;            //当前屏幕的宽 pixel
    private final int mCurHeight;           //当前屏幕的高 pixel
    private final Bitmap mBitmapAvat;

    public CusDrawColorFilterView(Context context) {
        super(context);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_graphics_bulb);
        mBitmapAvat = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);

        //获取屏幕宽高
        DisplayMetrics currentDisplayMetrics = UIUtils.getCurrentDisplayMetrics();
        mCurWidth = currentDisplayMetrics.widthPixels;
        mCurHeight = currentDisplayMetrics.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(2000,5500);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //练习LightingColorFilter函数
        simpleColorFilter(canvas);

        //练习PorterDuffColorFilter函数
        simplePorterDuffColorFilter(canvas);


    }
    /**
     * 使用paint的setColorFilter中的PorterDuffColorFilter()函数
     *
     * 也可以叫PorterDuff颜色滤镜, 也叫图形混合滤镜
     * int srcColor: 为AARRGGBB 的颜色值
     * porterDuff.Mode mode:  表示混合模式 枚举类型共18个,这里我们只关心Mode.add(饱和度相加),mode.darken(变暗), mode.lighten(变亮), mode.multiply(正片叠底), mode.overlay(叠加), mode.screen(滤色)
     * @param canvas
     */
    private void simplePorterDuffColorFilter(Canvas canvas) {
        int width = mCurWidth/2;
        int height = width * mBitmap.getHeight()/mBitmap.getWidth();
        writeBody(canvas,"src 使用PorterDuffColorFilter",0);
        writeBody(canvas,"红层 Multiply模式 -- 叠底",1);

        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(width,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(-width,height);

        writeBody(canvas,"红层 add模式 -- 饱和度相加",0);
        writeBody(canvas,"红层 Darken模式 -- 变暗",1);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.ADD));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(width,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(-width,height);

        writeBody(canvas,"红层 Lighten模式 -- 变亮",0);
        writeBody(canvas,"红层 Screen模式 -- 滤色",1);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(width,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SCREEN));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(-width,height);

        writeBody(canvas,"红层 Overlay模式 -- 叠加",0);
        writeBody(canvas,"红层 清空模式 -- 空图",1);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.OVERLAY));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(width,0);
        //Mode.CLEAR 和Mode.XOR  效果一致, 清空图像功能
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.CLEAR));
        canvas.drawBitmap(mBitmapAvat,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(-width,height);

        /**
         * 目标图像模式 一组关于DST相关的模式, dst所代表的用意就是被应用模式的图像  显示原图, 操作色值被忽略
         * Mode.DST、Mode.DST_IN、Mode.DST_OUT、Mode.DST_OVER、Mode.DST_ATOP
         * 这里除了Mode.DST_OUT显示完全透明图之外,其余都一样. 在PorterDuff并没卵用!!
         */

        /**
         * 源图模式 一组关于SRC相关的模式, src所代表的用意就是颜色值所代表的图像  显示操作颜色值, 原始图片被忽略
         * Mode.SRC、Mode.SRC_IN、Mode.SRC_OUT、Mode.SRC_OVER、Mode.SRC_ATOP
         * 这里除了Mode.SRC_OUT显示完全透明图之外,其余都一样. ** 但是我们可以通过src_in和src_atop来实现setLint()函数的效果
         */

        Bitmap bitmapBaidu = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_graphics_baidu);
        writeBody(canvas,"利用Mode.SRC来实现setLint()效果",0);
        writeBody(canvas," ",1);
        canvas.translate(0,50);
        int tempWidth = mCurWidth/14;
        int tempHeight = tempWidth * bitmapBaidu.getHeight()/bitmapBaidu.getWidth();

        mPaint.setColorFilter(null);
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        mPaint.setColorFilter(new PorterDuffColorFilter(0xffff00ff, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        mPaint.setColorFilter(new PorterDuffColorFilter(0xff00f0ff, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        mPaint.setColorFilter(new PorterDuffColorFilter(0xfff0f0ff, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        mPaint.setColorFilter(new PorterDuffColorFilter(0xffffff00, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        mPaint.setColorFilter(new PorterDuffColorFilter(0xff00ff00, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmapBaidu,null,new Rect(0,0,tempWidth,tempHeight),mPaint);
        canvas.translate(tempWidth+30,0);

        canvas.translate(0,150);

    }

    /**
     * 使用paint的setColorFilter中的LightingColorFilter()函数
     *
     * 此函数对应的参数  为RRGGBB  透明度在这里无效
     * arg1 为乘数
     * arg2 为向量 加数
     * @param canvas
     */
    private void simpleColorFilter(Canvas canvas) {
        int width = mCurWidth/2;
        int height = width * mBitmap.getHeight()/mBitmap.getWidth();

        writeBody(canvas,"src 使用LightingColorFilter",0);
        writeBody(canvas,"改变为绿色",1);

        //画出原始的bitmap图片
        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmap.copy(Bitmap.Config.ARGB_8888,false),null,new Rect(0,0,width,height),mPaint);

        canvas.translate(mCurWidth/2,0);
        //利用颜色过滤方法, 可以比颜色矩阵更方便的更改颜色
        //改变为绿色图片   关闭蓝色和红色通道
        mPaint.setColorFilter(new LightingColorFilter(0x00ff00,0x000000));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(-width,height);
        writeBody(canvas,"调高亮度",0);
        writeBody(canvas,"0",1);

        //提高亮度
        mPaint.setColorFilter(new LightingColorFilter(0x00ff00,0x00f000));
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(0,height);
    }

    private void writeBody(Canvas canvas, String bodyStr, int i){
        Paint paintText = new Paint();
        paintText.setTextSize(50);
        paintText.setColor(Color.BLACK);
        if (i==0) {
            canvas.translate(0,50);
            canvas.drawText(bodyStr, 0, 40, paintText);
        }else{
            canvas.drawText(bodyStr, mCurWidth/2, 40, paintText);
            canvas.translate(0,50);

        }
    }
}
