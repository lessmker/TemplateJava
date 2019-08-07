package com.zyc.core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 16:43
 * @Description: 对外的工具类
 */
public final class Ker {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getKerConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
