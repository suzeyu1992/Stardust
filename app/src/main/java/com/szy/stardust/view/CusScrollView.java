package com.szy.stardust.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * author: suzeyu on 16/6/29 21:51
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :
 */
public class CusScrollView extends ScrollView {
    public CusScrollView(Context context) {
        super(context);
    }

    public CusScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return false;
    }
}
