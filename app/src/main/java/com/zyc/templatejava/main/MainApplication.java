package com.zyc.templatejava.main;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zyc.core.app.Ker;
import com.zyc.templatejava.ui.event.TestEvent;
import com.zyc.core.net.interceptors.DebugInterceptor;
import com.zyc.core.net.rx.AddCookieInterceptor;
import com.zyc.templatejava.R;
import com.zyc.templatejava.database.DataBaseManager;
import com.zyc.templatejava.icon.FontAppModule;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 19:06
 * @Description:
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Ker.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new IoniconsModule())
                .withIcon(new FontAppModule())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withInterceptor(new DebugInterceptor("index_data", R.raw.index_data))
                .withInterceptor(new DebugInterceptor("user_profile", R.raw.user_profile))
                .withInterceptor(new DebugInterceptor("sort_list", R.raw.sort_list))
                .withInterceptor(new DebugInterceptor("sort_content_list", R.raw.sort_content_list))
                .withWeChatAppId("")//微信AppId
                .withWeChatAppSecret("")//微信AppSecret
                .withJavascriptInterface("ker")//交互唯一校验key
                .withWebEvent("test", new TestEvent())//添加测试交互
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .withInterceptor(new AddCookieInterceptor())//cookie同步拦截器
                .configure();
        Stetho.initializeWithDefaults(this);//在浏览器中输入chrome://inspect/#devices
        DataBaseManager.getInstance().init(this);
        // 初始化MultiDex
        MultiDex.install(this);

        //Logger的初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
