package com.zyc.templatejava.ui.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.zyc.core.delegates.AppDelegate;
import com.zyc.core.net.RestClient;
import com.zyc.core.net.callback.ISuccess;
import com.zyc.templatejava.R;
import com.zyc.templatejava.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 20:09
 * @Description: 注册界面
 */
public class SignUpDelegate extends AppDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
//        if (checkForm()) {
        if (true) {
//            postSignUp();//上传到服务器
            SignHandler.onSignUp("", mISignListener);
        }
    }

    @OnClick(R2.id.tv_link_sign_up_in)
    void onClickSignIn() {
        startWithPop(new SignInDelegate());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void postSignUp() {
        RestClient.builder()
                .url("sign_up")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                }).build().post();
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }
        if (email.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
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
        if (rePassword.isEmpty() || !(rePassword.equals(password))) {
            mPassword.setError("密码验证错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        return isPass;
    }
}
