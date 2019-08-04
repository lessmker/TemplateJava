package com.zyc.core.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatDialog;

import com.wang.avi.AVLoadingIndicatorView;
import com.zyc.core.R;
import com.zyc.core.utils.DimenUtil;

import java.util.ArrayList;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/4 13:00
 * @Description:
 */
public class AppLoader {

    //设置屏占比,可以根据屏幕的大小自动调整
    private static final int LOADER_SIZE_SCALE = 8;

    //屏幕的偏移量
    private static final int LOADER_OFFSET_SCALE = 10;

    //存储所有的loader
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    //默认loading样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        final LoaderDialog dialog = new LoaderDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.creat(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int screenWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = screenWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();//会执行onCancel的回调
//                dialog.dismiss();//单纯消失
            }
        }
    }
}
