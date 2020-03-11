package it.swiftelink.com.vcs_member.ui.fragment.card;

import android.os.Bundle;
import androidx.annotation.NonNull;
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
import it.swiftelink.com.factory.model.card.CardListResModel;

import it.swiftelink.com.factory.presenter.card.MyCardContract;
import it.swiftelink.com.factory.presenter.card.MyCardPresenter;

import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.holders.MyCardItemViewHolder;


/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/27 15:05
 */
public class MyCardListFragment extends BasePresenterFragment<MyCardContract.Presenter> implements MyCardContract.View {
    @BindView(R.id.card_list_rv)
    RecyclerView mCardListRecyclerView;
    @BindView(R.id.card_list_sr)
    SmartRefreshLayout mSmartRl;

    private int currPage;
    private String type;
    private int pageSize=10;
    private String validFlag;
    private boolean isLoadMore;
    private List<CardListResModel.DataBean.CardItemBean> cardList;
    private BaseRecyclerAdapter<CardListResModel.DataBean.CardItemBean> mAdapter;
    @Override
    public void getMyCardListSuccess(CardListResModel model) {
        if(null!=model &&  model.isSuccess()){
            int totalPage=model.getData().getTotal();
            if(totalPage>currPage){
                isLoadMore = true;
                mSmartRl.setEnableLoadMore(true);
                mSmartRl.setNoMoreData(false);
            }else{
                isLoadMore=false;
                mSmartRl.setNoMoreData(true);
            }
            if (currPage == 1) {
                cardList.clear();
            }
            cardList.addAll(model.getData().getList());
            if (null != cardList && cardList.size() > 0) {
                mAdapter.replice(cardList);
            }
        }


    }
    public static MyCardListFragment newInstance(String type) {
        MyCardListFragment myFragment = new MyCardListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        myFragment.setArguments(bundle);
        return myFragment;
    }
    @Override
    protected MyCardContract.Presenter initPresenter() {
        return new MyCardPresenter(this);
    }

    @Override
    protected StateView getStateView() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card_list;
    }

    @Override
    public void retry(int type) {

    }



    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if(null!=bundle) {
            type = bundle.getString("type");
            if(!TextUtils.isEmpty(type)){
                if(type.equals(Common.CommonStr.USE_MINE_CARD_TYPE)){
                    validFlag="1"; //有效
                }else if(type.equals(Common.CommonStr.LAPSE_MINE_CARD_TYPE)){
                    validFlag="0"; //失效
                }else if(type.equals(Common.CommonStr.ALL_MINE_CARD_TYPE)){
                    validFlag="";
                }
            }

        }
        currPage=1;
        loadData(currPage);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        initRefreshLayout();
        cardList=new ArrayList<>();
        mCardListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCardListRecyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<CardListResModel.DataBean.CardItemBean>() {


            @Override
            public void onUpdate(CardListResModel.DataBean.CardItemBean cardItemBean, ViewHolder<CardListResModel.DataBean.CardItemBean> viewHolder) {

            }

            @Override
            public int getViewType(int pos) {
                return R.layout.item_mine_card_layout;
            }

            @Override
            protected ViewHolder<CardListResModel.DataBean.CardItemBean> createViewHolder(View root, int viewType) {
                String language= App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                return new MyCardItemViewHolder(root,getActivity(),language);
            }
        });




    }

    private void loadData(int currentPage) {

        if (!TextUtils.isEmpty(type)) {
            //获取我的套餐卡列表
            mPresenter.getMyCardList(currentPage, pageSize, validFlag);
        }
    }


    //下拉刷新 初始化
    private void initRefreshLayout() {
        mSmartRl.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getActivity())));
        mSmartRl.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLoadMore) {
                    currPage++;
                    mPresenter.getMyCardList(currPage,pageSize,validFlag);
                }
                refreshLayout.finishLoadMore(500);
            }
        });
        mSmartRl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 1;
                mPresenter.getMyCardList(currPage,pageSize,validFlag);
                refreshLayout.finishRefresh(800);
            }
        });

    }

}
