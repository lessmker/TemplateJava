package com.zyc.core.ui.launcher;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 19:22
 * @Description: banner的初始化
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView imageView = null;

    @Override
    public View createView(Context context) {
        imageView = new AppCompatImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setBackgroundResource(data);
    }
}
