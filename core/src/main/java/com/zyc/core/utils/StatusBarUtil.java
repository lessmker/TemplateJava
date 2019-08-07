package com.zyc.core.utils;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/7 14:59
 * @Description: 状态栏与控件重叠解决
 */
public class StatusBarUtil {
    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
