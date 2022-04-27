package com.huanxin.workspace.http;


import static com.huanxin.workspace.Consts.LOGED;
import static com.huanxin.workspace.Consts.TOKEN;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.huanxin.workspace.MyApplication;
import com.huanxin.workspace.feature.login.LoginActivity;
import com.huanxin.workspace.util.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String path = originalRequest.url().encodedPath();

        Request authorised = originalRequest.newBuilder()
                .header("Authorization", "Bearer " + SharedPreferencesUtils.getParam(MyApplication.context, TOKEN, ""))
                .build();
        Response response = chain.proceed(authorised);
        try {
            JSONObject jsonObj = null;
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset UTF8 = Charset.forName("UTF-8");
            jsonObj = new JSONObject(buffer.clone().readString(UTF8));
            if (jsonObj.getInt("code") == 401) {
                //代表不是刷新token
                goToLoginActivity(MyApplication.context);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
        Looper.prepare();
        Toast.makeText(context, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(context, LOGED, false);
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        Looper.loop();

    }
}
