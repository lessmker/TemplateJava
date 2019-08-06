package com.zyc.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 22:16
 * @Description: 色值
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
