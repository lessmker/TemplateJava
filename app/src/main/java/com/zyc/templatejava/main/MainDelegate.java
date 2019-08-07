package com.zyc.templatejava.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyc.core.delegates.KerDelegate;
import com.zyc.core.net.RestClient;
import com.zyc.core.net.callback.IError;
import com.zyc.core.net.callback.IFailure;
import com.zyc.core.net.callback.ISuccess;
import com.zyc.templatejava.R;

import java.util.WeakHashMap;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 21:13
 * @Description: 主测试Delegate
 */
public class MainDelegate extends KerDelegate {

    final WeakHashMap<String, Object> params = new WeakHashMap<>();

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        onCallRxGet();
//        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/test")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("asdebug", response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

    //TODO:RxJava测试方法没啥用
    @SuppressLint("HandlerLeak")
    private void onCallRxGet() {
        test t = new test("test");
        t.dataHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 0) {
                    Toast.makeText(getContext(), (String) msg.obj, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "网络访问失败", Toast.LENGTH_LONG).show();
                }
            }
        };
        t.RunDataAsync();
    }
}
