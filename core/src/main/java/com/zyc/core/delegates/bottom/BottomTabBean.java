package com.zyc.core.delegates.bottom;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 16:24
 * @Description: 存储Tab的bean类
 */
public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

}
