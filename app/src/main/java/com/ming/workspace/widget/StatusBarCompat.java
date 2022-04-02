package com.ming.workspace.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/8/19 17:31
 * desc   :
 * version: 1.0
 */

public class StatusBarCompat {

    private static final int INVALID_VAL = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor)
    {
        //当前手机版本为5.0及以上
        if (statusColor != INVALID_VAL)
        {
            activity.getWindow().setStatusBarColor(statusColor);
        }
    }

    public static void compat(Activity activity)
    {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}