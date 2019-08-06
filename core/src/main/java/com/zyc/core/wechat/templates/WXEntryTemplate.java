package com.zyc.core.wechat.templates;

import com.zyc.core.wechat.AppWeChat;
import com.zyc.core.wechat.BaseWXEntryActivity;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 14:27
 * @Description:
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        AppWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
