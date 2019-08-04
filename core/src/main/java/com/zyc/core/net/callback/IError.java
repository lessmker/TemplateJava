package com.zyc.core.net.callback;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 11:16
 * @Description: 网络请求错误接口
 */

public interface IError {

    void onError(int code, String msg);
}
