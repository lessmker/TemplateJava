package com.zyc.core.net.rx;

import android.content.Context;

import com.zyc.core.net.RestClient;
import com.zyc.core.net.callback.IError;
import com.zyc.core.net.callback.IFailure;
import com.zyc.core.net.callback.IRequest;
import com.zyc.core.net.callback.ISuccess;
import com.zyc.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 11:16
 * @Description: RestClient的建造者
 */
public class RxRestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {//新版本取消了
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mFile, mContext, mLoaderStyle);
    }
}
