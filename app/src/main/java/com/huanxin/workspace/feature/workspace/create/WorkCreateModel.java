package com.huanxin.workspace.feature.workspace.create;

import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class WorkCreateModel implements WorkCreateContract.Model {
    @Override
    public void createWorkspace(WorkspaceAddBean workspaceAddBean, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().createWorkspace(callback, workspaceAddBean);
    }

    @Override
    public void getDeviceDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getDeviceDetail(callback, id);
    }
}