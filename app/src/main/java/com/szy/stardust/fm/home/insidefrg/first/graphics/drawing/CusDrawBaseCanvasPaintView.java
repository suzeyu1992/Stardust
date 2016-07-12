package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * author: suzeyu on 16/7/4 11:10
 * github: https://github.com/suzeyu1992
 * blog  : http://szysky.com
 * -------------------------------------
 * class description : 自定义view  来展示Canvas 和Paint的常用练习
 */
public class CusDrawBaseCanvasPaintView extends View {

    private Paint mPaint;
    private int mCurWidth;            //当前屏幕的宽 pixel
    private int mCurHeight;           //当前屏幕的高 pixel
    private float mDensity;           //当前屏幕的dpi密度的比值. 720*1080(比值为2), 1080*1920(比值为3), 1440*2550(比值为4)

    public CusDrawBaseCanvasPaintView(Context context) {
        super(context);
        //开始基础初始化
        init();

    }


    public CusDrawBaseCanvasPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusDrawBaseCanvasPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mCurWidth,mCurHeight*2+mCurHeight/10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //给整个画布设置颜色 或者canvas.drawColor(int RGB);
        canvas.drawRGB(0, 0, 0);                                //设置画布的背景 参数为RGB对应的0~255的色值, 还有同名函数,接收为ARGB
        //初始化画笔一些属性
        mPaint.setStrikeThruText(false);
        mPaint.setUnderlineText(false);
        mPaint.setTextSkewX(0);
        mPaint.setFakeBoldText(false);
        mPaint.setTextScaleX(1);
        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

        //canvas的绘制图形方法
        canvasGraphics(canvas);

        //path路径练习
        pathPractice(canvas);

        //Paint的文字练习
        paintTextPratice(canvas);

