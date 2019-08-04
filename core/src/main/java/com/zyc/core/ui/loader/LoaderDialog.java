package com.zyc.core.ui.loader;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;

import com.zyc.core.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 13:27
 * @Description: 对Androidx的AppCompatDialog实例化
 */
public class LoaderDialog extends AppCompatDialog {
    public LoaderDialog(Context context, int theme) {
        super(context, getThemeResId(context, theme));
        getDelegate().onCreate(null);
        getDelegate().applyDayNight();
    }

    private static int getThemeResId(Context context, int themeId) {
        if (themeId == 0) {
            // If the provided theme is 0, then retrieve the dialogTheme from our theme
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.dialogTheme, outValue, true);
            themeId = outValue.resourceId;
        }
        return themeId;
    }
}
