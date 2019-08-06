package com.zyc.templatejava.ui.main.index;

import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zyc.core.net.rx.RxJsonBase.BaseRxPost;

import java.io.FileNotFoundException;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 18:42
 * @Description:
 */
public class index_data extends BaseRxPost {

    public index_data(String opt) {
        super(opt);
    }

    @Override
    public boolean ParsReturnData() throws FileNotFoundException {
        dataMessage = new Message();
        boolean judge = false;
        try {
            judge = true;
            dataMessage.arg1 = 0;
            dataMessage.obj = getRevJson();
        } catch (JSONException e) {
            e.printStackTrace();
            judge = false;
            dataMessage.arg1 = 1;
        }
        return judge;
    }
}
