package com.huanxin.workspace.feature.workspace.create;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.EngineerListBean;
import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.feature.workspace.WorkspaceActivity;
import com.huanxin.workspace.widget.CustomTitleBar;
import com.huanxin.workspace.widget.PointProcessBar;
import com.huanxin.workspace.widget.workspace.DeviceInfo;
import com.huanxin.workspace.widget.workspace.PeopleInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkspaceCreateActivity extends BaseMvpActivity<WorkCreatePresenter> implements WorkCreateContract.View {
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.pb_worksheet)
    PointProcessBar mPbWorkspace;
    @BindView(R.id.pi_workspace_detail)
    PeopleInfo mPeopleInfo;
    @BindView(R.id.df_workspace_detail)
    DeviceInfo mDfDetail;
    @BindView(R.id.et_workspace_desc)
    EditText mEtWorkspaceDesc;
    @BindView(R.id.et_workspace_solution)
    EditText mEtWorkspaceSolution;
    @BindView(R.id.sp_work_engineer)
    Spinner mSpEngineer;

    List<String> engineerList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    Set<Integer> selectList = new HashSet<>();
    private final WorkspaceAddBean workspaceAddBean = new WorkspaceAddBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        engineerList.add("请选择工程师");
        titleList.add("用户报修");
        titleList.add("安排工程师");
        titleList.add("维修完成");
    }

    @Override
    public void initView() {
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());
        selectList.add(0);
        mPbWorkspace.show(titleList, selectList);
        mPeopleInfo.setTvName(getIntent().getStringExtra("userPhone"));
        mPeopleInfo.setTvTelephont(String.format(getContext().getString(R.string.telephone_time), getIntent().getStringExtra("createTime")));
        mPeopleInfo.setTvTitle("报修人");
        presenter.getDeviceDetail();
        presenter.getEngineerList();
    }

    @Override
    protected WorkCreatePresenter createPresenter() {
        return new WorkCreatePresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public WorkspaceAddBean getBean() {
        workspaceAddBean.setReporterId(getIntent().getStringExtra("userId"));
        workspaceAddBean.setReporterPhone(getIntent().getStringExtra("userPhone"));

        workspaceAddBean.setFaceTimeRecordId(getIntent().getStringExtra("id"));
        workspaceAddBean.setSource(2);
        workspaceAddBean.setTicketType("DEVICE_FAULT");
        workspaceAddBean.setTicketRecommend(mEtWorkspaceSolution.getText().toString());
        workspaceAddBean.setTicketContent(mEtWorkspaceDesc.getText().toString());
        return workspaceAddBean;
    }

    @Override
    public String getDeviceId() {
        return getIntent().getStringExtra("deviceId");
    }

    @Override
    public void createWorkListSuccess(BaseBean userBean) {
        Toast.makeText(this, "工单创建成功", Toast.LENGTH_SHORT).show();
        cleanGoActivity(WorkspaceActivity.class);
    }

    @Override
    public void getDeviceDetailSuccess(DeviceDetailBean.DataBean dataBean) {
        workspaceAddBean.setDeviceId(dataBean.getId());
        mDfDetail.setAddress(dataBean.getDeliveryAddress());
        mDfDetail.setLogo(dataBean.getBatteryImage());
        mDfDetail.setSubTitle(dataBean.getSn());
        mDfDetail.setName(String.format(getContext().getString(R.string.deveice_name), getIntent().getStringExtra("deviceName")));
        mDfDetail.setOwner(String.format(getContext().getString(R.string.deveice_owner), dataBean.getCustomerName()));
    }

    @Override
    public void getEngineerListSuccess(List<EngineerListBean.DataBean> beanList) {
        for (EngineerListBean.DataBean dataBean : beanList) {
            engineerList.add(dataBean.getNickName());
        }
        mSpEngineer.setSelection(0);
        mSpEngineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    workspaceAddBean.setEngineerId("");
                } else {
                    workspaceAddBean.setEngineerId(beanList.get(i - 1).getUserId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, R.layout.layout_spinner_left, engineerList);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.layout_spinner_drop);
        //加载适配器
        mSpEngineer.setAdapter(arr_adapter);
    }

    @OnClick({R.id.tv_workspace_create})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_workspace_create) {
            presenter.createWorkspace();
        }
    }
}