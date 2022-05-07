package com.huanxin.workspace.feature.workspace.update;

import com.huanxin.workspace.data.request.WorkspaceCloseBean;
import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;



public class WorkUpdateModel implements WorkUpdateContract.Model {

    @Override
    public void closeWorkspace(WorkspaceCloseBean workspaceAddBean, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().closeWorkspace(
                callback, workspaceAddBean);
    }

    @Override
    public void getDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getWorkspaceDetail(
                callback, id);
    }

    @Override
    public void getDeviceDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getDeviceDetail(
                callback, id);
    }
}