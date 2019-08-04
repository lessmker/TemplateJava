package com.zyc.core.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 17:24
 * @Description:
 */
public interface RxRestService {

    //get请求接口
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    //post请求接口
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    //post原始数据
    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    //put请求接口
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    //post原始数据
    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    //删除请求接口
    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    //下载请求接口
    @Streaming//边下载，边写入，使用异步方法
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    //上传请求接口
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
