package com.huanxin.workspace.http;


import static com.huanxin.workspace.Config.BASE_URL;

import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.CodeDetailBean;
import com.huanxin.workspace.data.DeviceListBean;
import com.huanxin.workspace.data.UserBean;
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

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/8/19 17:31
 * desc   :
 * version: 1.0
 */

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

    public void bindDevice(Subscriber<BaseBean> subscriber, BindReuest bindReuest) {
        baseService.bindDevice(bindReuest)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

