package com.zyc.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 16:43
 * @Description: 对外的工具类
 */
public final class App {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getAppConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
