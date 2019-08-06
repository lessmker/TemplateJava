package com.zyc.templatejava.main;

import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zyc.core.net.rx.RxJsonBase.BaseRxGet;
import com.zyc.core.net.rx.RxJsonBase.BaseRxPost;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 12:08
 * @Description:
 */
public class test extends BaseRxPost {
    final WeakHashMap<String, Object> params = new WeakHashMap<>();
    public test(String opt) {
        super(opt);
    }

    @Override
    public boolean ParsReturnData() throws FileNotFoundException {
        dataMessage = new Message();
        boolean judge = false;
        try {
            JSONObject object = JSON.parseObject(getRevJson());
            String s = object.getString("key");
            judge = true;
            dataMessage.arg1 = 0;
            dataMessage.obj = s;
        } catch (JSONException e) {
            e.printStackTrace();
            judge = false;
            dataMessage.arg1 = 1;
        }
        return judge;
    }
}
