package com.szy.stardust.fm.home.insidefrg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szy.stardust.R;

/**
 * author: suzeyu on 16/6/6 18:03
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :   一级页面的内部fragment
 */
public  class BaseInsidePagerFragment extends Fragment {

    protected int mDisplayStyle;        //此fragment具体要显示的内容

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // initLoad();
    }

    //初始化内部fragment要展示的类型
   // protected abstract void initLoad();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment_inside_home, container, false);


        return view;
    }





}
