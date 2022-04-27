package com.huanxin.workspace.feature.main.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpFragment;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.data.WorkListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WorkFragment extends BaseMvpFragment<WorkPresenter> implements WorkContract.View {
    @BindView(R.id.rv_main_work)
    RecyclerView mRvMainWork;

    Unbinder unbinder;
    private WorkAdapter workAdapter;
    private List<WorkListBean> workList = new ArrayList<>();

    public WorkFragment() {
    }


    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected WorkPresenter createPresenter() {
        return new WorkPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        mRvMainWork.setLayoutManager(new LinearLayoutManager(getContext()));
//        workList.add(new WorkListBean());
        workAdapter = new WorkAdapter(R.layout.item_main_work_list, workList);
        mRvMainWork.setAdapter(workAdapter);
        View empty = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        workAdapter.setEmptyView(empty);
        workAdapter.setOnLoadMoreListener(() -> {
//            page++;
            presenter.getWorkList();
        }, mRvMainWork);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getListSuccess(UserBean userBean) {

    }

    @Override
    public void getListFail(UserBean user) {

    }
}