package com.huanxin.workspace.http;


import static com.huanxin.workspace.Config.BASE_URL;

import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.CallBean;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.CodeDetailBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.DeviceListBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.data.request.WorkspaceDispatchBean;
import com.huanxin.workspace.data.request.WorkspaceUpdateBean;
import com.huanxin.workspace.requestBean.BindReuest;
import com.huanxin.workspace.requestBean.LoginBean;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HttpJsonMethod {
    private static final int DEFAULT_TIMEOUT = 30;
    private BaseService baseService;

    private HttpJsonMethod() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new HttpLoggingInterceptor(message -> {
                    if (message.startsWith("{") || message.startsWith("[")) {
                        Logger.t("network").json(message);
                    } else {
                        Logger.t("network").d(message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        baseService = retrofit.create(BaseService.class);
    }

    private static class SingletonHolder {
        private static final HttpJsonMethod INSTANCE = new HttpJsonMethod();
    }

    /**
     * getInstance.（）
     *
     * @return the instance
     */
//获取单例
    public static HttpJsonMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void login(Subscriber<UserBean> subscriber, LoginBean loginBean) {
        baseService.login(loginBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void sendSMS(Subscriber<CodeBean> subscriber) {
        baseService.sendCode()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getMissedRecord(Subscriber<CallListBean> subscriber, int pageNum, int pageSize) {
        baseService.getMissedRecord(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getWorkList(Subscriber<WorkspaceListBean> subscriber, String engineerId, String createBy, int pageNum, int pageSize) {
        baseService.getWorkList(engineerId, createBy, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getCallList(Subscriber<CallBean> subscriber, int pageNum, int pageSize) {
        baseService.getCallList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void createWorkspace(Subscriber<BaseBean> subscriber, WorkspaceAddBean workspaceAddBean) {
        baseService.createWorkspace(workspaceAddBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void dispatchWorkspace(Subscriber<BaseBean> subscriber, WorkspaceDispatchBean workspaceDispatchBean) {
        baseService.dispatchWorkspace(workspaceDispatchBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void updateWorkspace(Subscriber<BaseBean> subscriber, WorkspaceUpdateBean workspaceUpdateBean) {
        baseService.updateWorkspace(workspaceUpdateBean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getWorkspaceDetail(Subscriber<WorkspaceDetailBean> subscriber, String id) {
        baseService.getWorkspaceDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getCodeDetail(Subscriber<CodeDetailBean> subscriber, String url) {
        baseService.getCodeDetail(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDeviceList(Subscriber<DeviceListBean> subscriber, String search) {
        baseService.getDeviceList(search)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDeviceDetail(Subscriber<DeviceDetailBean> subscriber, String id) {
        baseService.getDeviceDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void bindDevice(Subscriber<BaseBean> subscriber, BindReuest bindReuest) {
        baseService.bindDevice(bindReuest)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

