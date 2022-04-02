package com.ming.workspace.http;

import com.ming.workspace.data.BaseBean;
import com.ming.workspace.data.CallListBean;
import com.ming.workspace.data.CodeBean;
import com.ming.workspace.data.CodeDetailBean;
import com.ming.workspace.data.DeviceListBean;
import com.ming.workspace.data.UserBean;
import com.ming.workspace.requestBean.BindReuest;
import com.ming.workspace.requestBean.LoginBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/8/19 17:31
 * desc   :
 * version: 1.0
 */

public interface BaseService {
    @POST("auth/login")
    rx.Observable<UserBean> login(@Body LoginBean loginBean);

    @GET("code")
    rx.Observable<CodeBean> sendCode();

    @GET("qrcode/deviceFacetimeRecord/getMissedRecord")
    rx.Observable<CallListBean> getMissedRecord(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("qrcode/qrcode/byUrl")
    rx.Observable<CodeDetailBean> getCodeDetail(@Query("url") String url);

    @GET("qrcode/device/getUnbindDevice")
    rx.Observable<DeviceListBean> getDeviceList(@Query("search") String search);

    @POST("qrcode/device/bind")
    rx.Observable<BaseBean> bindDevice(@Body BindReuest bindReuest);

}