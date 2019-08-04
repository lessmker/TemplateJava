package com.zyc.core.net;

import com.zyc.core.app.App;
import com.zyc.core.app.ConfigType;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 10:41
 * @Description: 网络建造者模式
 */
public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestSrevice() {
        return RestServiceHolder.REST_SREVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) App.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {//对okhttp的惰性初始化
        private static final int TIME_OUT = 60;//添加延时
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {//对restService的静态内部类
        private static final RestService REST_SREVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
