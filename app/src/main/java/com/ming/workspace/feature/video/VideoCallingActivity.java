package com.ming.workspace.feature.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.workspace.Consts;
import com.ming.workspace.R;
import com.tencent.liteav.TXLiteAVCode;
import com.tencent.liteav.device.TXDeviceManager;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;
import com.tencent.trtc.TRTCCloudListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VideoCallingActivity extends TRTCBaseActivity implements View.OnClickListener {

    private static final String TAG = "VideoCallingActivity";
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    private TextView mTextTitle;
    private TXCloudVideoView mTXCVVLocalPreviewView;
    private Button mButtonMuteVideo;
    private Button mButtonMuteAudio;
    private Button mButtonSwitchCamera;
    private Button mButtonAudioRoute;

    private TRTCCloud mTRTCCloud;
    private TXDeviceManager mTXDeviceManager;
    private boolean mIsFrontCamera = true;
    private List<String> mRemoteUidList;
    private List<TXCloudVideoView> mRemoteViewList;
    private int mUserCount = 0;
    private String mRoomId;
    private String mUserId;
    private boolean mAudioRouteFlag = true;
    private FloatingView mFloatingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();
        handleIntent();

        if (checkPermission()) {
            initView();
            enterRoom();
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (null != intent) {
            if (intent.getStringExtra(Consts.USER_ID) != null) {
                mUserId = intent.getStringExtra(Consts.USER_ID);
            }
            if (intent.getStringExtra(Consts.ROOM_ID) != null) {
                mRoomId = intent.getStringExtra(Consts.ROOM_ID);
            }
        }
    }

    private void initView() {
        mTextTitle = findViewById(R.id.tv_room_number);
        ImageView mImageBack = findViewById(R.id.iv_back);
        mTXCVVLocalPreviewView = findViewById(R.id.txcvv_main);
        mButtonMuteVideo = findViewById(R.id.btn_mute_video);
        mButtonMuteAudio = findViewById(R.id.btn_mute_audio);
        mButtonSwitchCamera = findViewById(R.id.btn_switch_camera);
        mButtonAudioRoute = findViewById(R.id.btn_audio_route);

        if (!TextUtils.isEmpty(mRoomId)) {
            mTextTitle.setText(getString(R.string.videocall_roomid) + mRoomId);
        }
        mImageBack.setOnClickListener(this);
        mButtonMuteVideo.setOnClickListener(this);
        mButtonMuteAudio.setOnClickListener(this);
        mButtonSwitchCamera.setOnClickListener(this);
        mButtonAudioRoute.setOnClickListener(this);

        mRemoteUidList = new ArrayList<>();
        mRemoteViewList = new ArrayList<>();
        mRemoteViewList.add(findViewById(R.id.trtc_view_1));
        mRemoteViewList.add(findViewById(R.id.trtc_view_2));
        mRemoteViewList.add(findViewById(R.id.trtc_view_3));
        mRemoteViewList.add(findViewById(R.id.trtc_view_4));
        mRemoteViewList.add(findViewById(R.id.trtc_view_5));
        mRemoteViewList.add(findViewById(R.id.trtc_view_6));

        mFloatingView = new FloatingView(getApplicationContext(), R.layout.videocall_view_floating_default);
        mFloatingView.setPopupWindow(R.layout.videocall_popup_layout);
        mFloatingView.setOnPopupItemClickListener(this);
    }

    private void enterRoom() {
        mTRTCCloud = TRTCCloud.sharedInstance(getApplicationContext());
        mTRTCCloud.setListener(new TRTCCloudImplListener(VideoCallingActivity.this));
        mTXDeviceManager = mTRTCCloud.getDeviceManager();

        TRTCCloudDef.TRTCParams trtcParams = new TRTCCloudDef.TRTCParams();
        trtcParams.sdkAppId = GenerateTestUserSig.SDKAPPID;
        trtcParams.userId = mUserId;
        trtcParams.roomId = Integer.parseInt(mRoomId);
        trtcParams.userSig = GenerateTestUserSig.genTestUserSig(trtcParams.userId);

        mTRTCCloud.startLocalPreview(mIsFrontCamera, mTXCVVLocalPreviewView);
        mTRTCCloud.startLocalAudio(TRTCCloudDef.TRTC_AUDIO_QUALITY_SPEECH);
        mTRTCCloud.enterRoom(trtcParams, TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        requestDrawOverLays();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null && mFloatingView.isShown()) {
            mFloatingView.dismiss();
        }
        exitRoom();
    }

    private void exitRoom() {
        if (mTRTCCloud != null) {
            mTRTCCloud.stopLocalAudio();
            mTRTCCloud.stopLocalPreview();
            mTRTCCloud.exitRoom();
            mTRTCCloud.setListener(null);
        }
        mTRTCCloud = null;
        TRTCCloud.destroySharedInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mFloatingView != null && mFloatingView.isShown()) {
            mFloatingView.dismiss();
        }
    }

    @Override
    protected void onPermissionGranted() {
        initView();
        enterRoom();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.btn_mute_video) {
            muteVideo();
        } else if (id == R.id.btn_mute_audio) {
            muteAudio();
        } else if (id == R.id.btn_switch_camera) {
            switchCamera();
        } else if (id == R.id.btn_audio_route) {
            audioRoute();
        } else if (id == R.id.iv_return) {
            floatViewClick();
        }
    }

    private void floatViewClick() {
        Intent intent = new Intent(this, VideoCallingActivity.class);
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void muteVideo() {
        boolean isSelected = mButtonMuteVideo.isSelected();
        if (!isSelected) {
            mTRTCCloud.stopLocalPreview();
            mButtonMuteVideo.setText(getString(R.string.videocall_open_camera));
        } else {
            mTRTCCloud.startLocalPreview(mIsFrontCamera, mTXCVVLocalPreviewView);
            mButtonMuteVideo.setText(getString(R.string.videocall_close_camera));
        }
        mButtonMuteVideo.setSelected(!isSelected);
    }

    private void muteAudio() {
        boolean isSelected = mButtonMuteAudio.isSelected();
        if (!isSelected) {
            mTRTCCloud.muteLocalAudio(true);
            mButtonMuteAudio.setText(getString(R.string.videocall_mute_audio));
        } else {
            mTRTCCloud.muteLocalAudio(false);
            mButtonMuteAudio.setText(getString(R.string.videocall_close_mute_audio));
        }
        mButtonMuteAudio.setSelected(!isSelected);
    }

    private void switchCamera() {
        mIsFrontCamera = !mIsFrontCamera;
        mTXDeviceManager.switchCamera(mIsFrontCamera);
        if (mIsFrontCamera) {
            mButtonSwitchCamera.setText(getString(R.string.videocall_user_back_camera));
        } else {
            mButtonSwitchCamera.setText(getString(R.string.videocall_user_front_camera));
        }
    }

    private void audioRoute() {
        if (mAudioRouteFlag) {
            mAudioRouteFlag = false;
            mTXDeviceManager.setAudioRoute(TXDeviceManager.TXAudioRoute.TXAudioRouteEarpiece);
            mButtonAudioRoute.setText(getString(R.string.videocall_use_speaker));
        } else {
            mAudioRouteFlag = true;
            mTXDeviceManager.setAudioRoute(TXDeviceManager.TXAudioRoute.TXAudioRouteSpeakerphone);
            mButtonAudioRoute.setText(getString(R.string.videocall_use_receiver));
        }
    }

    private class TRTCCloudImplListener extends TRTCCloudListener {

        private WeakReference<VideoCallingActivity> mContext;

        public TRTCCloudImplListener(VideoCallingActivity activity) {
            super();
            mContext = new WeakReference<>(activity);
        }

        @Override
        public void onUserVideoAvailable(String userId, boolean available) {
            Log.d(TAG, "onUserVideoAvailable userId " + userId + ", mUserCount " + mUserCount + ",available " + available);
            int index = mRemoteUidList.indexOf(userId);
            if (available) {
                if (index != -1) {
                    return;
                }
                mRemoteUidList.add(userId);
                refreshRemoteVideoViews();
            } else {
                if (index == -1) {
                    return;
                }
                mTRTCCloud.stopRemoteView(userId);
                mRemoteUidList.remove(index);
                refreshRemoteVideoViews();
            }

        }

        private void refreshRemoteVideoViews() {
            for (int i = 0; i < mRemoteViewList.size(); i++) {
                if (i < mRemoteUidList.size()) {
                    String remoteUid = mRemoteUidList.get(i);
                    mRemoteViewList.get(i).setVisibility(View.VISIBLE);
                    mTRTCCloud.startRemoteView(remoteUid, TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SMALL, mRemoteViewList.get(i));
                } else {
                    mRemoteViewList.get(i).setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onError(int errCode, String errMsg, Bundle extraInfo) {
            Log.d(TAG, "sdk callback onError");
            VideoCallingActivity activity = mContext.get();
            if (activity != null) {
                Toast.makeText(activity, "onError: " + errMsg + "[" + errCode + "]", Toast.LENGTH_SHORT).show();
                if (errCode == TXLiteAVCode.ERR_ROOM_ENTER_FAIL) {
                    activity.exitRoom();
                }
            }
        }
    }

    public void requestDrawOverLays() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N && !Settings.canDrawOverlays(VideoCallingActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + VideoCallingActivity.this.getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            showFloatingView();
        }
    }

    private void showFloatingView() {
        if (mFloatingView != null && !mFloatingView.isShown()) {
            if ((null != mTRTCCloud)) {
                mFloatingView.show();
                mFloatingView.setOnPopupItemClickListener(this);
            }
        }
    }
}
