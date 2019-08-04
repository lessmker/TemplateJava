package com.zyc.core.net.callback;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 11:16
 * @Description: 网络请求成功并返回数据接口
 */

public interface ISuccess {

    void onSuccess(String response);
}
