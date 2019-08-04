package com.zyc.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.ContentFrameLayout;

import com.zyc.core.R;
import com.zyc.core.delegates.AppDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 20:34
 * @Description: 容器activity
 */
public abstract class ProxyActivity extends SupportActivity {
    public abstract AppDelegate setRootDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout contenter =
                new ContentFrameLayout(this);
        contenter.setId(R.id.delegate_container);
        setContentView(contenter);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
