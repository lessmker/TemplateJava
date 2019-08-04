package com.zyc.core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 22:01
 * @Description: 网络接口与方法用来具体的请求
 */
public interface RestService {

    //get请求接口
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    //post请求接口
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    //put请求接口
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    //删除请求接口
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    //下载请求接口
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    //上传请求接口
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
