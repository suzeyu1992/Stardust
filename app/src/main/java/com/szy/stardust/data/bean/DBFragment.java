package com.szy.stardust.data.bean;

import android.support.v4.app.Fragment;

/**
 * author: suzeyu on 16/6/7 21:37
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  包含一个fragment和title的信息
 */
public class DBFragment {
    private Fragment mFragment;
    private String mString;

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }
}
