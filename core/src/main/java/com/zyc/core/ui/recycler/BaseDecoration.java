package com.zyc.core.ui.recycler;



import androidx.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 20:12
 * @Description:
 */
public class BaseDecoration extends DividerItemDecoration {
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }

}
