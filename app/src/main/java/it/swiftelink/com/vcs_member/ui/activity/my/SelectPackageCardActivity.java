package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.presenter.order.SelectCardContract;
import it.swiftelink.com.factory.presenter.order.SelectCardPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.holders.PackageItemViewHolder;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/2 9:46
 */
public class SelectPackageCardActivity extends BasePresenterActivity<SelectCardContract.Presenter> implements SelectCardContract.View {
    @BindView(R.id.select_refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.select_card_rcy)
    RecyclerView mSelectCardRcy;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_right)
    ImageView btnRight;

    private int pageSize = 10;
    private boolean isLoadMore;
    private int currPage = 1;
    private String languageType = null;
    private BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean> mAdapter;
    private List<SetMealListResModel.DataBean.PackageItemInfoBean> packageItemInfoBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_card;
    }

    public static void show(Activity activity) {

        activity.startActivity(new Intent(activity, SelectPackageCardActivity.class));
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getSetMealList(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1), currPage, pageSize);
    }

    @Override
    public void getSetMealListSuccess(SetMealListResModel model) {
        if (null != model.getData() && model.isSuccess()) {
            int totalPage = model.getData().getPages();
            if (totalPage > currPage) {
                isLoadMore = true;
                mRefreshLayout.setEnableLoadMore(true);
                mRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mRefreshLayout.setNoMoreData(true);
            }
            if (currPage == 1) {
                packageItemInfoBeans.clear();
            }
            if (null != model.getData().getList() && model.getData().getList().size() > 0) {
                packageItemInfoBeans.addAll(model.getData().getList());
                if (null != packageItemInfoBeans && packageItemInfoBeans.size() > 0) {
                    showContent();
                    mAdapter.replice(packageItemInfoBeans);
                }
            }
        }
    }

    @Override
    public void postFreePickSuccess(ReceiveCardResModel resModel) {
        //免费领取成功
        if(resModel.isSuccess()){
            Toast toast=Toast.makeText(this,getResources().getText(R.string.get_success),Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            this.finish();
        }else {
            Toast toast = Toast.makeText(this, resModel.getMessage(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }    }


    @Override
    protected SelectCardContract.Presenter initPresenter() {
        return new SelectCardPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.select_card_title_str));
        btnRight.setVisibility(View.GONE);

        languageType = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        packageItemInfoBeans = new ArrayList<>();
        initRefreshLayout();
        mSelectCardRcy.setLayoutManager(layoutManager);
        mSelectCardRcy.setAdapter(mAdapter = new BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_packge_layout;

            }

            @Override
            protected ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> createViewHolder(View root, int viewType) {


                PackageItemViewHolder itemViewHolder = new PackageItemViewHolder(root, SelectPackageCardActivity.this, languageType);
                itemViewHolder.setGetPackage(new PackageItemViewHolder.GetPackage() {
                    @Override
                    public void onClickGetCard(String id) {
                        mPresenter.postFreePick(id);
                    }
                });
                return itemViewHolder;

            }

            @Override
            public void onUpdate(SetMealListResModel.DataBean.PackageItemInfoBean dataListBean, ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> viewHolder) {

            }
        });
    }

    @Override
    public void retry(int type) {

    }

    //下拉刷新 初始化
    private void initRefreshLayout() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLoadMore) {
                    currPage++;
                    mPresenter.getSetMealList(languageType, currPage, pageSize);
                }
                refreshLayout.finishLoadMore(500);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 1;
                mPresenter.getSetMealList(languageType, currPage, pageSize);
                refreshLayout.finishRefresh(800);
            }
        });

    }

}
