package com.zyc.templatejava.ui.main.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zyc.core.delegates.AppDelegate;
import com.zyc.templatejava.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 22:37
 * @Description: 商品详细页面
 */
public class GoodsDetailDelegate extends AppDelegate {
    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();//水平打开本界面
    }
}
