package com.zyc.core.net.callback;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 11:16
 * @Description: 网络请求接口
 */

public interface IRequest {

    void onRequestStart();

    void onRequestEnd();
}
