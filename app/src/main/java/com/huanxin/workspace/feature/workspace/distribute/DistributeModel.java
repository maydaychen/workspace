package com.huanxin.workspace.feature.workspace.distribute;

import com.huanxin.workspace.data.request.EngineerDistributeBean;
import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DistributeModel implements DistributeContract.Model {

    @Override
    public void getDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getWorkspaceDetail(
                callback, id);
    }

    @Override
    public void getEngineerList(ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getEngineerList(callback);
    }

    @Override
    public void getDeviceDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getDeviceDetail(
                callback, id);
    }

    @Override
    public void distributeEngineer(EngineerDistributeBean engineerDistributeBean, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().dispatchWorkspace(
                callback, engineerDistributeBean);
    }
}