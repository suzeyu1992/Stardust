package com.szy.stardust.base;

/**
 * author: suzeyu on 16/6/6 15:06
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : mvp中v的基础特性
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

}
