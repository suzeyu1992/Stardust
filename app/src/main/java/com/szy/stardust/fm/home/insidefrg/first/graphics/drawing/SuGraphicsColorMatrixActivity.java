package com.szy.stardust.fm.home.insidefrg.first.graphics.drawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;

/**
 * author: suzeyu on 16/6/24 14:56
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  paint之colorMatrix 与滤镜效果
 */
public class SuGraphicsColorMatrixActivity extends BaseActivity {

    private SeekBar seekbar_saturation;             //控制饱和度的seekbar控件
    private ImageView iv_img;                       //要改变图片的imageview
    private Bitmap mTempBmp;
    private Bitmap mOriginBmp, mResultBmp;
    private SeekBar seekbar_scale;                  //控制色彩缩放seekbar控件
    private int mSaturationProgress , mRotatePregress;
    private float mScaleProgress;
    private SeekBar seekbar_rotate;                 //控制色彩旋转seekbar控件


    @Override
    protected void initView() {
        setContentView(R.layout.activity_graphics_drawtext);
        final LinearLayout ll_root = (LinearLayout) findViewById(R.id.ll_body);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("paint之colorMatrix 与滤镜效果");

        ll_root.removeAllViews();

        //创建自定义view
        CusDrawColorMatrixView cusDrawColorMatrixView = new CusDrawColorMatrixView(getApplicationContext());
        ll_root.addView(cusDrawColorMatrixView);

        //添加动态改变颜色的布局
        View inflate = View.inflate(getApplicationContext(), R.layout.layout_colormatrix, null);
        ll_root.addView(inflate);
        
        //找到需要的seekBar控件
        seekbar_saturation = (SeekBar) inflate.findViewById(R.id.seekbar);
        seekbar_scale = (SeekBar) inflate.findViewById(R.id.seekbar_scale);
        seekbar_rotate = (SeekBar) inflate.findViewById(R.id.seekbar_rotate);
        iv_img = (ImageView) inflate.findViewById(R.id.iv_img);



        mSaturationProgress = 1;
        mScaleProgress = 1f;
        mRotatePregress = 1;

        //对饱和seekbar进行初始化操作
        seekbar_saturation.setMax(20);
        seekbar_saturation.setProgress(0);

        seekbar_scale.setMax(20);
        seekbar_saturation.setProgress(10);

        //色彩旋转
        seekbar_rotate.setMax(360);
        seekbar_rotate.setProgress(180);


    }

    @Override
    protected void initListener() {

        //生成需要改变的图片的bitmap
        mOriginBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_experiment_cs);
        mTempBmp = Bitmap.createBitmap(mOriginBmp.getWidth(), mOriginBmp.getHeight(), Bitmap.Config.ARGB_8888);
        iv_img.setImageBitmap(mOriginBmp);


        seekbar_saturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mSaturationProgress = progress;
                Bitmap bitmap = handleColorMatrixBmp(mSaturationProgress,-999,-999);
                iv_img.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_scale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mScaleProgress = progress/10f;

                mScaleProgress = progress;
                Bitmap bitmap = handleColorMatrixBmp(-999,mScaleProgress,-999);
                iv_img.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_rotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                Bitmap bitmap = handleColorMatrixBmp(-999,-999,-progress-180);
                iv_img.setImageBitmap(bitmap);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





}

    @Override
    protected void initLoad() {




    }

    /**
     * 通过传入的进度值来改变颜色矩阵的饱和度
     * @param
     * @return
     */
    private Bitmap  handleColorMatrixBmp(int saturationProgress,float scaleProgress, int rotateProgress){
        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
        Canvas canvas = new Canvas(mTempBmp); // 得到画笔对象
        Paint paint = new Paint(); // 新建paint
        paint.setAntiAlias(true); // 设置抗锯齿,也即是边缘做平滑处理
        ColorMatrix mSaturationMatrix = new ColorMatrix();

        //这里需要注意  setSaturation 和setscale函数 只能同时执行一个, 如果同时执行已最后一个出现为有效操作
        if (saturationProgress != -999){
            //设置饱和度
            mSaturationMatrix.setSaturation(saturationProgress);
        }else if(scaleProgress != -999){
            //设置色彩缩放
            mSaturationMatrix.setScale(scaleProgress,scaleProgress,scaleProgress,1);
        }else if(rotateProgress != -999){
            //设置色彩旋转角度
            mSaturationMatrix.setRotate(0,rotateProgress);      //arg1 表示为围绕哪个轴旋转,取值为0,1,2  arg2: degrees 表示旋转的角度.
        }



        paint.setColorFilter(new ColorMatrixColorFilter(mSaturationMatrix));// 设置颜色变换效果
        canvas.drawBitmap(mOriginBmp, 0, 0, paint); // 将颜色变化后的图片输出到新创建的位图区

        // 返回新的位图，也即调色处理后的图片
        return mTempBmp;
    }
}
