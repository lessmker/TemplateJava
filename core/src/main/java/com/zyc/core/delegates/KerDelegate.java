package com.zyc.core.delegates;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/3 20:58
 * @Description: 正式的Delegate
 */
public abstract class KerDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends KerDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
