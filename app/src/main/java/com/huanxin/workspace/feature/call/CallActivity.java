package com.huanxin.workspace.feature.call;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.CallBean;
import com.huanxin.workspace.feature.workspace.create.WorkspaceCreateActivity;
import com.huanxin.workspace.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CallActivity extends BaseMvpActivity<CallPresenter> implements CallContract.View {
    @BindView(R.id.rv_call_list)
    RecyclerView mRvCallList;
    @BindView(R.id.ct_call)
    CustomTitleBar mCtCall;

    private int pageNum = 1;
    private CallAdapter callAdapter;
    private final List<CallBean.DataBean.ItemsBean> callList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mCtCall.setLeftIconOnClickListener(view -> finish());
        mRvCallList.setLayoutManager(new LinearLayoutManager(this));
        callAdapter = new CallAdapter(R.layout.item_main_call_list, callList);
        mRvCallList.setAdapter(callAdapter);
        callAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_useless_number:
                    callPhone(callList.get(position).getUserPhone());
                    break;
                case R.id.item_bt_create_space:
                    Intent intent = new Intent(this, WorkspaceCreateActivity.class);
                    intent.putExtra("userId", callList.get(position).getUserId());
                    intent.putExtra("userPhone", callList.get(position).getUserPhone());
                    intent.putExtra("deviceId", callList.get(position).getDeviceId());
                    intent.putExtra("deviceName", callList.get(position).getDeviceName());
                    intent.putExtra("createTime", callList.get(position).getCreateTime());
                    intent.putExtra("id", callList.get(position).getId());
                    startActivity(intent);
                    break;
            }
        });
//        callAdapter.setOnItemClickListener((adapter, view, position) -> {
//            callPhone(callList.get(position).getUserPhone());
//        });
        View empty = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        callAdapter.setEmptyView(empty);
        callAdapter.setOnLoadMoreListener(() -> {
            pageNum++;
            presenter.getCallList();
        }, mRvCallList);
        presenter.getCallList();
    }

    @Override
    protected CallPresenter createPresenter() {
        return new CallPresenter();
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
    public void getCallListSuccess(List<CallBean.DataBean.ItemsBean> itemsBeans) {
        callList.addAll(itemsBeans);
        callAdapter.notifyDataSetChanged();
        callAdapter.loadMoreComplete();
        if (itemsBeans.size() == 0) {
            callAdapter.loadMoreEnd();
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}