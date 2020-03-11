package it.swiftelink.com.vcs_member.ui.fragment.order;


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
import it.swiftelink.com.factory.model.order.PackageOrderListResModel;
import it.swiftelink.com.factory.presenter.order.PackageOrderListContract;
import it.swiftelink.com.factory.presenter.order.PackageOrderListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.ui.activity.appointment.PackageBuyConfirmActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetMealOrderListFragment extends BasePresenterFragment<PackageOrderListContract.Presenter> implements PackageOrderListContract.View {


    @BindView(R.id.rcv_inquiry_order_list)
    RecyclerView rcvInquiryOrderList;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<PackageOrderListResModel.DataBean.DataListBean> mAdapter;
    private String type;

    private int currentPage = 1;
    private int pageSize = 10;

    public SetMealOrderListFragment() {
        // Required empty public constructor
    }

    public static SetMealOrderListFragment newInstance(String type) {
        SetMealOrderListFragment myFragment = new SetMealOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        myFragment.setArguments(bundle);
        return myFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inquiry_order;
    }

    @Override
    protected PackageOrderListContract.Presenter initPresenter() {
        return new PackageOrderListPresenter(this);
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
    protected void initWidget(View view) {
        super.initWidget(view);

        rcvInquiryOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));

        rcvInquiryOrderList.setAdapter(mAdapter = new BaseRecyclerAdapter<PackageOrderListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_order_inquiry;
                }

            }

            @Override
            protected ViewHolder<PackageOrderListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }

            }

            @Override
            public void onUpdate(PackageOrderListResModel.DataBean.DataListBean dataListBean, ViewHolder<PackageOrderListResModel.DataBean.DataListBean> viewHolder) {

            }
        });
        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvInquiryOrderList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        if (bundle != null) {
            type = bundle.getString("type");

            loadData(1);

        }


    }

    private void loadData(int currentPage) {
        this.currentPage = currentPage;
        if (!TextUtils.isEmpty(type)) {
            mPresenter.getPackageOrderList(currentPage, pageSize, type, "PACKAGE");
        }
    }

    @Override
    protected StateView getStateView() {
        return stateView;
    }

    @Override
    public void getPackageOrderListSuccess(PackageOrderListResModel model) {

        if (currentPage == 1) {
            if (model.getData().getDataList().size() > 0) {
                mAdapter.replice(model.getData().getDataList());
                showContent();
                if ((model.getData().getDataList().size() == model.getData().getCount())) {
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
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode , errorMsg);

        if (type == Common.UrlApi.GET_PACKAGE_ORDER_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_PACKAGE_ORDER_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.PAY_FINISH:
                if (mPresenter != null && !TextUtils.isEmpty(type)) {
                    loadData(1);
                }
                break;
            default:
                break;
        }
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<PackageOrderListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.tv_order_name)
        TextView tvOrderName;
        @BindView(R.id.tv_order_price)
        TextView tvOrderPrice;
        @BindView(R.id.tv_order_create_time)
        TextView tvOrderCreateTime;
        @BindView(R.id.tv_order_price_all)
        TextView tvOrderPriceAll;
        @BindView(R.id.ll_order_handle)
        LinearLayout llOrderHandle;
        @BindView(R.id.tv_select_coupon_amount)
        TextView tvSelectCouponAmount;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getContext()).inflate(R.layout.item_rcv_order_inquiry, null);
        }

        @Override
        protected void onBind(PackageOrderListResModel.DataBean.DataListBean resModel) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            if (Common.CommonStr.SET_MEAL_TYPE1.equals(resModel.getStatus())) {
                llOrderHandle.setVisibility(View.VISIBLE);
                tvOrderState.setText(getString(R.string.label_to_pay));
            }

            if (Common.CommonStr.SET_MEAL_TYPE2.equals(resModel.getStatus())) {
                llOrderHandle.setVisibility(View.GONE);
                tvOrderState.setText(getString(R.string.label_order_has_finish));
            }

            if (Common.CommonStr.SET_MEAL_TYPE4.equals(resModel.getStatus())) {
                llOrderHandle.setVisibility(View.GONE);
                tvOrderState.setText(getString(R.string.label_has_cancel));
            }


            tvOrderId.setText(resModel.getOrderNo());

            tvOrderName.setText(resModel.getPackageName());
            tvOrderPrice.setText("¥" + decimalFormat.format(resModel.getDiscountPrice()));
            tvOrderCreateTime.setText(TextUtils.isEmpty(resModel.getCreationDate()) ? "" : DateTimeUtils.getDateToString(resModel.getCreationDate()));
            tvSelectCouponAmount.setText("¥" + decimalFormat.format(resModel.getCouponAmount()));
            tvOrderPriceAll.setText(getString(R.string.label_total_money) + decimalFormat.format((resModel.getTotalAmount() - resModel.getCouponAmount())));
        }

        @OnClick(R.id.btn_order_topay)
        public void onViewClicked() {

            PackageBuyConfirmActivity.show(getActivity(), null, mData);
        }
    }

}
