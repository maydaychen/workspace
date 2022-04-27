package com.huanxin.workspace.http;

import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.CallBean;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.CodeDetailBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.DeviceListBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.data.request.WorkspaceDispatchBean;
import com.huanxin.workspace.data.request.WorkspaceUpdateBean;
import com.huanxin.workspace.requestBean.BindReuest;
import com.huanxin.workspace.requestBean.LoginBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BaseService {
    @POST("auth/login")
    rx.Observable<UserBean> login(@Body LoginBean loginBean);

    @GET("code")
    rx.Observable<CodeBean> sendCode();

    @GET("qrcode/deviceFacetimeRecord/getMissedRecord")
    rx.Observable<CallListBean> getMissedRecord(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("qrcode/work-ticket/list")
    rx.Observable<WorkspaceListBean> getWorkList(@Query("engineerId") String engineerId, @Query("createBy") String createBy,
                                                 @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("qrcode/deviceFacetimeRecord/list")
    rx.Observable<CallBean> getCallList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @POST("qrcode/work-ticket/add")
    rx.Observable<BaseBean> createWorkspace(@Body WorkspaceAddBean workspaceAddBean);

    @POST("qrcode/work-ticket/dispatch")
    rx.Observable<BaseBean> dispatchWorkspace(@Body WorkspaceDispatchBean workspaceDispatchBean);

    @POST("qrcode/work-ticket/update")
    rx.Observable<BaseBean> updateWorkspace(@Body WorkspaceUpdateBean workspaceUpdateBean);

    @GET("qrcode/work-ticket/get/{id}")
    rx.Observable<BaseBean> getWorkspaceDetail(@Path("id") String id);

    @GET("qrcode/qrcode/byUrl")
    rx.Observable<CodeDetailBean> getCodeDetail(@Query("url") String url);

    @GET("qrcode/device/getUnbindDevice")
    rx.Observable<DeviceListBean> getDeviceList(@Query("search") String search);

    @GET("qrcode/device/byId")
    rx.Observable<DeviceDetailBean> getDeviceDetail(@Query("id") String id);

    @POST("qrcode/device/bind")
    rx.Observable<BaseBean> bindDevice(@Body BindReuest bindReuest);

}