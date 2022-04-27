package com.huanxin.workspace.feature.workspace.update;

import android.content.Context;
import android.os.Bundle;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.request.WorkspaceUpdateBean;

public class WorkspaceUpdateActivity extends BaseMvpActivity<WorkUpdatePresenter> implements WorkUpdateContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_workspace_create;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        presenter.getDetail();
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
    public void updateWorkListSuccess(BaseBean userBean) {

    }

    @Override
    public void getDetailSuccess(WorkspaceUpdateBean userBean) {

    }
}