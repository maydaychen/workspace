package com.huanxin.workspace.feature.workspace.update;

import static com.huanxin.workspace.util.DateUtils.timestampToDate;

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
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.data.request.WorkspaceCloseBean;
import com.huanxin.workspace.widget.CustomTitleBar;
import com.huanxin.workspace.widget.PointProcessBar;
import com.huanxin.workspace.widget.workspace.DeviceInfo;
import com.huanxin.workspace.widget.workspace.PeopleInfo;
import com.huanxin.workspace.widget.workspace.WorksheetInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkspaceUpdateActivity extends BaseMvpActivity<WorkUpdatePresenter> implements WorkUpdateContract.View {
    @BindView(R.id.pb_worksheet)
    PointProcessBar mPbWorkspace;
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.ei_workspace_update)
    PeopleInfo mEiWorkspace;
    @BindView(R.id.df_workspace_update)
    DeviceInfo mDfWorkspace;
    @BindView(R.id.wi_workspace_update)
    WorksheetInfo mWiWorkspace;
    @BindView(R.id.et_work_remark)
    EditText mEtRemark;
    @BindView(R.id.sp_work_finished)
    Spinner mSpFinished;

    private String deviceId = "";
    private WorkspaceCloseBean workspaceCloseBean = new WorkspaceCloseBean();
    List<String> titleList = new ArrayList<>();
    List<String> finishList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_update;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        titleList.add("用户报修");
        titleList.add("安排工程师");
        titleList.add("维修完成");
        Set<Integer> selectList = new HashSet<>();
        selectList.add(0);
        selectList.add(1);
        mPbWorkspace.show(titleList, selectList);
        finishList.add("请选择");
        finishList.add("未处理");
        finishList.add("已处理");
    }

    @Override
    public void initView() {
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());
        presenter.getDetail();
        mSpFinished.setSelection(0);
        mSpFinished.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    workspaceCloseBean.setTicketResultState(null);
                } else {
                    workspaceCloseBean.setTicketResultState(i - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<>(this, R.layout.layout_spinner_left, finishList);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.layout_spinner_drop);
        //加载适配器
        mSpFinished.setAdapter(arr_adapter);
    }

    @Override
    protected WorkUpdatePresenter createPresenter() {
        return new WorkUpdatePresenter();
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
    public WorkspaceCloseBean getBean() {
        workspaceCloseBean.setTicketResultContent(mEtRemark.getText().toString());
        workspaceCloseBean.setId(getId());
        return workspaceCloseBean;
    }

    @Override
    public void closeWorkSuccess(BaseBean userBean) {
        Toast.makeText(this, "处理成功！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void getDetailSuccess(WorkspaceDetailBean.DataBean dataBean) {
        deviceId = dataBean.getDeviceId();
        mDfWorkspace.setName(String.format(getContext().getString(R.string.deveice_name), dataBean.getDeviceName()));
        mDfWorkspace.setOwner(String.format(getContext().getString(R.string.deveice_owner), dataBean.getDeviceCustomer()));
        mDfWorkspace.setLogo(dataBean.getDeviceImage());
        mEiWorkspace.setTvName(dataBean.getEngineerName());
        mEiWorkspace.setTvTelephont(dataBean.getEngineerPhone());
        mWiWorkspace.setDesc(dataBean.getTicketContent());
        mWiWorkspace.setName(dataBean.getReporterPhone());
        mWiWorkspace.setTime(timestampToDate(dataBean.getCreateTime()));
        presenter.getDeviceDetail();
    }

    @Override
    public void getDeviceDetailSuccess(DeviceDetailBean.DataBean dataBean) {
        mDfWorkspace.setAddress(dataBean.getDeliveryAddress());
        mDfWorkspace.setSubTitle(dataBean.getSn());
    }

    @OnClick({R.id.tv_work_update})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_work_update) {
            presenter.closeWorkspace();
        }
    }
}