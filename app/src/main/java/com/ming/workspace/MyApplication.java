package com.ming.workspace;

import android.app.Application;
import android.content.Context;

import com.ming.workspace.util.CustomLogCatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/7  18:05
 * desc   :
 * version: 1.0
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //网络请求增加log
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new CustomLogCatStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .build();

        String a = "";
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


}
