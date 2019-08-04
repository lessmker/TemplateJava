package com.zyc.templatejava.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 17:38
 * @Description: 添加第三方矢量图标ttf字体
 */
public class FontAppModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return AppIcons.values();
    }
}
