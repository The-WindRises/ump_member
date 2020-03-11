package it.swiftelink.com.vcs_member.ui.activity.my;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.presenter.order.CouponListContract;
import it.swiftelink.com.factory.presenter.order.CouponListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.holders.SelectCouponViewHolder;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/2 18:15
 */
public class


SelectCouponActivity extends BasePresenterActivity<CouponListContract.Presenter> implements CouponListContract.View {


    @BindView(R.id.rcv_coupon_list)
    RecyclerView rcvCouponList;
    @BindView(R.id.coupon_list_sr)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.select_coupon_stateView)
    StateView stateView;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    private int currPage = 1;
    private int pageSize = 10;
    private boolean isLoadMore;
    private String orderAmount;
    private String type;
    private String languageType;
    private String selectCouponId;
    private int selectCouponPrice;

    BaseRecyclerAdapter<CouponListResModel.DataBean.CouponListBean> mAdapter;
    private List<CouponListResModel.DataBean.CouponListBean> couponListBeans;

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
    protected CouponListContract.Presenter initPresenter() {
        return new CouponListPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return mStateView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_coupon;
    }

    @Override
    public void retry(int type) {

    }

    @Override
    protected void initData() {
        super.initData();

        Log.i("lqi","订单金额"+getIntent().getStringExtra("orderAmount"));
        orderAmount=getIntent().getStringExtra("orderAmount");
            type="Not_Use";
            mPresenter.getCouponList(currPage, pageSize, orderAmount, type, languageType);

    }
    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText("选择优惠卷");
        couponListBeans = new ArrayList<>();
        languageType= App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        initRefreshLayout();
        rcvCouponList.setLayoutManager(new LinearLayoutManager(this));
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
                    SelectCouponViewHolder selectCouponViewHolder=new SelectCouponViewHolder(root,SelectCouponActivity.this);
                    selectCouponViewHolder.setUseCoupon((id, price) -> {
                        selectCouponId=id;
                        selectCouponPrice=price;
                        Intent intent=new Intent();
                        intent.putExtra("selectCouponId",selectCouponId);
                        intent.putExtra("selectCouponPrice",(double)selectCouponPrice);
                        setResult(Common.RequstCode.SELECT_COUPON,intent);
                        SelectCouponActivity.this.finish();

                    });
                return selectCouponViewHolder ;

            }


        });

    }

    //下拉刷新 初始化
    private void initRefreshLayout() {
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(this)));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
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
}
