package com.zyc.templatejava.ui.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.zyc.core.app.AccountManager;
import com.zyc.core.app.IUserChecker;
import com.zyc.core.delegates.AppDelegate;
import com.zyc.core.ui.launcher.ILauncherListener;
import com.zyc.core.ui.launcher.LauncherHolderCreator;
import com.zyc.core.ui.launcher.OnLauncherFinishTag;
import com.zyc.core.ui.launcher.ScrollLauncherTag;
import com.zyc.core.utils.AppPreference;
import com.zyc.templatejava.R;

import java.util.ArrayList;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 19:10
 * @Description: 轮播图
 */
public class LauncherScrollDelegate extends AppDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_1);
        INTEGERS.add(R.mipmap.launcher_2);
        INTEGERS.add(R.mipmap.launcher_3);
        INTEGERS.add(R.mipmap.launcher_4);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)//启动页 绑定data
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})//底部数量显示
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//水平居中
                .setOnItemClickListener(this)//添加点击事件
                .setCanLoop(false);//设置不可以循环
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == (INTEGERS.size() - 1)) {
            AppPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
