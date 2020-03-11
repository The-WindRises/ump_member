package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.HealthReportListResModel;
import it.swiftelink.com.factory.presenter.health.HealthReportListContract;
import it.swiftelink.com.factory.presenter.health.HealthReportListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class HealthReportListActivity extends BasePresenterActivity<HealthReportListContract.Presenter> implements HealthReportListContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_health_report_list)
    RecyclerView rcvHealthReportList;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<HealthReportListResModel.DataBean> mAdapter;
    private String type;

    public static void show(Activity activity, String type) {

        Intent intent = new Intent(activity, HealthReportListActivity.class);

        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_report_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        rcvHealthReportList.setLayoutManager(new LinearLayoutManager(this));

        rcvHealthReportList.setAdapter(mAdapter = new BaseRecyclerAdapter<HealthReportListResModel.DataBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_health_report;
            }

            @Override
            protected ViewHolder<HealthReportListResModel.DataBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(HealthReportListResModel.DataBean dataBean, ViewHolder<HealthReportListResModel.DataBean> viewHolder) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("type");
            if (Common.CommonStr.HEALTH_REPORT_TYPE3.equals(type)) {
                tvTitle.setText(getText(R.string.label_examination_report));

            }

            if (Common.CommonStr.HEALTH_REPORT_TYPE1.equals(type)) {
                tvTitle.setText(getText(R.string.label_image_data));

            }
            if (Common.CommonStr.HEALTH_REPORT_TYPE2.equals(type)) {
                tvTitle.setText(getText(R.string.label_survey_report));

            }

            mPresenter.getHealthReportList(type);

        }
    }

    @Override
    protected HealthReportListContract.Presenter initPresenter() {
        return new HealthReportListPresenter(this);
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
    public void getHealthReportListSuccess(HealthReportListResModel model) {

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
    public void showError(int type, int errorCode, String errorMsg) {
        showContent();
        App.showToast(errorMsg);
    }

    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<HealthReportListResModel.DataBean> {

        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.tv_checkout_name)
        TextView tvCheckoutName;
        @BindView(R.id.tv_checkout_result)
        TextView tvCheckoutResult;
        @BindView(R.id.tv_name_tips)
        TextView tvNameTips;
        @BindView(R.id.tv_result_tips)
        TextView tvResultTips;

        public MyViewHolder(View itemView) {
            super(itemView);
            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_health_report, null);
        }

        @Override
        protected void onBind(HealthReportListResModel.DataBean dataBean) {

            tvCreateTime.setText(TextUtils.isEmpty(dataBean.getCreationDate()) ? "" : DateTimeUtils.getDateToString(dataBean.getCreationDate()));

            tvCheckoutName.setText(dataBean.getName());
            tvCheckoutResult.setText(dataBean.getAppearance());

            if (Common.CommonStr.HEALTH_REPORT_TYPE3.equals(type)) {
                tvNameTips.setText(getResources().getString(R.string.tips_examination_name));
                tvResultTips.setText(getResources().getString(R.string.tips_examination_result));

            }

            if (Common.CommonStr.HEALTH_REPORT_TYPE1.equals(type)) {
                tvNameTips.setText(getResources().getString(R.string.tips_image_name));
                tvResultTips.setText(getResources().getString(R.string.tips_image_result));

            }
            if (Common.CommonStr.HEALTH_REPORT_TYPE2.equals(type)) {
                tvNameTips.setText(getResources().getString(R.string.tips_check_name));
                tvResultTips.setText(getResources().getString(R.string.tips_check_result));
            }
        }

        @OnClick(R.id.ll_item_health_report)
        public void onViewClicked() {
            HealthReportDetailActivity.show(HealthReportListActivity.this, type, mData.getId());
        }
    }
}
