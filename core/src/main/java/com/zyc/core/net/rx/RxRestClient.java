package com.zyc.core.net.rx;

import android.content.Context;

import com.zyc.core.net.HttpMethod;
import com.zyc.core.net.RestCreator;
import com.zyc.core.net.callback.IError;
import com.zyc.core.net.callback.IFailure;
import com.zyc.core.net.callback.IRequest;
import com.zyc.core.net.callback.ISuccess;
import com.zyc.core.net.callback.RequestCallbacks;
import com.zyc.core.net.download.DownloadHandler;
import com.zyc.core.ui.loader.AppLoader;
import com.zyc.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 21:54
 * @Description: 网络请求的具体实现类
 */
public class RxRestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEEXT;

    public RxRestClient(String url, WeakHashMap<String, Object> params, RequestBody body,
                        File file, Context context, LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.CONTEEXT = context;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            AppLoader.showLoading(CONTEEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW://post原始数据
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD://上传
                final RequestBody requestBody = RequestBody.create(
                        MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }
}
