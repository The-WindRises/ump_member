package it.swiftelink.com.vcs_member.base;

import android.content.Intent;
import android.view.View;

import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.vcs_member.mqtt.MyJPushMessageReceiver;
import it.swiftelink.com.vcs_member.ui.activity.MainActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.LoginActivity;
import it.swiftelink.com.vcs_member.utils.AppShortCutUtil;


/**
 * Created by Administrator on 2018/8/21.
 */

public abstract class BasePresenterActivity<Presenter extends BaseContract.Presenter>
        extends BaseActivity<BaseContract.Presenter> implements BaseContract.View<Presenter> {

    protected Presenter mPresenter;
    protected StateView mStateView;
    protected View loadingView;

    private int type;

    private int statType = Common.NetState.STATE_CONTENT;

    @Override
    protected void initWidget() {
        super.initWidget();
        mStateView = setStateView();
        if (null != mStateView) {
            mStateView.setBackState(Common.NetState.STATE_CONTENT);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        initPresenter();
    }

    @Override
    public void showLoading() {
        if (mStateView != null) {
            statType = Common.NetState.STATE_LOADING;
            loadingView = mStateView.showLoading();
        }
    }

    @Override
    public void showRetry(final int type) {
        if (mStateView != null) {

            this.type = type;
            mStateView.setBackState(Common.NetState.STATE_RETRY);
            statType = Common.NetState.STATE_RETRY;
            loadingView = mStateView.showRetry();

            mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
                @Override
                public void onRetryClick() {
                    retry(type);
                }
            });
        }
    }

    @Override
    public void showContent() {
        if (mStateView != null) {
            mStateView.showContent();
            statType = Common.NetState.STATE_CONTENT;
            mStateView.setBackState(Common.NetState.STATE_CONTENT);
        }
    }

    @Override
    public void showEmpty() {
        if (mStateView != null) {
            mStateView.showEmpty();
            mStateView.setBackState(Common.NetState.STATE_EMPTY);
            statType = Common.NetState.STATE_EMPTY;
        }
    }


    @Override
    public void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }

    //初始化Presenter
    protected abstract Presenter initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    /**
     * 设置空布局
     */
    protected abstract StateView setStateView();

    @Override
    public void onBackPressed() {
        if (statType == Common.NetState.STATE_LOADING) {
            switch (mStateView.getBackState()) {
                case Common.NetState.STATE_CONTENT:
                   showContent();
                    break;
                case Common.NetState.STATE_EMPTY:
                    showEmpty();
                    break;
                case Common.NetState.STATE_RETRY:
                    showRetry(type);
                    break;
                default:
                    super.onBackPressed();
                    break;
            }

        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        if(errorCode==401){
            startActivity(new Intent(this,LoginActivity.class));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
            //角标清空
            MyJPushMessageReceiver.count = 0;
             JPushInterface.setBadgeNumber(this,0);
            AppShortCutUtil.setCount(MyJPushMessageReceiver.count, this);


    }
}
