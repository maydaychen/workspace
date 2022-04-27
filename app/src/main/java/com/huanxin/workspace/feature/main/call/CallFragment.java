package com.huanxin.workspace.feature.main.call;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpFragment;
import com.huanxin.workspace.data.CallListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallFragment extends BaseMvpFragment<CallPresenter> implements CallContract.View {
    @BindView(R.id.rv_main_call)
    RecyclerView mRvMainCall;

    Unbinder unbinder;
    private int pageNum = 1;
    private CallAdapter callAdapter;
    private List<CallListBean.DataBean.RecordsBean> callList = new ArrayList<>();

    public CallFragment() {
        // Required empty public constructor
    }

    public static CallFragment newInstance(String param1, String param2) {
        return new CallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected CallPresenter createPresenter() {
        return new CallPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_call;
    }

    @Override
    protected void initView() {
        mRvMainCall.setLayoutManager(new LinearLayoutManager(getContext()));
        callAdapter = new CallAdapter(R.layout.item_main_call_list, callList);
        mRvMainCall.setAdapter(callAdapter);
        callAdapter.setOnItemClickListener((adapter, view, position) -> {
            callPhone(callList.get(position).getUserPhone());
        });
        View empty = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null, false);
        callAdapter.setEmptyView(empty);
        callAdapter.setOnLoadMoreListener(() -> {
            pageNum++;
            presenter.getCallList();
        }, mRvMainCall);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void loadData() {
        presenter.getCallList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int getPageNum() {
        return pageNum;
    }

    @Override
    public void getListSuccess(List<CallListBean.DataBean.RecordsBean> userBean) {
        callList.addAll(userBean);
        callAdapter.notifyDataSetChanged();
        callAdapter.loadMoreComplete();
        if (userBean.size() == 0) {
            callAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getListFail(CallListBean user) {

    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}