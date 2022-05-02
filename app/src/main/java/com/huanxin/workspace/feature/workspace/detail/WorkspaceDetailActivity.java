package com.huanxin.workspace.feature.workspace.detail;

import android.content.Context;
import android.os.Bundle;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.widget.CustomTitleBar;
import com.huanxin.workspace.widget.PointProcessBar;
import com.huanxin.workspace.widget.workspace.DeviceInfo;
import com.huanxin.workspace.widget.workspace.HandleResult;
import com.huanxin.workspace.widget.workspace.PeopleInfo;
import com.huanxin.workspace.widget.workspace.WorksheetInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class WorkspaceDetailActivity extends BaseMvpActivity<DetailPresenter> implements DetailContract.View {
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.pb_worksheet)
    PointProcessBar mPbWorkspace;
    @BindView(R.id.ei_workspace_detail)
    PeopleInfo mEiWorkspace;
    @BindView(R.id.df_workspace_detail)
    DeviceInfo mDfWorkspace;
    @BindView(R.id.hr_workspace_detail)
    HandleResult mHrWorkspace;
    @BindView(R.id.wi_workspace_detail)
    WorksheetInfo mWiWorkspace;
    private String deviceId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());
        List<String> titleList = new ArrayList<>();
        Set<Integer> selectList = new HashSet<>();
        titleList.add("用户报修");
        titleList.add("安排工程师");
        titleList.add("维修完成");
        selectList.add(0);
        selectList.add(1);
        mPbWorkspace.show(titleList, selectList);
        presenter.getDetail();
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void getDetailSuccess(WorkspaceDetailBean.DataBean dataBean) {
        deviceId = dataBean.getDeviceId();
        presenter.getDeviceDetail();
    }

    @Override
    public void getDeviceDetailSuccess(DeviceDetailBean.DataBean dataBean) {
        mDfWorkspace.setAddress(dataBean.getDeliveryAddress());
        mDfWorkspace.setLogo(dataBean.getBatteryImage());
        mDfWorkspace.setSubTitle(dataBean.getSn());
        mDfWorkspace.setName(String.format(getContext().getString(R.string.deveice_name), getIntent().getStringExtra("deviceName")));
        mDfWorkspace.setOwner(String.format(getContext().getString(R.string.deveice_owner), dataBean.getCustomerName()));
    }
}