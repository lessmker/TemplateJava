package com.zyc.templatejava.ui.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.zyc.core.app.AccountManager;
import com.zyc.core.app.IUserChecker;
import com.zyc.core.delegates.AppDelegate;
import com.zyc.core.ui.launcher.ILauncherListener;
import com.zyc.core.ui.launcher.OnLauncherFinishTag;
import com.zyc.core.ui.launcher.ScrollLauncherTag;
import com.zyc.core.utils.AppPreference;
import com.zyc.core.utils.timer.BaseTimerTask;
import com.zyc.core.utils.timer.ITimerListener;
import com.zyc.templatejava.R;
import com.zyc.templatejava.R2;
import com.zyc.templatejava.ui.sign.SignUpDelegate;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 18:18
 * @Description: 启动图
 */
public class LauncherDelegate extends AppDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);//计时器绑定,开始时间,时间长度
    }

    //判断是否展示滑动演示页
    private void checkIsShowScroll() {
        if (!AppPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            startWithPop(new LauncherScrollDelegate());
        } else {//检查用户是否已经登录APP
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_laucher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if ((mCount < 0) && (mTimer != null)) {
                        mTimer.cancel();
                        mTimer = null;
                        checkIsShowScroll();
                    }
                }
            }
        });
    }
}
