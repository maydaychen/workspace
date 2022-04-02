package com.ming.workspace.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ming.workspace.Consts;
import com.ming.workspace.R;
import com.ming.workspace.base.BaseMvpActivity;
import com.ming.workspace.data.CallListBean;
import com.ming.workspace.data.WorkListBean;
import com.ming.workspace.feature.device.list.DeviceActivity;
import com.ming.workspace.feature.main.call.CallFragment;
import com.ming.workspace.feature.main.work.WorkFragment;
import com.ming.workspace.feature.video.VideoCallingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.tl_main)
    TabLayout mTlMain;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.tv_unreceive_call)
    TextView mTvCall;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CallFragment());
        fragments.add(new WorkFragment());
        HomeTitlesPagerAdapter homeTitlesPagerAdapter = new HomeTitlesPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"未接视频来电", "未完成工单"});
        mVpMain.setAdapter(homeTitlesPagerAdapter);
        mVpMain.setOffscreenPageLimit(2);
        mTlMain.setupWithViewPager(mVpMain);
        presenter.getCallList();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @OnClick({R.id.ll_main_video, R.id.ll_main_work, R.id.ll_main_device})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_video:
                String time = String.valueOf(System.currentTimeMillis());
                Intent intent = new Intent(this, VideoCallingActivity.class);
                intent.putExtra(Consts.ROOM_ID, "1256732");
                intent.putExtra(Consts.USER_ID, time.substring(time.length() - 8));
                startActivity(intent);
//                goActivity(VideoCallingActivity.class);
                break;
            case R.id.ll_main_work:
                break;
            case R.id.ll_main_device:
                goActivity(DeviceActivity.class);
                break;
            default:
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getCallListSuccess(CallListBean.DataBean callListBean) {
        mTvCall.setText(callListBean.getTotal() + "");
        mTlMain.getTabAt(0).setText(String.format(getString(R.string.main_unreceive_call_num), callListBean.getTotal() + ""));

    }

    @Override
    public void getWorkListSuccess(WorkListBean.DataBean workListBean) {

    }
}
