package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.message.MessageListResModel;
import it.swiftelink.com.factory.presenter.message.MessageContract;
import it.swiftelink.com.factory.presenter.message.MessagePresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;
import it.swiftelink.com.vcs_member.utils.SpacesItemDecoration;

public class MessageActivity extends BasePresenterActivity<MessageContract.Presenter> implements MessageContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rvc_message)
    RecyclerView rvcMessage;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<MessageListResModel.DataBean.DataListBean> mAdapter;

    private int currentPage = 1;
    private int pageSize = 10;


    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, MessageActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_msg));

        rvcMessage.setLayoutManager(new LinearLayoutManager(this));
        rvcMessage.addItemDecoration(new SpacesItemDecoration(30));

        rvcMessage.setAdapter(mAdapter = new BaseRecyclerAdapter<MessageListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {
                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_my_message;
                }
            }

            @Override
            protected ViewHolder<MessageListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(MessageListResModel.DataBean.DataListBean dataListBean,
                                 ViewHolder<MessageListResModel.DataBean.DataListBean> viewHolder) {
                readMessage(dataListBean.getId());
            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rvcMessage.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setLoadState(BaseRecyclerAdapter.LOADING);
                loadData(currentPage);
            }
        });
    }

    private void readMessage(String id) {
        mPresenter.readMessage(id);
    }

    @Override
    protected void initData() {
        super.initData();
        loadData(1);

    }

    private void loadData(int currentPage) {
        this.currentPage = currentPage;

        mPresenter.getMessageList(currentPage, pageSize);
    }

    private void refreshData() {
        loadData(1);
    }

    @Override
    protected MessageContract.Presenter initPresenter() {
        return new MessagePresenter(this);
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
    public void getMessageListSuccess(MessageListResModel model) {

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
    public void readMessageSuccess(BaseResModel model) {
        showContent();
        refreshData();
    }

    @Override
    public void retry(int type) {

        loadData(1);
    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type, errorCode ,errorMsg);

        if (type == Common.UrlApi.GET_MSG_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_MSG_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<MessageListResModel.DataBean.DataListBean> {

        @BindView(R.id.titleTv)
        TextView tvMsgTitle;
        @BindView(R.id.dateTv)
        TextView tvMsgCreateTime;
        @BindView(R.id.unreadIv)
        ImageView unreadIv;
//        @BindView(R.id.tv_msg_content)
//        TextView tvMsgContent;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_my_message, null);
        }

        @Override
        protected void onBind(MessageListResModel.DataBean.DataListBean dataBean) {
            tvMsgTitle.setText(dataBean.getTitle());
            tvMsgCreateTime.setText(DateTimeUtils.getDateToString(dataBean.getCreationDate() + ""));
            unreadIv.setVisibility("Unread".equals(dataBean.getStatus()) ? View.VISIBLE : View.INVISIBLE);
//            tvMsgContent.setText(dataBean.getContent());
        }

        @OnClick(R.id.ll_item_msg_list)
        public void onViewClicked() {
            mAdapter.onUpdate(mData, this);
            MessageDetailActivity.show(MessageActivity.this, mData);

        }
    }
}
