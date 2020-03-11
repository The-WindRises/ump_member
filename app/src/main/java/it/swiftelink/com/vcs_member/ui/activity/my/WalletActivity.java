package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.BalanceListResModel;
import it.swiftelink.com.factory.presenter.account.BalanceContract;
import it.swiftelink.com.factory.presenter.account.BalancePresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class WalletActivity extends BasePresenterActivity<BalanceContract.Presenter> implements BalanceContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_my_wallet)
    RecyclerView rcvMyWallet;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<BalanceListResModel.DataBean.DataListBean> mAdapter;

    private int currentPage = 1;
    private int pageSize = 10;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, WalletActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_my_wallet));

        rcvMyWallet.setLayoutManager(new LinearLayoutManager(this));
        rcvMyWallet.setAdapter(mAdapter = new BaseRecyclerAdapter<BalanceListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {
                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_wallet;
                }
            }

            @Override
            protected ViewHolder<BalanceListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {

                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(BalanceListResModel.DataBean.DataListBean dataListBean, ViewHolder<BalanceListResModel.DataBean.DataListBean> viewHolder) {

            }
        });
        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvMyWallet.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setLoadState(BaseRecyclerAdapter.LOADING);
                loadData(currentPage);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

        loadData(1);

    }

    private void loadData(int currentPage) {
        this.currentPage = currentPage;
        mPresenter.getBalanceList(currentPage, pageSize);
    }

    @Override
    protected BalanceContract.Presenter initPresenter() {
        return new BalancePresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getBalanceListSuccess(BalanceListResModel model) {

        if (currentPage == 1) {
            if (model.getData().getDataList().size() > 0) {
                mAdapter.replice(model.getData().getDataList());
                showContent();
                if (model.getData().getDataList().size() == model.getData().getDataCount()) {
                    mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
                } else {
                    currentPage++;
                }

            } else {
                mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
                mAdapter.clear();
                showEmpty();
            }
        } else {
            mAdapter.addData(model.getData().getDataList());
            showContent();
            if (model.getData().getDataList() == null || model.getData().getDataList().size() == 0) {
                mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_END);
            } else {
                currentPage++;
            }
        }

    }

    @Override
    public void retry(int type) {

        loadData(1);
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);

        if (type == Common.UrlApi.GET_BALANCE_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_BALANCE_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<BalanceListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_balance_name)
        TextView tvBalanceName;
        @BindView(R.id.tv_balance_money)
        TextView tvBalanceMoney;
        @BindView(R.id.tv_balance_create_time)
        TextView tvBalanceCreateTime;
        @BindView(R.id.tv_balance_type)
        TextView tvBalanceType;

        public MyViewHolder(View itemView) {
            super(itemView);
            LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_rcv_wallet, null);
        }

        @Override
        protected void onBind(BalanceListResModel.DataBean.DataListBean dataListBean) {

            tvBalanceName.setText(dataListBean.getMethod());
            tvBalanceType.setText(dataListBean.getName());

            String type = dataListBean.getType().equals("EXPEND") ? "-" : "+";

            tvBalanceMoney.setText(type + "¥" + dataListBean.getAmount());

            tvBalanceCreateTime.setText(DateTimeUtils.getDateToString(dataListBean.getCreationDate() + ""));
        }
    }
}
