package com.szy.stardust.util;

import android.content.SharedPreferences;

import com.szy.stardust.define.SpFiled;

/**
 * author: suzeyu on 16/6/6 16:14
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 操作localSp的操作类
 */
public class SpUtil {
    /**
     * 判断当前登录的类型是
     *
     * @return
     */
    public static boolean loginType() {
        //判断登陆状态
        SharedPreferences sp = UIUtils.getContext().getSharedPreferences("config", 0);
        return sp.getBoolean(SpFiled.LOGIN_STATUS, false);
    }
}
