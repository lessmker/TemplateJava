package com.zyc.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zyc.core.app.Ker;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 13:35
 * @Description: 测量工具方法
 */
public class DimenUtil {

    //得到屏幕的宽度
    public static int getScreenWidth() {
        final Resources resources = Ker.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    //得到屏幕的高度
    public static int getScreenHeight() {
        final Resources resources = Ker.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
