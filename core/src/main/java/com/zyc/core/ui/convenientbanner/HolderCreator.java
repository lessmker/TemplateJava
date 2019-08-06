package com.zyc.core.ui.convenientbanner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 19:48
 * @Description:
 */
public class HolderCreator  implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}