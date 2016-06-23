package com.szy.stardust.fm.home.insidefrg.first.graphics.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.szy.stardust.R;

/**
 * author: suzeyu on 16/6/20 15:35
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  自定义View展示  drawText的知识点
 */
public class CusDrawTextView extends View {
    public final String TAG = getClass().getSimpleName();
    public final String mSimpleStr = "just do it";

    public CusDrawTextView(Context context) {
        super(context);
    }

    public CusDrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusDrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 5;
        int baseLineY = 200;

        //画出一条基准线
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY,paint);

        //写文字  just do it
        paint.setColor(Color.GREEN);
        paint.setTextSize(120);                                         //默认是px为单位
        paint.setTextAlign(Paint.Align.LEFT);                         //其中Align的取值为：Paint.Align.LEFT,Paint.Align.CENTER,Paint.Align.RIGHT
        // 1、drawText是中的参数y是基线的位置。
        // 2、一定要清楚的是，只要x坐标、基线位置、文字大小确定以后，文字的位置就是确定的了
        canvas.drawText(mSimpleStr,baseLineX,baseLineY,paint);

        /*******************************************************************************************
         *****                                                                                 *****
         *****           获得fontMetrics()对象来画出五线格                                         *****
         *****                                                                                 *****
         ********************************************************************************************/
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        //计算出ascent desent top bottom四个点的y坐标
        float ascent = fontMetrics.ascent + baseLineY;
        float descent = fontMetrics.descent + baseLineY;
        float top = fontMetrics.top + baseLineY;
        float bottom = fontMetrics.bottom + baseLineY;

        Log.d(TAG,"ascent:"+ascent+",descent:"+descent+",top:"+top+",bottom:"+bottom);
        paint.setColor(Color.BLACK);
        //画出绘制文字推荐的顶线   ascent
        canvas.drawLine(baseLineX,ascent,3000,ascent,paint);
        //画出绘制文字推荐的底线   descent
        canvas.drawLine(baseLineX,descent,3000,descent,paint);
        //画出绘制文字最大的顶线   top
        canvas.drawLine(baseLineX,top,3000,top,paint);
        //画出绘制文字最大的底线   bottom
        canvas.drawLine(baseLineX,bottom,3000,bottom,paint);


        /*******************************************************************************************
         *****                                                                                 *****
         *****           获得回执文字的宽度, 高度, 和最小矩形的获取                                   *****
         *****                                                                                 *****
         ********************************************************************************************/
        //字体最大高度    通过top bottom之间差求得
        int height = (int) (bottom - top);

        //字体的宽度
        int width = (int) paint.measureText(mSimpleStr);
        //画出textview所占的区域
        paint.setColor(Color.BLUE);
        canvas.drawRect(baseLineX,top,baseLineX+width,bottom,paint);

        //获得最小矩形  此时得到的矩形是相对于字体的baseline   所以如果想重叠在我们写的字体上,只要对准字体的baseline和矩形的baseline即可
        Rect minRect = new Rect();
        paint.getTextBounds(mSimpleStr,0,mSimpleStr.length(),minRect);
        minRect.top = minRect.top + baseLineY;
        minRect.bottom = minRect.bottom + baseLineY;
        minRect.left = minRect.left + baseLineX;
        minRect.right = minRect.right + baseLineX;
        paint.setColor(Color.RED);
        canvas.drawRect(minRect,paint);

        paint.setColor(Color.GREEN);
        canvas.drawText(mSimpleStr,baseLineX,baseLineY,paint);
        Log.d(TAG,"最大高度:"+height+",宽度:"+width+",最小矩形:"+minRect.toShortString());

        /*******************************************************************************************
         *****                                                                                 *****
         *****           练习:    给定左上定点绘图                                                 *****
         *****                                                                                 *****
         ********************************************************************************************/
        canvas.translate(0,250);
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("给定左上定点绘图",0,40,paint);
        canvas.translate(0,60);
        int fixedTop = 0;     //给定固定定点坐标
        int resultLine = 0 ;    //需要求得的基准线

        //先画出已知的top线
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,0,2000,0,paint);

        //设置要写文字的画笔
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setTextSize(120);
        paint.setTextAlign(Paint.Align.LEFT);

        //开始测量出baseline的位置
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        resultLine = fixedTop - fontMetricsInt.top;

        //画出基准线
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,resultLine,2000,resultLine,paint);

        //写出文字
        paint.setColor(Color.GREEN);
        canvas.drawText(mSimpleStr,0,resultLine,paint);



        /*******************************************************************************************
         *****                                                                                 *****
         *****           练习:    给定中间线位置绘图                                               *****
         *****                                                                                 *****
         ********************************************************************************************/
        canvas.translate(0,250);
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("给定中间线位置绘图",0,40,paint);
        canvas.translate(0,60);

        int fixedCenter = 100;     //给定固定定点坐标
        resultLine = 0 ;           //需要求得的基准线

        //画center线
        paint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, fixedCenter, 3000, fixedCenter, paint);

        //计算出baseLine位置
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        resultLine = fixedCenter + (fm.bottom - fm.top)/2 - fm.bottom;

        //画基线
        paint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, resultLine, 3000, resultLine, paint);

        //写文字
        paint.setColor(Color.GREEN);
        paint.setTextSize(120);
        canvas.drawText(mSimpleStr, baseLineX, resultLine, paint);

    }
}
