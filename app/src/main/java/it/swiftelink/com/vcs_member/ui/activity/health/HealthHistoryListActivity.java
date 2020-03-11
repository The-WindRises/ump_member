package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.HealthHistoryListResModel;
import it.swiftelink.com.factory.presenter.health.HealthHistoryListContract;
import it.swiftelink.com.factory.presenter.health.HeanthHistoryListPresenter;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class HealthHistoryListActivity extends BasePresenterActivity<HealthHistoryListContract.Presenter> implements HealthHistoryListContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_health_history_list)
    RecyclerView rcvHealthHistoryList;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<HealthHistoryListResModel.DataBean> mAdapter;
    private String type;

    public static void show(Activity activity, String type) {
        Intent intent = new Intent(activity, HealthHistoryListActivity.class);

        intent.putExtra("type", type);

        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_history_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        rcvHealthHistoryList.setLayoutManager(new LinearLayoutManager(this));

        rcvHealthHistoryList.setAdapter(mAdapter = new BaseRecyclerAdapter<HealthHistoryListResModel.DataBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_health_history_list;
            }

            @Override
            protected ViewHolder<HealthHistoryListResModel.DataBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(HealthHistoryListResModel.DataBean dataBean, ViewHolder<HealthHistoryListResModel.DataBean> viewHolder) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("type");

            if (Common.CommonStr.HEALTH_HISTORY_TYPE1.equals(type)) {
                tvTitle.setText(getString(R.string.label_allergic_history));
            }

            if (Common.CommonStr.HEALTH_HISTORY_TYPE2.equals(type)) {
                tvTitle.setText(getString(R.string.label_previous_history));
            }

            if (Common.CommonStr.HEALTH_HISTORY_TYPE3.equals(type)) {
                tvTitle.setText(getString(R.string.label_family_medical_history));
            }
            mPresenter.getHealthHistoryList(type);
        }

    }

    @Override
    protected HealthHistoryListContract.Presenter initPresenter() {
        return new HeanthHistoryListPresenter(this);
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
    public void getHealthHistoryListSuccess(HealthHistoryListResModel model) {

        if (model.getData().size() > 0) {
            showContent();
            mAdapter.replice(model.getData());
        } else {
            showEmpty();
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        App.showToast(errorMsg);
        showContent();
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<HealthHistoryListResModel.DataBean> {

        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_health_history_content)
        TextView tvHealthHistoryContent;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_health_history_list, null);
        }

        @Override
        protected void onBind(HealthHistoryListResModel.DataBean dataBean) {
            tvCreateTime.setText(DateTimeUtils.getDateToString(dataBean.getCreationDate()));
            tvHealthHistoryContent.setText(dataBean.getContent());
        }

    }
}
