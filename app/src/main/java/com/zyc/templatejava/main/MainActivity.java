package com.zyc.templatejava.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zyc.core.activities.ProxyActivity;
import com.zyc.core.app.App;
import com.zyc.core.delegates.AppDelegate;
import com.zyc.templatejava.icon.FontAppModule;

/**
 * 单activity模式
 */
public class MainActivity extends ProxyActivity {

    @Override
    public AppDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
