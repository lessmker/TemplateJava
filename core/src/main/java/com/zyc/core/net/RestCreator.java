package com.zyc.core.net;

import com.zyc.core.app.Ker;
import com.zyc.core.app.ConfigKeys;
import com.zyc.core.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 10:41
 * @Description: 网络建造者模式
 */
public class RestCreator {

    /**
     * 参数容器
     */
//    private static final class ParamsHolder {
//        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
//    }
//
//    public static WeakHashMap<String, Object> getParams() {
//        return ParamsHolder.PARAMS;
//    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Ker.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 构建OkHttp
     */
    private static final class OkHttpHolder {//对okhttp的惰性初始化
        private static final int TIME_OUT = 60;//添加延时
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Ker.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if ((INTERCEPTORS != null) && (!INTERCEPTORS.isEmpty())) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {//对restService的静态内部类
        private static final RestService REST_SREVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SREVICE;
    }

    /**
     * Service接口
     */
    private static final class RxRestServiceHolder {//对RxRestService的静态内部类
        private static final RxRestService REST_SREVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SREVICE;
    }
}
