package com.zyc.core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.zyc.core.R;
import com.zyc.core.app.App;
import com.zyc.core.delegates.AppDelegate;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 16:14
 * @Description:
 */
public abstract class BottomItemDelegate extends AppDelegate implements View.OnKeyListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
            if (TOUCH_TIME != 0) {
                TOUCH_TIME = 0;
            }
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + App.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }
}
