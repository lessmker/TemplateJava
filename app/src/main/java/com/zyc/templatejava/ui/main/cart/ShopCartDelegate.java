package com.zyc.templatejava.ui.main.cart;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.templatejava.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:08
 * @Description: 购物车
 */
public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
