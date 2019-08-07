package com.zyc.core.delegates.web.event;

import com.zyc.core.utils.KerLogger;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/7 15:55
 * @Description:
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        KerLogger.e("UndefineEvent", params);
        return null;
    }
}
