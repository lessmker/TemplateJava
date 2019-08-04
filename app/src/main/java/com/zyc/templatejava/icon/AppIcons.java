package com.zyc.templatejava.icon;

import com.joanzapata.iconify.Icon;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 17:43
 * @Description:将第三方矢量图标代码加入
 */
public enum AppIcons implements Icon {
    icon_scan('\ue694'),
    icon_ali_pay('\ue626');

    private char character;

    AppIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
