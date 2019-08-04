package com.zyc.core.app;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 23:00
 * @Description: 登录状态返回的接口
 */
public interface IUserChecker {
    void onSignIn();

    void onNotSignIn();
}
