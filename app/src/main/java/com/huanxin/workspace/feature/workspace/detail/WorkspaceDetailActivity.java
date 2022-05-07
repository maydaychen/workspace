package com.huanxin.workspace.feature.workspace.detail;

import static com.huanxin.workspace.util.DateUtils.timestampToDate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

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
    List<String> titleList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        titleList.add("用户报修");
        titleList.add("安排工程师");
        titleList.add("维修完成");
    }

    @Override
    public void initView() {
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());
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
        mDfWorkspace.setName(String.format(getContext().getString(R.string.deveice_name), dataBean.getDeviceName()));
        mDfWorkspace.setOwner(String.format(getContext().getString(R.string.deveice_owner), dataBean.getDeviceCustomer()));
        mDfWorkspace.setLogo(dataBean.getDeviceImage());
        mWiWorkspace.setName(dataBean.getReporterPhone());
        mWiWorkspace.setDesc(dataBean.getTicketContent());
        mWiWorkspace.setTime(timestampToDate(dataBean.getCreateTime()));
        mEiWorkspace.setTvName(dataBean.getEngineerName());
        mEiWorkspace.setTvTelephont(dataBean.getEngineerPhone());
        mHrWorkspace.setRemark(dataBean.getTicketContent());
        mHrWorkspace.setResult(dataBean.getTicketResultState() == null ? "" : dataBean.getTicketResultState() == 0 ? "未处理" : "已处理");
        Set<Integer> selectList = new HashSet<>();
        switch (dataBean.getTicketState()) {
            case 1:
                mWiWorkspace.setStatus("待派工");
                selectList.add(0);
                break;
            case 2:
                mWiWorkspace.setStatus("处理中");
                selectList.add(0);
                selectList.add(1);
                mEiWorkspace.setVisibility(View.VISIBLE);
                break;
            case 3:
                mWiWorkspace.setStatus("已完成");
                selectList.add(0);
                selectList.add(1);
                selectList.add(2);
                mHrWorkspace.setVisibility(View.VISIBLE);
                break;
        }
        mPbWorkspace.show(titleList, selectList);
        presenter.getDeviceDetail();
    }

    @Override
    public void getDeviceDetailSuccess(DeviceDetailBean.DataBean dataBean) {
        mDfWorkspace.setAddress(dataBean.getDeliveryAddress());
        mDfWorkspace.setSubTitle(dataBean.getSn());
    }
}