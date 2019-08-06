package com.zyc.templatejava.ui.main.personal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.templatejava.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:08
 * @Description: 我的
 */
public class PersonalDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
