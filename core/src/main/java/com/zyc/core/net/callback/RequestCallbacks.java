package com.zyc.core.net.callback;

import android.os.Handler;

import com.zyc.core.ui.loader.KerLoader;
import com.zyc.core.ui.loader.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 12:12
 * @Description:
 */
public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();//加static避免内存泄露

    public RequestCallbacks(IRequest request, ISuccess success,
                            IFailure failure, IError error, LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stoploading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stoploading();
    }

    private void stoploading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {//延时1s
                @Override
                public void run() {
                    KerLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
