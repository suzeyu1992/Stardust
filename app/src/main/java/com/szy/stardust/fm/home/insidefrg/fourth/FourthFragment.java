package com.szy.stardust.fm.home.insidefrg.fourth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szy.stardust.R;
import com.szy.stardust.fm.myinfo.HomePagerFragment;

import static com.szy.stardust.R.id.tv_department;

/**
 * author: suzeyu on 16/6/8 00:11
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  一级页面内部的第四个fragment管理组件
 */
public class FourthFragment extends HomePagerFragment {


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_myinfo, container, false);

        final TextView tv_department = (TextView) view.findViewById(R.id.tv_department);
        tv_department.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = tv_department.getLayoutParams();

                Log.d("sususus","width:"+tv_department.getMeasuredWidth());
                Log.d("sususus","height:"+tv_department.getMeasuredHeight());
            }
        });

        return view;
    }
}
