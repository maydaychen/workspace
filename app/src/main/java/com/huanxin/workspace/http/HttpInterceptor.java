package com.huanxin.workspace.http;


import static com.huanxin.workspace.Consts.TOKEN;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.huanxin.workspace.MyApplication;
import com.huanxin.workspace.feature.login.LoginActivity;
import com.huanxin.workspace.util.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/9/6  17:40
 * desc   :
 * version: 1.0
 */
public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String path = originalRequest.url().encodedPath();

        Request authorised = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + SharedPreferencesUtils.getParam(MyApplication.context, TOKEN, ""))
                .build();
        Response response = chain.proceed(authorised);
        if (response.code() == 401) {
            //代表不是刷新token
            goToLoginActivity(MyApplication.context);
        }
        return response;
    }

    /**
     * 要求用户直接登录
     *
     * @param context 上下文
     */
    private void goToLoginActivity(Context context) {
//        String username = (String) SharedPreferencesUtils.getParam(MyApplication.context, "username", "");
//        String pass = (String) SharedPreferencesUtils.getParam(MyApplication.context, "pass", "");
//        SharedPreferencesUtils.clear(MyApplication.context);
//        SharedPreferencesUtils.setParam(MyApplication.context, "first", false);
//        SharedPreferencesUtils.setParam(MyApplication.context, "username", username);
//        SharedPreferencesUtils.setParam(MyApplication.context, "pass", pass);
        Toast.makeText(context, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
