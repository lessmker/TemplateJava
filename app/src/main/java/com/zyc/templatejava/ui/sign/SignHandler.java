package com.zyc.templatejava.ui.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyc.core.app.AccountManager;
import com.zyc.templatejava.database.DataBaseManager;
import com.zyc.templatejava.database.UserProfile;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 22:52
 * @Description:
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(1l, "张三", "xxx", "男", "山东");
        DataBaseManager.getInstance().getDao().insert(profile);
        //注册完成并开始登录
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
