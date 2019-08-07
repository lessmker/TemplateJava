package com.zyc.templatejava.ui.event;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.widget.Toast;

import com.zyc.core.delegates.web.event.Event;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/7 18:40
 * @Description: 测试Event
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
