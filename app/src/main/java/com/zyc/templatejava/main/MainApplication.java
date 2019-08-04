package com.zyc.templatejava.main;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.zyc.core.app.App;
import com.zyc.core.net.interceptors.DebugInterceptor;
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
        App.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new IoniconsModule())
                .withIcon(new FontAppModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        Stetho.initializeWithDefaults(this);//在浏览器中输入chrome://inspect/#devices
        DataBaseManager.getInstance().init(this);
    }
}
