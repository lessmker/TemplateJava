package com.zyc.templatejava.ui.main.index;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.zyc.core.app.App;
import com.zyc.core.ui.recycler.RgbValue;
import com.zyc.templatejava.R;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 22:02
 * @Description: 沉浸式状态栏
 */
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout
            , @NonNull Toolbar child
            , @NonNull View directTargetChild
            , @NonNull View target
            , int axes
            , int type) {
        return true;
    }

    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout
            , @NonNull Toolbar toolbar
            , @NonNull View target
            , int dxConsumed
            , int dyConsumed
            , int dxUnconsumed
            , int dyUnconsumed
            , int type) {
        final int startOffset = 0;
        final Context context = App.getApplicationContext();
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
        mOffset += dyConsumed;
        if (mOffset <= startOffset) {
            toolbar.getBackground().setAlpha(0);
        } else if (mOffset > startOffset && mOffset < endOffset) {
            final float percent = (float) (mOffset - startOffset) / endOffset;
            final int alpha = Math.round(percent * 255);
            toolbar.getBackground().setAlpha(alpha);
        } else if (mOffset >= endOffset) {
            toolbar.getBackground().setAlpha(255);
        }
    }
}
