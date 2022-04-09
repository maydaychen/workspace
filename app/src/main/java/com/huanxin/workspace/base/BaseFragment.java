package com.huanxin.workspace.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huanxin.workspace.R;
import com.huanxin.workspace.util.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected Dialog loadingDialog;

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 是否初始化过布局
     */
    protected boolean isViewCread;
    /**
     * 当前界面是否可见
     */
    protected boolean isUIVisble;
    /**
     * 是否加载过数据
     */
    protected boolean isLoadCompleted;
    Unbinder unbinder;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser用户是否可见
        if (isVisibleToUser && !isLoadCompleted) {
            isUIVisble = true;
            lazyLoad();
        } else {
            isUIVisble = false;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && !isLoadCompleted) {
            lazyLoad();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        isViewCread = true;
        loadingDialog = DialogUtils.createLoadingDialog(getActivity(), getString(R.string.loading));
        initView();
        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isUIVisble = !hidden;
    }

    //加载数据
    protected void lazyLoad() {
        if (isViewCread && isUIVisble) {
            loadData();
            //isViewCread=false;
            //isUIVisble=false;
            isLoadCompleted = true;
        }
    }

    protected abstract void loadData();

    public boolean onBackPressed() {
        assert getFragmentManager() != null;
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    //展示加载框
    public void showLoading(String msg) {
        if (!loadingDialog.isShowing()) {
            if (!TextUtils.isEmpty(msg)) {
                loadingDialog.setTitle(msg);
            }
            loadingDialog.show();
        }
    }

    public void dismiss() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
