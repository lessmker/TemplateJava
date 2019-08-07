package com.zyc.templatejava.ui.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.zyc.core.R2;
import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.templatejava.R;
import com.zyc.templatejava.ui.main.sort.content.ContentDelegate;
import com.zyc.templatejava.ui.main.sort.list.VerticalListDelegate;

import butterknife.BindView;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:06
 * @Description: 分类
 */
public class SortDelegate extends BottomItemDelegate {

    @BindView(R2.id.base_tool_bar_tv_on)
    AppCompatTextView compatTextView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        compatTextView.setText("分类");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
