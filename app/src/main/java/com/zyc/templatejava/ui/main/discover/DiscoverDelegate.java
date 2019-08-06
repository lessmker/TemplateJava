package com.zyc.templatejava.ui.main.discover;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.templatejava.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:07
 * @Description: 发现
 */
public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
