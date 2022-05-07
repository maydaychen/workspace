package com.huanxin.workspace.feature.workspace.distribute;

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
import com.huanxin.workspace.data.EngineerListBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.data.request.EngineerDistributeBean;
import com.huanxin.workspace.widget.CustomTitleBar;
import com.huanxin.workspace.widget.PointProcessBar;
import com.huanxin.workspace.widget.workspace.DeviceInfo;
import com.huanxin.workspace.widget.workspace.WorksheetInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkspaceDistributeActivity extends BaseMvpActivity<DistributePresenter> implements DistributeContract.View {
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.et_workspace_solution)
    EditText mEtSolution;
    @BindView(R.id.sp_work_engineer)
    Spinner mSpEngineer;
    @BindView(R.id.pb_worksheet)
    PointProcessBar mPbWorkspace;
    @BindView(R.id.df_workspace_distribute)
    DeviceInfo mDfWorkspace;
    @BindView(R.id.wi_workspace_distribute)
    WorksheetInfo mWiWorkspace;

    private String deviceId = "";
    List<String> titleList = new ArrayList<>();
    List<String> engineerList = new ArrayList<>();
    List<EngineerListBean.DataBean> engineerListBean = new ArrayList<>();

    private final EngineerDistributeBean engineerDistributeBean = new EngineerDistributeBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_distribute;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        titleList.add("用户报修");
        titleList.add("安排工程师");
        titleList.add("维修完成");
        engineerList.add("请选择工程师");
        engineerDistributeBean.setId(getIntent().getStringExtra("id"));
    }

    @Override
    public void initView() {
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());
        presenter.getDetail();
        presenter.getEngineerList();
    }

    @Override
    protected DistributePresenter createPresenter() {
        return new DistributePresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public EngineerDistributeBean getBean() {
        engineerDistributeBean.setTicketRecommend(mEtSolution.getText().toString());
        return engineerDistributeBean;
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
        Set<Integer> selectList = new HashSet<>();
        mWiWorkspace.setStatus("待派工");
        selectList.add(0);
        mPbWorkspace.show(titleList, selectList);
        mEtSolution.setText(dataBean.getTicketRecommend());
        presenter.getDeviceDetail();
    }

    @Override
    public void getDeviceDetailSuccess(DeviceDetailBean.DataBean dataBean) {
        mDfWorkspace.setAddress(dataBean.getDeliveryAddress());
        mDfWorkspace.setSubTitle(dataBean.getSn());
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
                    engineerDistributeBean.setEngineerId("");
                } else {
                    engineerDistributeBean.setEngineerId(beanList.get(i-1).getUserId());
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

    @Override
    public void distributeEngineerSuccess(BaseBean baseBean) {
        Toast.makeText(this, "派发成功！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.tv_workspace_distribute})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_workspace_distribute) {
            presenter.distributeEngineer();
        }
    }
}