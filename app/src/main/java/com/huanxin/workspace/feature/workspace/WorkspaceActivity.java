package com.huanxin.workspace.feature.workspace;

import static com.huanxin.workspace.Consts.TOKEN;
import static com.huanxin.workspace.util.pageUtils.getUserId;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.feature.workspace.detail.WorkspaceDetailActivity;
import com.huanxin.workspace.feature.workspace.distribute.WorkspaceDistributeActivity;
import com.huanxin.workspace.feature.workspace.update.WorkspaceUpdateActivity;
import com.huanxin.workspace.util.SharedPreferencesUtils;
import com.huanxin.workspace.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class WorkspaceActivity extends BaseMvpActivity<WorkPresenter> implements WorkContract.View {
    @BindView(R.id.ct_workspace)
    CustomTitleBar mCtWorkspace;
    @BindView(R.id.sp_source)
    Spinner mSpSource;
    @BindView(R.id.rv_workspace)
    RecyclerView mRvWorkspace;
    private List<String> data_list;
    private List<WorkspaceListBean.DataBean.ItemsBean> workList = new ArrayList<>();
    private ArrayAdapter<String> arr_adapter;
    private int pageNum = 1;
    private String engineerId = "";
    private String createBy = "";
    private String userid = "";

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
        userid = getUserId((String) Objects.requireNonNull(SharedPreferencesUtils.getParam(this, TOKEN, "")));
    }

    @Override
    public void initView() {
        mSpSource.setSelection(0);
        mSpSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i) {
//                    case 0:
//                        engineerId = "";
//                        createBy = "";
//                        presenter.getWorkList();
//                        break;
//                    case 1:
//                        engineerId = userid;
//                        createBy = "";
//                        presenter.getWorkList();
//                        break;
//                    case 2:
//                        engineerId = "";
//                        createBy = userid;
//                        presenter.getWorkList();
//                        break;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //适配器
        arr_adapter = new ArrayAdapter<>(this, R.layout.layout_spinner_common, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.layout_spinner_drop);
        //加载适配器
        mSpSource.setAdapter(arr_adapter);

        mCtWorkspace.setLeftIconOnClickListener(view -> finish());

        mRvWorkspace.setLayoutManager(new LinearLayoutManager(this));
        workspaceListAdapter = new WorkspaceListAdapter(R.layout.item_main_work_list, workList);
        workspaceListAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (workList.get(position).getTicketState()) {
                case 1:
                    Intent intent = new Intent(this, WorkspaceDistributeActivity.class);
                    intent.putExtra("id", workList.get(position).getId());
                    startActivity(intent);
                    break;
                case 2:
                    Intent updateWork = new Intent(this, WorkspaceUpdateActivity.class);
                    updateWork.putExtra("id", workList.get(position).getId());
                    startActivity(updateWork);
                    break;
                case 3:
                    Intent workDetail = new Intent(this, WorkspaceDetailActivity.class);
                    workDetail.putExtra("id", workList.get(position).getId());
                    startActivity(workDetail);
                    break;
            }
        });
        mRvWorkspace.setAdapter(workspaceListAdapter);
//        presenter.getWorkList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        workList.clear();
        workspaceListAdapter.notifyDataSetChanged();
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