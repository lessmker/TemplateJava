package com.zyc.templatejava.main;

import androidx.appcompat.app.ActionBar;

import android.os.Bundle;
import android.widget.Toast;

import com.zyc.core.activities.ProxyActivity;
import com.zyc.core.delegates.AppDelegate;
import com.zyc.core.ui.launcher.ILauncherListener;
import com.zyc.core.ui.launcher.OnLauncherFinishTag;
import com.zyc.templatejava.ui.launcher.LauncherDelegate;
import com.zyc.templatejava.ui.sign.ISignListener;
import com.zyc.templatejava.ui.sign.SignInDelegate;

/**
 * 单activity模式
 */
public class MainActivity extends ProxyActivity implements
        ISignListener, ILauncherListener {

    @Override
    public AppDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
    }

    private void initWindows() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "正在登录。。。", Toast.LENGTH_LONG).show();
        startWithPop(new MainDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功，正在登录。。。", Toast.LENGTH_LONG).show();
        startWithPop(new MainDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已经登录。。。", Toast.LENGTH_LONG).show();
                startWithPop(new MainDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录。。。", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
