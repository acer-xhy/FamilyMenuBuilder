package com.xiong.familymenubuilder.utils;

import android.text.TextUtils;
import android.util.Log;

import com.xiong.familymenubuilder.BuildConfig;

/**
 * Created by xsw on 2016/10/27.
 */
public class LogUtils {
    private static boolean showLog = BuildConfig.DEBUG;

    public static void xswShowLog(String msg) {
        if (showLog && !TextUtils.isEmpty(msg)) {
            Log.i("xsw", msg);
        }
    }

}
