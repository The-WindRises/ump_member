package it.swiftelink.com.vcs_member.ui.fragment.inquiry;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryListResModel;
import it.swiftelink.com.factory.presenter.inquiry.GetInquiryListContract;
import it.swiftelink.com.factory.presenter.inquiry.GetInquiryListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.ui.activity.appointment.ForeseeInquiryDetailActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.EditInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiryReport.InquiryReportDetailActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.DoctorDetailActivity;
import it.swiftelink.com.vcs_member.ui.activity.recipe.RecipeDetailActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;
import it.swiftelink.com.vcs_member.weight.SpinnerPopWindow;

/**
 * A simple {@link Fragment} subclass.
 */
public class InquiryFragment extends BasePresenterFragment<GetInquiryListContract.Presenter> implements GetInquiryListContract.View {

    @BindView(R.id.month_spinner_tv)
    TextView monthSpinnerTv;
    @BindView(R.id.year_spinner_tv)
    TextView yearSpinnerTv;
    @BindView(R.id.inquiry_list_sr)
    SmartRefreshLayout mSmartRefreshLayout;
    private SpinnerPopWindow mSpinnerMonthPopWindow;
    private SpinnerPopWindow mSpinnerYearPopWindow;
    private List<String> spinnerMonthData;
    private List<String> spinnerYearDate;
    List<InquiryListResModel.DataBean.DataListBean> inquiryList;
    @BindView(R.id.rvc_inquiry)
    RecyclerView rvcInquiry;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<InquiryListResModel.DataBean.DataListBean> mAdapter;
    private String type;
    private boolean isLoadMore;
    private int currentPage = 1;
    private int pageSize = 10;


    private int curMonth = -1;
    private int curYear = -1;

    public InquiryFragment() {
        // Required empty public constructor
    }

