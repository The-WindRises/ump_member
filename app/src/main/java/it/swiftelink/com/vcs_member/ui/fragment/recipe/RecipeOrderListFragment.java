package it.swiftelink.com.vcs_member.ui.fragment.recipe;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.recipe.RecipeOrderListResModel;
import it.swiftelink.com.factory.presenter.recipe.RecipeOrderListContract;
import it.swiftelink.com.factory.presenter.recipe.RecipeOrderListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.ui.activity.order.LogisticsActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.OrderConfirmActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeOrderListFragment extends BasePresenterFragment<RecipeOrderListContract.Presenter> implements RecipeOrderListContract.View {

    @BindView(R.id.rcv_recipe_order_list)
    RecyclerView rcvRecipeOrderList;
    @BindView(R.id.stateView)
    StateView stateView;


    private BaseRecyclerAdapter<RecipeOrderListResModel.DataBean.DataListBean> mAdapter;
    private String type;

    private int currentPage = 1;
    private int pageSize = 10;

    public RecipeOrderListFragment() {
        // Required empty public constructor
    }

    public static RecipeOrderListFragment newInstance(String type) {
        RecipeOrderListFragment myFragment = new RecipeOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recipe_order_list;
    }


    @Override
    protected RecipeOrderListContract.Presenter initPresenter() {
        return new RecipeOrderListPresenter(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        rcvRecipeOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));

        rcvRecipeOrderList.setAdapter(mAdapter = new BaseRecyclerAdapter<RecipeOrderListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_order_set_meal;
                }
            }

            @Override
            protected ViewHolder<RecipeOrderListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(RecipeOrderListResModel.DataBean.DataListBean dataListBean, ViewHolder<RecipeOrderListResModel.DataBean.DataListBean> viewHolder) {

            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvRecipeOrderList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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

        Bundle bundle = getArguments();
        type = bundle.getString("type");
        loadData(1);

    }

    private void loadData(int currentPage) {
        this.currentPage = currentPage;
        if (!TextUtils.isEmpty(type)) {
            mPresenter.getRecipeOrderList(currentPage, pageSize, type);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.PAY_FINISH:
                loadData(1);
                break;
            default:
                break;
        }
    }

    @Override
    protected StateView getStateView() {
        return stateView;
    }

    @Override
    public void getRecipeOrderListSuccess(RecipeOrderListResModel model) {

        if (currentPage == 1) {
            if (model.getData().getDataList().size() > 0) {
                mAdapter.replice(model.getData().getDataList());
                showContent();
                if ((model.getData().getDataList().size() == model.getData().getDataCount())) {
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
    public void confirmOrderReceivedSuccess() {
        loadData(1);
    }

    @Override
    public void refundOrerderSuccess() {
        loadData(1);
    }

    @Override
    public void retry(int type) {
        loadData(1);
    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode , errorMsg);

        if (type == Common.UrlApi.RECIPE_ORDER_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.RECIPE_ORDER_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<RecipeOrderListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.tv_order_goods_amount_all)
        TextView tvOrderGoodsAmountAll;
        @BindView(R.id.tv_order_price_all)
        TextView tvOrderPriceAll;
        @BindView(R.id.rcv_drug_list)
        RecyclerView rcvDrugList;
        @BindView(R.id.tv_order_to_pay)
        TextView tvOrderToPay;
        @BindView(R.id.tv_confirm_received)
        TextView tvConfirmReceived;
        @BindView(R.id.tv_look_logistics)
        TextView tvLookLogistics;
        @BindView(R.id.tv_order_cancel)
        TextView tvOrderCancel;
        @BindView(R.id.ll_order_handle)
        LinearLayout llOrderHandle;
        @BindView(R.id.tv_express_price)
        TextView tvExpressPrice;


        private BaseRecyclerAdapter<RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean> mAdapter;

        public MyViewHolder(View itemView) {
            super(itemView);
            LayoutInflater.from(getContext()).inflate(R.layout.item_rcv_order_set_meal, null);
        }

        @Override
        protected void onBind(RecipeOrderListResModel.DataBean.DataListBean dataListBean) {


            String RecipeStatus = "";
            if (!TextUtils.isEmpty(dataListBean.getStatus())) {
                switch (dataListBean.getStatus()) {

                    case "PAYMENT":
                        RecipeStatus = getString(R.string.label_to_pay);
                        llOrderHandle.setVisibility(View.VISIBLE);
                        tvOrderToPay.setVisibility(View.VISIBLE);
                        tvConfirmReceived.setVisibility(View.GONE);
                        tvLookLogistics.setVisibility(View.GONE);
                        tvOrderCancel.setVisibility(View.GONE);
                        break;

                    case "DELIVER":
                        RecipeStatus = getString(R.string.label_drop_shipping);
                        llOrderHandle.setVisibility(View.VISIBLE);
                        tvOrderToPay.setVisibility(View.GONE);
                        tvConfirmReceived.setVisibility(View.GONE);
                        tvLookLogistics.setVisibility(View.GONE);
                        tvOrderCancel.setVisibility(View.VISIBLE);
                        break;

                    case "RECEIVING":
                        RecipeStatus = getString(R.string.label_order_delivered);
                        llOrderHandle.setVisibility(View.VISIBLE);
                        tvOrderToPay.setVisibility(View.GONE);
                        tvConfirmReceived.setVisibility(View.VISIBLE);
                        tvLookLogistics.setVisibility(View.VISIBLE);
                        tvOrderCancel.setVisibility(View.GONE);
                        break;

                    case "FINISH":
                        llOrderHandle.setVisibility(View.GONE);
                        RecipeStatus = getString(R.string.label_order_has_finish);
                        break;
                    case "INREFUND":
                        llOrderHandle.setVisibility(View.GONE);
                        RecipeStatus = getString(R.string.label_in_refund);
                        break;
                    case "CANCLE":
                        llOrderHandle.setVisibility(View.GONE);
                        RecipeStatus = getString(R.string.label_has_cancel);
                        break;


                }
            }

            rcvDrugList.setLayoutManager(new LinearLayoutManager(getActivity()));


            rcvDrugList.setAdapter(mAdapter = new BaseRecyclerAdapter<RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean>() {
                @Override
                public int getViewType(int pos) {
                    return R.layout.item_rcv_drug_list;
                }

                @Override
                protected ViewHolder<RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean> createViewHolder(View root, int viewType) {
                    return new DrugViewHolder(root);
                }

                @Override
                public void onUpdate(RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean perscriptionDrugsBean, ViewHolder<RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean> viewHolder) {

                }
            });

            if (dataListBean.getPerscriptionDrugs().size() > 0) {
                mAdapter.replice(dataListBean.getPerscriptionDrugs());
            }

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            tvOrderId.setText(dataListBean.getNo());
            tvOrderState.setText(RecipeStatus);
            tvOrderTime.setText(DateTimeUtils.getDateToString(dataListBean.getCreationDate() + ""));
            tvOrderGoodsAmountAll.setText(getString(R.string.label_together) + dataListBean.getQuantity() + getString(R.string.unit));
            tvOrderPriceAll.setText(getString(R.string.msg_total)+"¥" + decimalFormat.format((dataListBean.getTotalAmount() + dataListBean.getExpressPrice())));
            tvExpressPrice.setText("¥" + decimalFormat.format(dataListBean.getExpressPrice()));


        }

        @OnClick({R.id.tv_order_to_pay, R.id.tv_confirm_received, R.id.tv_look_logistics, R.id.tv_order_cancel})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_order_to_pay:
                    OrderConfirmActivity.show(getActivity(), "", "", mData.getId(), mData.getNo());
                    break;
                case R.id.tv_confirm_received:
                    mPresenter.confirmOrderReceived(mData.getId());
                    break;
                case R.id.tv_look_logistics:
                    LogisticsActivity.show(getActivity(), mData.getId());
                    break;
                case R.id.tv_order_cancel:
                    mPresenter.refundOrder(mData.getId());
                    break;
            }
        }


        class DrugViewHolder extends BaseRecyclerAdapter.ViewHolder<RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean> {

            @BindView(R.id.tv_drug_name)
            TextView tvDrugName;
            @BindView(R.id.tv_order_goods_amount)
            TextView tvOrderGoodsAmount;
            @BindView(R.id.tv_order_goods_price)
            TextView tvOrderGoodsPrice;

            public DrugViewHolder(View itemView) {
                super(itemView);

//                LayoutInflater.from(getContext()).inflate(R.layout.item_rcv_drug_list, null);
            }

            @Override
            protected void onBind(RecipeOrderListResModel.DataBean.DataListBean.PerscriptionDrugsBean dataBean) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                tvDrugName.setText(dataBean.getName());
                tvOrderGoodsAmount.setText("x" + dataBean.getQuantity() + "");
                tvOrderGoodsPrice.setText("¥" + decimalFormat.format(dataBean.getPrice()));
            }
        }
    }
}