        //Canvas的文字练习
        canvasTextPratice(canvas);


    }

    /**
     * 练习canvas的几个写文字的方法
     * @param canvas
     */
    private void canvasTextPratice(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0,0,mCurHeight,0,mPaint);
        canvas.drawText("Canvas的文字练习",10,20*mDensity,mPaint);
        canvas.translate(0,25*mDensity);


        //指定每个字符的位置来绘制
        mPaint.setColor(Color.YELLOW);
        float []pos=new float[]{20*mDensity,25*mDensity,
                                20*mDensity,50*mDensity,
                                20*mDensity,75*mDensity,
                                20*mDensity,100*mDensity};    //注意这里的数组,俩俩一对, 对数要等于drawPostText的字符串长度,否则会出现越界异常
        canvas.drawPosText("八荣八耻", pos, mPaint);//两个构造函数
        canvas.translate(0,110*mDensity);




        //创建文字路径绘制
        //先创建两个相同的圆形路径，并先画出两个路径原图
        mPaint.setColor(Color.GREEN);mPaint.setStyle(Paint.Style.STROKE);
        Path circlePath=new Path();
        circlePath.addCircle(55*mDensity,50*mDensity, 45*mDensity, Path.Direction.CCW);//逆向绘制,还记得吗,上篇讲过的
        canvas.drawPath(circlePath, mPaint);//绘制出路径原形


        Path circlePath2=new Path();
        circlePath2.addCircle(185*mDensity,50*mDensity, 45*mDensity, Path.Direction.CCW);
        canvas.drawPath(circlePath2, mPaint);//绘制出路径原形

        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        //hoffset、voffset参数值全部设为0，看原始状态是怎样的
        canvas.drawTextOnPath("只要想努力,永远都不会晚.", circlePath, 45*mDensity, 0, mPaint);
        //第二个路径，改变hoffset、voffset参数值 ,参数3偏移路径起始点的距离, 参数4效果向外扩张的效果
        canvas.drawTextOnPath("只要想努力,永远都不会晚.", circlePath2, 20*mDensity, 8*mDensity, mPaint);
        canvas.translate(0,110*mDensity);


        //字体练习
        //首先默认字体
        Typeface 楷体 = Typeface.create("楷体", Typeface.NORMAL);
        mPaint.setTypeface(楷体);
        canvas.drawText("我可是设置过TypeFace的人,好像无效果",10,25*mDensity, mPaint);

        //设置自定义的字体
        Typeface myTypeFace = Typeface.createFromAsset(getContext().getAssets(), "Fonts/jian_luobo.ttf");
        mPaint.setTypeface(myTypeFace);
        canvas.drawText("你妹我看你好不好使!!!",10,50*mDensity, mPaint);



    }


    /**
     * 练习canvas的各种绘制图形方法
     * @param canvas
     */
    private void canvasGraphics(Canvas canvas) {
        //绘制文字
        mPaint.setTextSize(mDensity * 15);                      //设置当绘制文字的时候的字体大小
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShadowLayer(mDensity*5, mDensity*15, mDensity*10, Color.RED );      //设置文字的阴影, 参数分别为:每一点像素模糊的半径, x轴偏移的距离, y轴偏移的距离, 阴影的颜色
        canvas.drawText("阴影文字演示", 0, 25*mDensity, mPaint);                       //绘制文字, 参数2:文字绘制的x轴起点, 参数3: 文字绘制在的基准线位置Y轴
        //移动画布的圆点位置 参数表示为x和y的偏移量,  方便绘制的时候需要计算起始点繁琐的问题
        canvas.translate(0,40 * mDensity);


        //画实心圆
        canvas.drawCircle(mDensity * 30, mDensity * 30, mDensity * 30, mPaint);
        //写文字说明
        mPaint.clearShadowLayer();
        mPaint.setTextSize(13 * mDensity);
        mPaint.setTextAlign(Paint.Align.RIGHT);     //这里设置文字右对齐, 方便之后文字以屏幕右边界为基准实现对齐方式.
        canvas.drawText("圆形,drawCircle(...)",mCurWidth, mDensity* 40, mPaint);

        //画空圆
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mDensity * 100, mDensity * 30, mDensity * 30, mPaint);
        canvas.translate(0,mDensity * 80);

        //画直线
        canvas.drawLine(mDensity*5, 0, mDensity*60, mDensity*20, mPaint);  //参数1,2:为直线的起始点xy; 参数3,4:为结束点xy
        // or 利用点的集合 此数组必须为4的倍数 , 下面可看作为{(mDensity * 65,mDensity*10), (mDensity*90,mDensity*10), (mDensity*100,mDensity*10), (mDensity*30,mDensity*10)}
        float[] pts = {mDensity * 95,mDensity*10,  mDensity*140,mDensity*10,  mDensity*145,mDensity*10,  mDensity*190,mDensity*10};
        canvas.drawLines(pts,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("直线,drawLine(...)",mCurWidth, mDensity* 15, mPaint);
        canvas.translate(0,mDensity * 35);


        //画矩形 实心
        canvas.drawRect(0,0,mDensity*80, mDensity*20,mPaint);           //参数12为矩形左上角的点, 参数34为矩形右下角的点
        //矩形 空心
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new RectF(mDensity*100,0, mDensity*180,mDensity*20),mPaint);
        //文字说明
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("矩形 drawRect(...)", mCurWidth, mDensity*15, mPaint );
        canvas.translate(0, mDensity*35);


        //圆角矩形 实心
        canvas.drawRoundRect(new RectF(0,0,mDensity*80, mDensity*20), mDensity*5, mDensity*5 ,mPaint);      //参数2,3 为圆角的xy的弧度半径
        //圆角矩形 空心
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(mDensity*100,0,mDensity*180, mDensity*20), mDensity*5, mDensity*5 ,mPaint);
        //文字说明
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("圆角矩形 drawRoundRect(...)", mCurWidth, mDensity*15, mPaint );
        canvas.translate(0, mDensity*35);


        //椭圆 实心  本质是根据定义的矩形四边画出的椭圆
        RectF rectF_ovalStart = new RectF(0, 0, mDensity * 80, mDensity * 40);
        canvas.drawOval(rectF_ovalStart, mPaint);
        //椭圆 空心
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF_ovalEnd = new RectF(mDensity*100,0,mDensity*180, mDensity*40);
        canvas.drawOval(rectF_ovalEnd, mPaint);
        //文字说明
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("椭圆 drawOval(...)", mCurWidth, mDensity*30, mPaint );
        canvas.translate(0, mDensity*45);


        //弧  有两边 fill实心 扇形实心
        RectF rectF_ArcStart1 = new RectF(-mDensity * 20, 0, mDensity * 60, mDensity * 40);
        canvas.drawArc(rectF_ArcStart1,0,120,true, mPaint);      //arg2:为开始角度,从x坐标系的正方向为0度起始点. arg3是选装多少角度,为顺时针旋转
        //弧  无两边 实心  圆的一部分
        RectF rectF_ArcStart2 = new RectF(mDensity*50,0,mDensity*130, mDensity*40);
        canvas.drawArc(rectF_ArcStart2,0,120,false, mPaint);
        //弧  无两边 空心 弧边
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF_ArcEnd1 = new RectF(mDensity*120,0,mDensity*200, mDensity*40);
        canvas.drawArc(rectF_ArcEnd1,0,120,false, mPaint);
        //弧  有两边 空心 扇形边框
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF_ArcEnd2 = new RectF(mDensity*190,0,mDensity*260, mDensity*40);
        canvas.drawArc(rectF_ArcEnd2,0,120,true, mPaint);
        //文字说明
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("弧 drawArc(...)", mCurWidth, mDensity*30, mPaint );
        canvas.translate(0, mDensity*65);
    }


    /**
     * 练习pathPractice
     */
    private void pathPractice(Canvas canvas){

        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0,0,mCurHeight,0,mPaint);
        canvas.drawText("path路径的练习",10,20*mDensity,mPaint);
        canvas.translate(0,25*mDensity);


        // 绘制直线路径
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(10*mDensity, 10*mDensity);                        //设定起始点
        path.lineTo(10*mDensity, 100*mDensity);                       //第一条直线的终点，也是第二条直线的起点
        path.lineTo(200*mDensity,100*mDensity);                       //画第二条直线
        path.close();//闭环
        canvas.drawPath(path, mPaint);
        canvas.translate(0,110*mDensity);


        //绘制路径矩形  并根据生成顺序来显示设置文字
        //逆向生成矩形
        Path CCWRectpath = new Path();
        RectF rect1 =  new RectF(30*mDensity, 10*mDensity, 110*mDensity, 60*mDensity);
        CCWRectpath.addRect(rect1, Path.Direction.CCW);

        //顺向生成生成
        Path CWRectpath = new Path();
        RectF rect2 =  new RectF(130*mDensity, 10*mDensity, 210*mDensity, 60*mDensity);
        CWRectpath.addRect(rect2, Path.Direction.CW);

        //先画出这两个路径
        canvas.drawPath(CCWRectpath, mPaint);
        canvas.drawPath(CWRectpath, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(10*mDensity);
        //根据路径顺序画出文字,  参数2为路径, 参数3:每一边的头文字从原点的偏移量,相对于文字的左边开始为整数. 参数4: 每一行文字与所在路径的y轴偏移量,可以理解为值越大越远.
        canvas.drawTextOnPath("啥时候能涨工资呢,好无聊啊好无聊",CCWRectpath,0,10*mDensity,mPaint);
        canvas.drawTextOnPath("啥时候能涨工资呢,好无聊啊好无聊",CWRectpath,0,0*mDensity,mPaint);
        canvas.translate(0,60*mDensity);


        //画圆角矩形路径
        mPaint.setStyle(Paint.Style.STROKE);
        Path pathRoundRect = new Path();
        RectF rectRoundCCW =  new RectF(20*mDensity, 25*mDensity, 120*mDensity, 80*mDensity);
        pathRoundRect.addRoundRect(rectRoundCCW, 5*mDensity, 15*mDensity , Path.Direction.CCW);        //arg2,3: 为生成椭圆的横轴和竖轴半径

        RectF rectRoundCW =  new RectF(140*mDensity,25*mDensity, 240*mDensity, 80*mDensity);
        float radii[] ={2*mDensity,3*mDensity,
                        10*mDensity,10*mDensity,
                        2*mDensity,3*mDensity,
                        25*mDensity,25*mDensity};           //必须为8个值, 对应四个角, 俩俩一堆为xy.
        pathRoundRect.addRoundRect(rectRoundCW, radii, Path.Direction.CCW);
        canvas.drawPath(pathRoundRect, mPaint);
        canvas.translate(0, 140*mDensity);



    }

    /**
     * 练习Paint画笔的写文字的方法
     * @param canvas
     */
    private void paintTextPratice(Canvas canvas) {
        /**
        //普通设置
        mPaint.setStrokeWidth (5);//设置画笔宽度
        mPaint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        mPaint.setStyle(Paint.Style.FILL);//绘图样式，对于设文字和几何图形都有效
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文字对齐方式，取值：align.CENTER、align.LEFT或align.RIGHT
        mPaint.setTextSize(12);//设置文字大小

        //样式设置
        mPaint.setFakeBoldText(true);//设置是否为粗体文字
        mPaint.setUnderlineText(true);//设置下划线
        mPaint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
        mPaint.setStrikeThruText(true);//设置带有删除线效果

        //其它设置
        mPaint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变**/

        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(15*mDensity);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1*mDensity);
        canvas.drawLine(0,0,mCurHeight,0,mPaint);
        canvas.drawText("Paint的文字练习",10,20*mDensity,mPaint);
        canvas.translate(0,25*mDensity);

        //观察各个style的区别
        mPaint.setTextSize(15*mDensity);
        mPaint.setColor(Color.YELLOW);
        //绘图样式，设置为填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("今天的天气还是不错的哦. Style.FILL", 10,25*mDensity, mPaint);
        //绘图样式设置为描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("今天的天气还是不错的哦. Style.STROKE", 10,50*mDensity, mPaint);
        //绘图样式设置为填充且描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("今天的天气还是不错的哦. Style.FILL_AND_STROKE", 10,75*mDensity, mPaint);
        canvas.translate(0,80*mDensity);



        //观察文字的样式设置
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFakeBoldText(true);           //设置为粗体文字
        mPaint.setUnderlineText(true);          //设置下划线
        mPaint.setStrikeThruText(true);         //设置显示删除线

        mPaint.setTextSkewX(-0.3f);             //设置文字倾斜,负值为向右倾斜
        canvas.drawText("文字的删除线,下划线,斜率设置. setTextSkewX(-值)", 10,25*mDensity, mPaint);

        mPaint.setTextSkewX(0.3f);             //设置文字倾斜,正值为向左倾斜
        canvas.drawText("文字的删除线,下划线,斜率设置. setTextSkewX(+值)", 10,50*mDensity, mPaint);
        canvas.translate(0,60*mDensity);


        //文字的水平拉伸
        //先关闭不需要的效果
        mPaint.setFakeBoldText(false);
        mPaint.setUnderlineText(false);
        mPaint.setStrikeThruText(false);
        mPaint.setTextSkewX(0);

        mPaint.setTextScaleX(2);                //设置x轴的水平拉伸比例
        canvas.drawText("来看看水平拉伸的效果. setTextScaleX(2)", 10,25*mDensity, mPaint);
        mPaint.setTextScaleX(1);                //还原
        canvas.drawText("来看看水平拉伸的效果. setTextScaleX(1)", 10,50*mDensity, mPaint);
        canvas.translate(0,60*mDensity);





    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);

        //获得屏幕宽高信息
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mCurHeight = displayMetrics.heightPixels;
        mCurWidth = displayMetrics.widthPixels;
        mDensity = displayMetrics.density;

        //开始设置画笔的基本信息
        mPaint.setAntiAlias(true);                              //设置画笔的抗锯齿
        mPaint.setColor(Color.WHITE);                           //设置画笔的颜色
        mPaint.setStyle(Paint.Style.FILL);                      //设置画出的图形填充的类型,fill为内部填充,stroke为只有边框,内容不填充
        mPaint.setStrokeWidth(mDensity * 2);                    //设置边框的宽度. 如矩形的边宽, 文字的宽度. 接收实参为像素单位
        mPaint.setTextSize(mDensity * 20);                      //设置当绘制文字的时候的字体大小
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);              //设置当绘制文字时候的字体粗细
        mPaint.setShadowLayer(mDensity*3, mDensity*10, mDensity*10, Color.RED );      //设置文字的阴影, 参数分别为:每一点像素模糊的半径, x轴偏移的距离, y轴偏移的距离, 阴影的颜色



    }
}
