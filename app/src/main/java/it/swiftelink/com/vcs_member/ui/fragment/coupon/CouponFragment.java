package it.swiftelink.com.vcs_member.ui.fragment.coupon;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.presenter.order.CouponListContract;
import it.swiftelink.com.factory.presenter.order.CouponListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.holders.CouponViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends BasePresenterFragment<CouponListContract.Presenter> implements CouponListContract.View {


    @BindView(R.id.rcv_coupon_list)
    RecyclerView rcvCouponList;
    @BindView(R.id.coupon_list_sr)
    SmartRefreshLayout mSmartRefreshLayout;

    BaseRecyclerAdapter<CouponListResModel.DataBean.CouponListBean> mAdapter;
    private List<CouponListResModel.DataBean.CouponListBean> couponListBeans;


    private int currPage = 1;
    private int pageSize = 10;
    private boolean isLoadMore;
    private String orderAmount;
    private String type;
    private String languageType;

    public CouponFragment() {

    }

    public static CouponFragment newInstance(String type, String orderAmount) {
        CouponFragment myFragment = new CouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("orderAmount", orderAmount);
        myFragment.setArguments(bundle);
        return myFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected CouponListContract.Presenter initPresenter() {
        return new CouponListPresenter(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        couponListBeans = new ArrayList<>();
        languageType=App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        initRefreshLayout();
        rcvCouponList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvCouponList.setAdapter(mAdapter = new BaseRecyclerAdapter<CouponListResModel.DataBean.CouponListBean>() {
            @Override
            public void onUpdate(CouponListResModel.DataBean.CouponListBean couponListBean, ViewHolder<CouponListResModel.DataBean.CouponListBean> viewHolder) {

            }

            @Override
            public int getViewType(int pos) {

                return R.layout.item_rcv_coupon;

            }

            @Override
            protected ViewHolder<CouponListResModel.DataBean.CouponListBean> createViewHolder(View root, int viewType) {

                return new CouponViewHolder(root,getActivity(),type,getActivity());

            }


        });


    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if(null!=bundle) {
            type = bundle.getString("type");
            orderAmount = bundle.getString("orderAmount", "");
        }
        currPage=1;
        loadData(currPage);
    }

    @Override
    protected StateView getStateView() {
        return null;
    }

    private void loadData(int currentPage) {

        if (!TextUtils.isEmpty(type)) {
            mPresenter.getCouponList(currentPage, pageSize, orderAmount, type, languageType);
        }
    }



    //下拉刷新 初始化
    private void initRefreshLayout() {
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getActivity())));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLoadMore) {
                    currPage++;
                    mPresenter.getCouponList(currPage,pageSize,orderAmount,type,languageType);
                }
                refreshLayout.finishLoadMore(500);
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 1;
                mPresenter.getCouponList(currPage,pageSize,orderAmount,type,languageType);
                refreshLayout.finishRefresh(800);
            }
        });

    }






    @Override
    public void getCouponListSuccess(CouponListResModel model) {
        if (null != model.getData() && model.isSuccess()) {
            int totalPage = model.getData().getTotalPages();
            if (totalPage > currPage) {
                isLoadMore = true;
                mSmartRefreshLayout.setEnableLoadMore(true);
                mSmartRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mSmartRefreshLayout.setNoMoreData(true);
            }
            if (currPage == 1) {
                couponListBeans.clear();
            }
            couponListBeans.addAll(model.getData().getDataList());
            if (null != couponListBeans && couponListBeans.size() > 0) {
                mAdapter.replice(couponListBeans);
            }
        }
    }

    @Override
    public void retry(int type) {
        currPage=1;
        loadData(currPage);


    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);

        showContent();
        App.showToast(errorMsg);

    }

}
