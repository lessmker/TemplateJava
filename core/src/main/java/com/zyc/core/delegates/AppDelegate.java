package com.zyc.core.delegates;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 20:58
 * @Description: 正式的Delegate
 */
public abstract class AppDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends AppDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
