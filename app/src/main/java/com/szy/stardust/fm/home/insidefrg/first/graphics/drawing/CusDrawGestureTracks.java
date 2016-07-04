package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author: suzeyu on 16/6/23 15:58
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  自定义view  利用贝塞尔曲线实现手势轨迹绘制
 */
public class CusDrawGestureTracks extends View {
    private Path mGesturePath = new Path();          //手势轨迹的路径
    private float mPreX,mPreY;                       //定义手势最后一次的结束坐标点
    public CusDrawGestureTracks(Context context) {
        super(context);
    }

    public CusDrawGestureTracks(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //创建画笔
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);

        //画一条波浪线
        drawRipple(canvas,paint);

        canvas.drawPath(mGesturePath,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                mGesturePath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                //mGesturePath.lineTo(event.getX(),event.getY());       //这里是利用lineTo画出的手势轨迹
                //首先贝塞尔曲线的终点   (终点+起点)/2
                float endX = (event.getX()+mPreX)/2;
                float endY = (event.getY()+mPreY)/2;

                //绘制贝塞尔曲线
                //注意  当down的时候 接下来move的时候此时的轨迹实际上是一条直线,
                // 而当move --> move 的时候此时的path起始点为上次路径的终点, 这时贝塞尔曲线终点为(mPre+end)/2, 控制点为手势起点.
                mGesturePath.quadTo(mPreX,mPreY,endX,endY);

                //更新下一次path的起始点
                mPreX = event.getX();
                mPreY = event.getY();


                postInvalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void reset(){
        mGesturePath.reset();
        invalidate();
    }

    /**
     * 利用二阶贝塞尔曲线画出一个水纹波浪线
     * @param canvas
     */
    public void drawRipple(Canvas canvas,Paint paint){
        //利用path绘制二阶贝塞尔曲线
        Path path = new Path();
        path.moveTo(100,300);
        path.quadTo(200,200,300,300);       //参数1,2 为控制点xy,  参数3,4为终点,  起始点为上一次的终点
        path.quadTo(400,400,500,300);
        canvas.drawPath(path,paint);

        //利用rQuadTo()  的与上次的偏移量差值来计算
        path.reset();
        path.moveTo(100,300);
        path.rQuadTo(100,-100,200,0);       //参数1,2 为控制点xy,  参数3,4为终点,  起始点为上一次的终点
        path.rQuadTo(100,100,200,0);
        canvas.drawPath(path,paint);
    }

}
