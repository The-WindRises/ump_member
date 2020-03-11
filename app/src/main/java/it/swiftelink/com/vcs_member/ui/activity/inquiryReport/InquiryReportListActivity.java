package it.swiftelink.com.vcs_member.ui.activity.inquiryReport;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.inquiry.InquiryReportListResModel;
import it.swiftelink.com.factory.presenter.inquiry.GetInquiryListReportContract;
import it.swiftelink.com.factory.presenter.inquiry.GetInquiryReportListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.recipe.RecipeDetailActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class InquiryReportListActivity extends BasePresenterActivity<GetInquiryListReportContract.Presenter> implements GetInquiryListReportContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_inquiry_report_list)
    RecyclerView rcvInquiryReportList;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<InquiryReportListResModel.DataBean.DataListBean> mAdapter;

    private int currentPage = 1;
    private int pageSize = 10;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, InquiryReportListActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry_report_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_inquiry_report));

        rcvInquiryReportList.setLayoutManager(new LinearLayoutManager(this));

        rcvInquiryReportList.setAdapter(mAdapter = new BaseRecyclerAdapter<InquiryReportListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_inquiry_report;
                }

            }

            @Override
            protected ViewHolder<InquiryReportListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {

                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(InquiryReportListResModel.DataBean.DataListBean dataListBean, ViewHolder<InquiryReportListResModel.DataBean.DataListBean> viewHolder) {

            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvInquiryReportList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        mPresenter.getInquiryReportList(pageSize, currentPage);
    }

    @Override
    protected GetInquiryListReportContract.Presenter initPresenter() {
        return new GetInquiryReportListPresenter(this);
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
    public void getInquiryReportListSuccess(InquiryReportListResModel model) {

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
    public void retry(int type) {

        loadData(1);
    }

    @Override
    public void showError(int type,int errorCode , String errorMsg) {
        super.showError(type, errorCode,errorMsg);
        if (type == Common.UrlApi.GET_INQUIRY_REPORT_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_INQUIRY_REPORT_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<InquiryReportListResModel.DataBean.DataListBean> {

        @BindView(R.id.iv_doctor_header)
        ImageView ivDoctorHeader;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_report_time)
        TextView tvReportTime;
        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_symptom_descriptions)
        TextView tvSymptomDescriptions;
        @BindView(R.id.tv_tentative_diagnosis)
        TextView tvTentativeDiagnosis;
        @BindView(R.id.tv_look_recipe)
        TextView tvLookRecipe;

        public MyViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(this).inflate(R.layout.item_rcv_inquiry_report, null);
        }

        @Override
        protected void onBind(InquiryReportListResModel.DataBean.DataListBean dataListBean) {
            InquiryReportListResModel.DataBean.DataListBean.DiagnosisBean diagnosis = dataListBean.getDiagnosis();

            tvDoctorName.setText(diagnosis.getDoctorName() == null ? "" : diagnosis.getDoctorName());
            tvReportTime.setText(DateTimeUtils.getDateToString(diagnosis.getCreationDate()));
            tvOrderId.setText(diagnosis.getNo() == null ? "" : diagnosis.getNo());
            tvUserName.setText(diagnosis.getPatientName() == null ? "" : diagnosis.getPatientName());
            tvSymptomDescriptions.setText(diagnosis.getSymptomDescription() == null ? "" : diagnosis.getSymptomDescription());
            tvTentativeDiagnosis.setText(diagnosis.getPreliminaryDiagnosis() == null ? "" : diagnosis.getPreliminaryDiagnosis());
            tvLookRecipe.setVisibility("1".equals(dataListBean.getNeedPrescription()) ? View.VISIBLE : View.GONE);
            GlideLoadUtils.getInstance().glideLoadCircle(InquiryReportListActivity.this, dataListBean.getDoctorHeadImg(), ivDoctorHeader, R.mipmap.icon_dc_man);
        }

        @OnClick({R.id.tv_look_recipe, R.id.tv_look_inquiry_report})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_look_recipe:
                    RecipeDetailActivity.show(InquiryReportListActivity.this, mData.getDiagnosis().getId());
                    break;
                case R.id.tv_look_inquiry_report:
                    InquiryReportDetailActivity.show(InquiryReportListActivity.this, mData.getId());
                    break;
            }
        }
    }
}
