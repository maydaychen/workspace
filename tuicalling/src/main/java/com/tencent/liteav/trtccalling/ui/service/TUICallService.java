package com.tencent.liteav.trtccalling.ui.service;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Base64;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.ServiceUtils;
import com.google.gson.Gson;
import com.tencent.liteav.basic.UserTokenBean;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * TUICalling组件保活, 确保应用在后台不被系统杀死, 如不需要,可删除.
 */
public class TUICallService extends Service {
    private static final int NOTIFICATION_ID = 1001;
    public Application application;

    public Context context;
    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 15 * 60 * 1000;//每隔15秒进行一次对长连接的心跳检测
    private static final String WEBSOCKET_HOST_AND_PORT = "ws://qr3.keyicloud.cn:9800/websocket/";//可替换为自己的主机名和端口号
    private WebSocket mWebSocket;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取服务通知
        Notification notification = createForegroundNotification();
        //将服务置于启动状态 ,NOTIFICATION_ID指的是创建的通知的ID
        startForeground(NOTIFICATION_ID, notification);
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
        context = getApplicationContext();
        new InitSocketThread().start();
//        Log.e("chenyi", "onCreate------------*************-------------");
    }

    public static void start(Context context) {
        if (ServiceUtils.isServiceRunning(TUICallService.class)) {
            return;
        }
        Intent starter = new Intent(context, TUICallService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(starter);
        } else {
            context.startService(starter);
        }
    }

    public static void stop(Context context) {
        Intent intent = new Intent(context, TUICallService.class);
        context.stopService(intent);
    }

    private Notification createForegroundNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 唯一的通知通道的id.
        String notificationChannelId = "notification_channel_id_01";
        // Android8.0以上的系统，新建消息通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //用户可见的通道名称
            String channelName = "TRTC Foreground Service Notification";
            //通道的重要程度
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(
                    notificationChannelId, channelName, importance);
            notificationChannel.setDescription("视频通话推送渠道");
            //震动
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, notificationChannelId);
        //创建通知并返回
        return builder.build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
    }

    public class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                initSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // 初始化socket
        private void initSocket() throws UnknownHostException, IOException {
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
            SharedPreferences sp = context.getSharedPreferences("share_date", Context.MODE_PRIVATE);
            String token = sp.getString("token", "");
            String strToken = new String(Base64.decode(token.split("\\.")[1], 0));
            Gson gson = new Gson();
            UserTokenBean personToken = gson.fromJson(strToken, UserTokenBean.class);
            String userId = personToken.getUser_id();
//            Log.e("chenyi", WEBSOCKET_HOST_AND_PORT + userId);
            Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT + userId).build();
            client.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    //开启长连接成功的回调
                    super.onOpen(webSocket, response);
                    mWebSocket = webSocket;
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {//接收消息的回调
                    super.onMessage(webSocket, text);
                    //收到服务器端传过来的消息text
//                    Log.e("chenyi", "收到来自后台的信息-------------" + text);
                }

                @Override
                public void onMessage(WebSocket webSocket, ByteString bytes) {
                    super.onMessage(webSocket, bytes);
                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    super.onClosing(webSocket, code, reason);
                }

                @Override
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    super.onClosed(webSocket, code, reason);
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                    super.onFailure(webSocket, t, response);
                }
            });
            client.dispatcher().executorService().shutdown();
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
        }

        private long sendTime = 0L;
        // 发送心跳包
        private final Handler mHandler = new Handler();
        private final Runnable heartBeatRunnable = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                    boolean isSuccess = mWebSocket.send("{\"actionCode\":\"kf_heart_beat\"}");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                    if (!isSuccess) {//长连接已断开
                        mHandler.removeCallbacks(heartBeatRunnable);
                        mWebSocket.cancel();//取消掉以前的长连接
                        new InitSocketThread().start();//创建一个新的连接
                    } else {//长连接处于连接状态
                        //长连接处于连接状态---
//                        Log.e("chenyi", "发送心跳包-------------长连接处于连接状态");
                    }
//                    SharedPreferences sp = context.getSharedPreferences("share_date", Context.MODE_PRIVATE);
//                    String token = sp.getString("token", "");
//                    String strToken = new String(Base64.decode(token.split("\\.")[1], 0));
//                    Gson gson = new Gson();
//                    UserTokenBean personToken = gson.fromJson(strToken, UserTokenBean.class);
//                    String userId = personToken.getUser_id();
//                    String url = "https://qr3.keyicloud.cn/dev-api/system/user/syncKfLogin/" + userId;
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    final Request request = new Request.Builder()
//                            .url(url)
//                            .get()//默认就是GET请求，可以不写
//                            .build();
//                    Call call = okHttpClient.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            Log.d("xintiao", "onFailure: ");
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            Log.d("xintiao", "onResponse: " + response.body().string());
//                        }
//                    });
                    sendTime = System.currentTimeMillis();
                }
                mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
            }
        };


    }

}
