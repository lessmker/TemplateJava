package com.zyc.core.net.rx.RxJsonBase;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zyc.core.net.RestCreator;
import com.zyc.core.net.rx.RxRestClient;
import com.zyc.core.utils.KerLogger;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 10:19
 * @Description: RxJava的get封装
 */
public abstract class BaseRxGet {

    //地址接口
    private String URL = null;

    //接收数据
    private String REVJSON = null;

    //判段是否接收成功
    private boolean REV_SUCCESS = false;


    public abstract boolean ParsReturnData() throws FileNotFoundException;

    //
    public Handler dataHandler = null;
    public Message dataMessage = null;

    public BaseRxGet(String opt) {
        this.URL = opt;
    }

    public String getRevJson() {
        return REVJSON;
    }

    private void successRun() {
        try {
            REV_SUCCESS = this.ParsReturnData();
            if (this.dataMessage != null && this.dataHandler != null) {
                this.dataHandler.sendMessage(dataMessage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void errorRun() {
        this.dataMessage = new Message();
        this.dataMessage.arg1 = 1;
        this.dataMessage.obj = null;
        if (this.dataMessage != null && this.dataHandler != null) {
            this.dataHandler.sendMessage(this.dataMessage);
        }
    }

    public void RunDataAsync(WeakHashMap<String, Object> params) {
        (new Thread(new Runnable() {
            public void run() {
                final Observable<String> observable = RestCreator.getRxRestService()
                        .get(URL, params);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull String s) {
                                REVJSON = s;
                                REV_SUCCESS = true;
                                KerLogger.json("接收数据", s);
                                successRun();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Log.e("接收数据失败", Objects.requireNonNull(e.getMessage()));
                                errorRun();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        })).start();
    }
    public void RunDataAsync() {
        (new Thread(new Runnable() {
            public void run() {
                RxRestClient.builder()
                        .url(URL)
                        .build()
                        .get()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull String s) {
                                REVJSON = s;
                                REV_SUCCESS = true;
                                KerLogger.json("接收数据", s);
                                successRun();
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Log.e("接收数据失败", Objects.requireNonNull(e.getMessage()));
                                errorRun();
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        })).start();
    }
}
