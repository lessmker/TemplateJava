package com.zyc.core.utils.timer;

import java.util.TimerTask;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 18:21
 * @Description: 计时器工具类
 */
public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
