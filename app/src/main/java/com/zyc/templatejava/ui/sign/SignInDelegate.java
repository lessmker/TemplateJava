package com.zyc.templatejava.ui.sign;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.zyc.core.delegates.KerDelegate;
import com.zyc.core.wechat.KerWeChat;
import com.zyc.core.wechat.callbacks.IWeChatSignInCallback;
import com.zyc.templatejava.R;
import com.zyc.templatejava.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 21:51
 * @Description:
 */
public class SignInDelegate extends KerDelegate {

    @BindView(R2.id.edit_sign_in_phone)
    AppCompatEditText mPhone = null;
    @BindView(R2.id.edit_sign_in_password)
    AppCompatEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {//登录
        //TODO: 这里以后要修改
//        if (checkForm()) {
        if (true) {
            SignHandler.onSignIn("", mISignListener);
        }
    }

    @OnClick(R2.id.tv_link_sign_in_up)
    void onClickSignInUp() {//注册
        startWithPop(new SignUpDelegate());
    }

    @OnClick(R2.id.tv_link_sign_in_forget)
    void onClickSignInForget() {//忘记密码
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignInWeChat() {//微信登录
        KerWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).SigIn();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    private boolean checkForm() {
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        if (phone.isEmpty() || (phone.length() != 11)) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || (password.length() < 6)) {
            mPassword.setError("请填写至少六位数密码");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        return isPass;
    }
}
