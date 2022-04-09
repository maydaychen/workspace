package com.huanxin.workspace.util;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;
import com.huanxin.workspace.data.UserTokenBean;

public class pageUtils {
    //dp转成PX
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static String getUserId(String token) {
        String strToken = new String(Base64.decode(token.split("\\.")[1], 0));
        Gson gson = new Gson();
        UserTokenBean personToken = gson.fromJson(strToken, UserTokenBean.class);
        return personToken.getEnterprise_id();
    }
}
