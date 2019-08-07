package com.zyc.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.zyc.core.delegates.web.event.Event;
import com.zyc.core.delegates.web.event.EventManager;
import com.zyc.core.utils.KerLogger;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/7 15:51
 * @Description: 与原生进行交互的
 */
public class KerWebInterface {
    private final WebDelegate DELEGATE;

    private KerWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static KerWebInterface create(WebDelegate delegate) {
        return new KerWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        //js的返回值
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        KerLogger.d("WEB_EVENT", params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
