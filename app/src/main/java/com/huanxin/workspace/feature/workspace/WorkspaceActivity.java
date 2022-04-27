package com.huanxin.workspace.feature.workspace;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.feature.workspace.create.WorkspaceCreateActivity;
import com.huanxin.workspace.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WorkspaceActivity extends BaseMvpActivity<WorkPresenter> implements WorkContract.View {
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.sp_status)
    Spinner mSpStatus;
    @BindView(R.id.sp_source)
    Spinner mSpSource;
    @BindView(R.id.rv_workspace)
    RecyclerView mRvWorkspace;
    private List<String> data_list;
    private List<WorkspaceListBean.DataBean.ItemsBean> workList = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;
    private int pageNum = 0;
    private WorkspaceListAdapter workspaceListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //数据
        data_list = new ArrayList<>();
        data_list.add("请选择来源");
        data_list.add("指派给我的");
        data_list.add("我创建的");
        data_list.add("客户创建的");
        data_list.add("全部");
    }

    @Override
    public void initView() {
        //适配器
        arr_adapter = new ArrayAdapter<>(this, R.layout.layout_spinner_common, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.layout_spinner_drop);
        //加载适配器
        mSpStatus.setAdapter(arr_adapter);
        mSpSource.setAdapter(arr_adapter);
        mCtWorkspace.setLeftIconOnClickListener(view -> finish());

        mRvWorkspace.setLayoutManager(new LinearLayoutManager(this));
        workspaceListAdapter = new WorkspaceListAdapter(R.layout.item_main_work_list, workList);
        workspaceListAdapter.setOnItemClickListener((adapter, view, position) -> goActivity(WorkspaceCreateActivity.class));
        mRvWorkspace.setAdapter(workspaceListAdapter);
        presenter.getWorkList();
    }

    @Override
    protected WorkPresenter createPresenter() {
        return new WorkPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public String getCreateBy() {
        return null;
    }

    @Override
    public String getEngineerId() {
        return null;
    }

    @Override
    public void getWorkListSuccess(List<WorkspaceListBean.DataBean.ItemsBean> beanList) {
        workList.addAll(beanList);
        workspaceListAdapter.notifyDataSetChanged();
    }
}