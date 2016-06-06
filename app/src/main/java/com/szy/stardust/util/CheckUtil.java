package com.szy.stardust.util;

/**
 * author: suzeyu on 16/6/6 15:46
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :
 */
public class CheckUtil {

    /**
     * @param  reference an object reference
     * @return the non-null reference that was validated
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
