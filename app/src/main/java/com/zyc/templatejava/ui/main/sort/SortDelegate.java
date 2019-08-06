package com.zyc.templatejava.ui.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.templatejava.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:06
 * @Description: 分类
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