    public static InquiryFragment newInstance(String type) {
        InquiryFragment myFragment = new InquiryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_inquiry;
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        inquiryList = new ArrayList<>();
        rvcInquiry.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvcInquiry.setAdapter(mAdapter = new BaseRecyclerAdapter<InquiryListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                return R.layout.item_rcv_inquiry;
            }

            @Override
            protected ViewHolder<InquiryListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {

                return new InquiryViewHolder(root);

            }

            @Override
            public void onUpdate(InquiryListResModel.DataBean.DataListBean dataListBean, ViewHolder<InquiryListResModel.DataBean.DataListBean> viewHolder) {

            }
        });
        initRefreshLayout();

        spinnerMonthData = new LinkedList<>(Arrays.asList(getContext().getString(R.string.tab_all_card),
                getContext().getString(R.string.january_str),
                getContext().getString(R.string.february_str),
                getContext().getString(R.string.march_str),
                getContext().getString(R.string.April_str),
                getContext().getString(R.string.may_str),
                getContext().getString(R.string.june_str),
                getContext().getString(R.string.july_str),
                getContext().getString(R.string.august_str),
                getContext().getString(R.string.september_str),
                getContext().getString(R.string.october_str),
                getContext().getString(R.string.november_str),
                getContext().getString(R.string.december_str)));
       initYears();

        mSpinnerMonthPopWindow = new SpinnerPopWindow(getActivity(), spinnerMonthData, itemMonthClickListener);
        mSpinnerMonthPopWindow.setBackgroundDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.color.white));

        mSpinnerYearPopWindow = new SpinnerPopWindow(getActivity(), spinnerYearDate, itemYearClickListener);
        mSpinnerYearPopWindow.setBackgroundDrawable(Objects.requireNonNull(getActivity()).getDrawable(R.color.white));
        monthSpinnerTv.setOnClickListener(v -> {
            mSpinnerMonthPopWindow.setWidth(monthSpinnerTv.getWidth());
            mSpinnerMonthPopWindow.setHeight(monthSpinnerTv.getHeight() * 5);
            mSpinnerMonthPopWindow.showAsDropDown(monthSpinnerTv);
        });
        yearSpinnerTv.setOnClickListener(v -> {
            mSpinnerYearPopWindow.setWidth(yearSpinnerTv.getWidth());
            mSpinnerYearPopWindow.setHeight(yearSpinnerTv.getHeight() * 5);
            mSpinnerYearPopWindow.showAsDropDown(yearSpinnerTv);
        });

    }
    private void initYears(){
        spinnerYearDate = new LinkedList<>();
        int year=Calendar.getInstance().get(Calendar.YEAR);
        spinnerYearDate.add(getContext().getString(R.string.tab_all_card));
        for(int i=0;i<5;i++){
            spinnerYearDate.add(String.valueOf(year));
            year--;
        }
    }

    private AdapterView.OnItemClickListener itemMonthClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinnerMonthPopWindow.dismiss();
            monthSpinnerTv.setText(spinnerMonthData.get(position));
            if (position == 0) {
                curMonth = -1;
            } else {
                curMonth = position;
            }
            updateList();
        }
    };

    private AdapterView.OnItemClickListener itemYearClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinnerYearPopWindow.dismiss();
            yearSpinnerTv.setText(spinnerYearDate.get(position));
            if (position == 0) {
                curYear = -1;
            } else {
                curYear = Integer.parseInt(spinnerYearDate.get(position));
            }
            updateList();
        }

    };

    //下拉刷新 初始化
    private void initRefreshLayout() {
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getActivity())));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (isLoadMore) {
                currentPage++;
                updateList();
            }
            refreshLayout.finishLoadMore(500);
        });
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            currentPage = 1;
            updateList();
            refreshLayout.finishRefresh(800);
        });

    }



    @Override
    protected void initData() {
        super.initData();

        Bundle bundle = getArguments();
        type = bundle.getString("type");
        if (!TextUtils.isEmpty(type)) {
            currentPage=1;
            loadData(1);
        }
    }


    private void loadData(int currentPage) {
        this.currentPage = currentPage;
        if (!TextUtils.isEmpty(type)) {
            mPresenter.getInquiryList(type, pageSize, currentPage);
        }
    }

    public void refreshData() {

        if (!TextUtils.isEmpty(type)) {
            loadData(1);
        }
    }


    @Override
    protected GetInquiryListContract.Presenter initPresenter() {
        return new GetInquiryListPresenter(this);
    }

    @Override
    protected StateView getStateView() {
        return stateView;
    }

    @Override
    public void getInquiryListSuccess(InquiryListResModel model) {
        showContent();
        if (null != model.getData()&& model.isSuccess()) {
            int totalPage = model.getData().getTotalPages();
            if (totalPage > currentPage) {
                isLoadMore = true;
                mSmartRefreshLayout.setEnableLoadMore(true);
                mSmartRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mSmartRefreshLayout.setNoMoreData(true);
            }
            if (currentPage == 1) {
                inquiryList.clear();
            }
            if (null != model.getData().getDataList() && model.getData().getDataList().size() > 0) {
                inquiryList.addAll(model.getData().getDataList());

            }
            mAdapter.clear();
            mAdapter.replice(inquiryList);

        }

    }

    @Override
    public void getInquiryListByTimeSuccess(InquiryListResModel model) {
        showContent();
        if (null != model.getData()&& model.isSuccess()) {
            currentPage=1;
            int totalPage = model.getData().getTotalPages();
            if (totalPage > currentPage) {
                isLoadMore = true;
                mSmartRefreshLayout.setEnableLoadMore(true);
                mSmartRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mSmartRefreshLayout.setNoMoreData(true);
            }
            if (currentPage == 1) {
                inquiryList.clear();
            }
            if (null != model.getData().getDataList() && model.getData().getDataList().size() > 0) {
                inquiryList.addAll(model.getData().getDataList());

            }
            mAdapter.clear();
            mAdapter.replice(inquiryList);

        }
    }

    @Override
    public void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel) {
        showContent();
        if(null==resModel.getData()){
            return;
        }
        if(resModel.getData().isSuccessX()){
            //当前可问诊
            App.getSPUtils().put(Common.SPApi.VIDEO_END_TYPE, Common.CommonStr.ENTER_FROM_INQUIRY);
            EditInquiryActivity.show(getActivity(), Common.CommonStr.ENTER_FROM_INQUIRY);
        }else {
            //当前不可问诊
            int type=resModel.getData().getType();
            //1:当前用户没有套餐卡
            //2：当前时间不符合套餐卡使用条件
            if(type==1) {
                ForeseeInquiryDetailActivity.show(getActivity());
            }else if(type==2){
                Toast.makeText(getActivity(),getString(R.string.error_card_tip),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateList() {
        //判断筛选条件
        if (curYear == -1 && curMonth == -1) {
            //年份选择全部
            currentPage=1;
            loadData(currentPage);

        } else {
            if(curYear!=-1){
                if(curMonth==-1){
                    mPresenter.getInquiryListByTime(type, pageSize, currentPage,String.valueOf(curYear),"");
                }else {
                    mPresenter.getInquiryListByTime(type, pageSize, currentPage,String.valueOf(curYear),String.valueOf(curMonth));
                }

            }else {
                ToastUtil.toastShortMessage("请选择年份");
            }

//            mPresenter.getInquiryListByTime(type, pageSize, currentPage,String.valueOf(curYear),String.valueOf(curMonth));
//            mPresenter.getInquiryListByTime();
            //根据年份进行筛选
//            List<InquiryListResModel.DataBean.DataListBean> tempList = new ArrayList<>();
//            if (null != inquiryList && inquiryList.size() > 0) {
//                for (InquiryListResModel.DataBean.DataListBean dataBean : inquiryList) {
//                    String time = DateTimeUtils.getDateToString(dataBean.getDiagnosisStartTime());
//                    if (!TextUtils.isEmpty(time)) {
//                        if (curYear != -1) {
//                            int year = DateTimeUtils.getCalendarByInintData(time).get(Calendar.YEAR);
//                            int month = DateTimeUtils.getCalendarByInintData(time).get(Calendar.MONTH);
//
//                            if (curYear == year) {
//                                if (curMonth != -1) {
//                                    //选择了月份
//                                    if (curMonth == month + 1) {
//                                        tempList.add(dataBean);
//                                    }
//                                } else {
//                                    tempList.add(dataBean);
//                                }
//                            }
//                        } else {
//                            ToastUtil.toastShortMessage("请选择年份");
//
//                        }
//
//                    }
//
//
//                }
//                mAdapter.clear();
//                mAdapter.replice(tempList);
//            }
        }

    }

    @Override
    public void retry(int type) {
        currentPage=1;
        loadData(currentPage);
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        if (type == Common.UrlApi.GET_INQUIRY_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_INQUIRY_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class InquiryViewHolder extends BaseRecyclerAdapter.ViewHolder<InquiryListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_order_create_time)
        TextView tvOrderCreateTime;
        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_symptoms_described)
        TextView tvSymptomsDescribed;
        @BindView(R.id.tv_preliminary_judgment)
        TextView tvPreliminaryJudgment;
        @BindView(R.id.tv_look_recipe)
        TextView tvLookRecipe;
        @BindView(R.id.tv_look_inquiry_report)
        TextView tvLookInquiryReport;
        @BindView(R.id.ll_recipe_detail)
        RelativeLayout llRecipeDetail;
        @BindView(R.id.tv_look_inquiry)
        TextView tvLookInquiry;

        public InquiryViewHolder(View itemView) {
            super(itemView);

//            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_rcv_inquiry, null);
        }

        @Override
        protected void onBind(InquiryListResModel.DataBean.DataListBean dataListBean) {

            tvOrderId.setText(TextUtils.isEmpty(dataListBean.getNo()) ? "" : dataListBean.getNo());

            if ("DRAFT".equals(dataListBean.getStatus())) {
                tvOrderState.setText(getString(R.string.tab_my_inquiry1));
                tvLookInquiry.setVisibility(View.VISIBLE);
            } else if ("FINISH".equals(dataListBean.getStatus())) {
                tvOrderState.setText(getString(R.string.tab_my_inquiry2));
                tvLookInquiry.setVisibility(View.GONE);
            } else if ("SINGULAR".equals(dataListBean.getStatus())) {
                tvOrderState.setText(getString(R.string.tab_my_inquiry4));
                tvLookInquiry.setVisibility(View.GONE);
            } else if ("UNCONNECTED".equals(dataListBean.getStatus())) {
                tvOrderState.setText(getString(R.string.label_access_failure));
                tvLookInquiry.setVisibility(View.VISIBLE);
            }
            String time = dataListBean.getDiagnosisStartTime();
            if (!TextUtils.isEmpty(time)) {
                tvOrderCreateTime.setText( DateTimeUtils.getDateToString(time));
            }
            tvUserName.setText(TextUtils.isEmpty(dataListBean.getPatientName()) ? "" : dataListBean.getPatientName());
            tvDoctorName.setText(TextUtils.isEmpty(dataListBean.getDoctorName()) ? "" : dataListBean.getDoctorName());
            tvSymptomsDescribed.setText(TextUtils.isEmpty(dataListBean.getSymptomDescription()) ? "" : dataListBean.getSymptomDescription());
            tvPreliminaryJudgment.setText(TextUtils.isEmpty(dataListBean.getPreliminaryDiagnosis()) ? "" : dataListBean.getPreliminaryDiagnosis());

            tvLookRecipe.setVisibility(TextUtils.isEmpty(dataListBean.getPrescriptionId()) ? View.GONE : View.VISIBLE);
            tvLookInquiryReport.setVisibility(TextUtils.isEmpty(dataListBean.getDisgnosisReportId()) ? View.GONE : View.VISIBLE);

        }

        @OnClick({R.id.tv_look_recipe, R.id.tv_look_inquiry_report, R.id.tv_look_inquiry, R.id.tv_doctor_name})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_look_recipe:
                    RecipeDetailActivity.show(getActivity(), mData.getId());
                    break;
                case R.id.tv_look_inquiry_report:
                    InquiryReportDetailActivity.show(getActivity(), mData.getDisgnosisReportId());
                    break;
                case R.id.tv_look_inquiry:
//                    boolean isMember = App.getSPUtils().getBoolean(Common.SPApi.IS_MEMBER);
//                    if (isMember) {
//                        App.getSPUtils().put(Common.SPApi.VIDEO_END_TYPE, Common.CommonStr.ENTER_FROM_INQUIRY);
//                        EditInquiryActivity.show(getActivity(), Common.CommonStr.ENTER_FROM_INQUIRY);
//                    } else {
//                        App.showToast(R.string.label_members_expired);
//                    }
                    mPresenter.getInquiryValidPackageCard();
                    break;
                case R.id.tv_doctor_name:
                    if (!TextUtils.isEmpty(mData.getDoctorId())) {
                        DoctorDetailActivity.show(getActivity(), mData.getDoctorId());
                    }

                    break;
            }
        }
    }
}
