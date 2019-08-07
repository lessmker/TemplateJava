package com.zyc.templatejava.ui.main.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.zyc.core.R2;
import com.zyc.core.delegates.IPageLoadListener;
import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.core.delegates.web.WebDelegateImpl;
import com.zyc.core.ui.launcher.ILauncherListener;
import com.zyc.core.ui.launcher.OnLauncherFinishTag;
import com.zyc.templatejava.R;
import com.zyc.templatejava.ui.main.MainBottomDelegate;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:07
 * @Description: 发现
 */
public class DiscoverDelegate extends BottomItemDelegate {

    @BindView(R2.id.base_tool_bar_tv_on)
    AppCompatTextView mToolBar = null;

    private IPageLoadListener listener = null;//使用时直接调用

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
    }

    private void initView() {
        mToolBar.setText("发现");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
