package it.swiftelink.com.vcs_member.ui.activity.inquiryReport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.InquiryReport.InquiryReportResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;
import it.swiftelink.com.factory.presenter.inquiryReport.InquiryReportDetailContract;
import it.swiftelink.com.factory.presenter.inquiryReport.InquiryReportDetailPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.OrderConfirmActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class InquiryReportDetailActivity extends BasePresenterActivity<InquiryReportDetailContract.Presenter> implements InquiryReportDetailContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.tv_user_birth)
    TextView tvUserBirth;
    @BindView(R.id.tv_user_wight)
    TextView tvUserWight;
    @BindView(R.id.tv_inquiry_id)
    TextView tvInquiryId;
    @BindView(R.id.tv_inquiry_code)
    TextView tvInquiryCode;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_inquiry_time)
    TextView tvInquiryTime;
    @BindView(R.id.tv_main_suit)
    TextView tvMainSuit;
    @BindView(R.id.tv_history_of_present_illness)
    TextView tvHistoryOfPresentIllness;
    @BindView(R.id.tv_previous_history)
    TextView tvPreviousHistory;
    @BindView(R.id.tv_allergic_history)
    TextView tvAllergicHistory;
    @BindView(R.id.tv_family_history)
    TextView tvFamilyHistory;
    @BindView(R.id.tv_tentative_diagnosis)
    TextView tvTentativeDiagnosis;
    @BindView(R.id.tv_medical_advice)
    TextView tvMedicalAdvice;
    @BindView(R.id.ll_recipe_list)
    LinearLayout llRecipeList;
    @BindView(R.id.rcv_inquiry_detail)
    RecyclerView rcvInquiryDetail;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.tv_price_all)
    TextView tvPriceAll;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_recipe_status)
    TextView tvRecipeStatus;
    @BindView(R.id.tv_recipe_desc)
    TextView tvRecipeDesc;
    @BindView(R.id.tv_administrative_office)
    TextView tvAdministrativeOffice;
    @BindView(R.id.iv_check_all)
    ImageView ivCheckAll;


    private BaseRecyclerAdapter<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> mAdapter;
    private InquiryReportResModel.DataBean data;
    private RecipeInfoResModel.DataBean dataBean;
    private String inquiryReportId;
    private List<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> drugsList;


    public static void show(Activity activity, String InquiryReportId) {

        Intent intent = new Intent(activity, InquiryReportDetailActivity.class);

        intent.putExtra("InquiryReportId", InquiryReportId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry_report_detail;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_inquiry_detail));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvInquiryDetail.setLayoutManager(layoutManager);


        rcvInquiryDetail.setAdapter(mAdapter = new BaseRecyclerAdapter<RecipeInfoResModel.DataBean.PrescriptionDrugsBean>() {
            @Override
            public int getViewType(int pos) {
                if (Common.CommonStr.RECIPE_INFO_TYPE2.equals(dataBean.getVaildType())) {
                    return R.layout.item_rcv_inquiry_detail_has_pay;
                } else if (Common.CommonStr.RECIPE_INFO_TYPE3.equals(dataBean.getVaildType())) {
                    return R.layout.item_rcv_inquiry_detail_loss_effect;
                } else {
                    return R.layout.item_rcv_inquiry_detail;
                }
            }

            @Override
            protected ViewHolder<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> createViewHolder(View root, int viewType) {
                if (Common.CommonStr.RECIPE_INFO_TYPE2.equals(dataBean.getVaildType())) {
                    return new MyViewHolder1(root);
                } else if (Common.CommonStr.RECIPE_INFO_TYPE3.equals(dataBean.getVaildType())) {
                    return new MyViewHolder2(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(RecipeInfoResModel.DataBean.PrescriptionDrugsBean prescriptionDrugsBean, ViewHolder<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> viewHolder) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();

        if (intent != null) {
            inquiryReportId = intent.getStringExtra("InquiryReportId");
            if (!TextUtils.isEmpty(inquiryReportId)) {
                mPresenter.getInquiryInfo(inquiryReportId);
            }
        }
    }

    @Override
    protected InquiryReportDetailContract.Presenter initPresenter() {
        return new InquiryReportDetailPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.ll_check_all, R.id.tv_inquiry_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_check_all:
                checkAll();
                break;
            case R.id.tv_inquiry_submit:
                submitInquiry();
                break;
        }
    }

    private void submitInquiry() {

        List<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> items = mAdapter.getItems();

        StringBuffer stringBuffer = new StringBuffer();
        for (RecipeInfoResModel.DataBean.PrescriptionDrugsBean item : items) {
            stringBuffer.append(item.getId() + ",");
        }

        String prescriptionDrugIds = stringBuffer.toString();

        OrderConfirmActivity.show(this, data.getId(), prescriptionDrugIds, "", "");


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.PAY_FINISH:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void getInquiryInfoSuccess(InquiryReportResModel model) {
        showContent();
        data = model.getData();
        if (data != null) {
            InquiryReportResModel.DataBean.DiagnosisBean diagnosisBean = data.getDiagnosis();

            tvUserName.setText(TextUtils.isEmpty(diagnosisBean.getPatientName()) ? "" : diagnosisBean.getPatientName());
            tvUserName.setSelected(Common.CommonStr.GENDER_TYPE2.equals(diagnosisBean.getGender()));
            tvUserWight.setText(TextUtils.isEmpty(data.getWeight()) ? "" : data.getWeight());
            if (!TextUtils.isEmpty(diagnosisBean.getMaritalStatus())) {
                tvMaritalStatus.setText("Y".equals(diagnosisBean.getMaritalStatus()) ? getString(R.string.label_be_married) : getString(R.string.label_spinsterhood));
            } else {
                tvMaritalStatus.setText("");
            }

            if (!TextUtils.isEmpty(diagnosisBean.getBirthday())) {
                String birthDay = DateTimeUtils.getDateToStringDay(diagnosisBean.getBirthday() + "");
                tvUserBirth.setText(birthDay);
                String age = DateTimeUtils.getAge(birthDay, this);
                tvUserAge.setText(age);
            } else {
                tvUserBirth.setText("");
                tvUserAge.setText("");
            }

            tvInquiryId.setText(TextUtils.isEmpty(diagnosisBean.getNo()) ? "" : diagnosisBean.getNo());
            tvInquiryCode.setText(TextUtils.isEmpty(diagnosisBean.getMedicalRecord()) ? "" : diagnosisBean.getMedicalRecord());
            tvInquiryTime.setText(DateTimeUtils.getDateToString(diagnosisBean.getDiagnosisStartTime() + ""));
            tvDoctorName.setText(TextUtils.isEmpty(diagnosisBean.getDoctorName()) ? "" : diagnosisBean.getDoctorName());
            tvAdministrativeOffice.setText(TextUtils.isEmpty(diagnosisBean.getSectionOfficeName()) ? "" : diagnosisBean.getSectionOfficeName());

            tvMainSuit.setText(TextUtils.isEmpty(data.getMainAppeal()) ? "" : data.getMainAppeal());
            tvHistoryOfPresentIllness.setText(TextUtils.isEmpty(data.getCurrentMedicalHistory()) ? "" : data.getCurrentMedicalHistory());

            tvAllergicHistory.setText(TextUtils.isEmpty(data.getAllergies()) ? "" : data.getAllergies());
            tvPreviousHistory.setText(TextUtils.isEmpty(data.getPastHistory()) ? "" : data.getPastHistory());
            tvFamilyHistory.setText(TextUtils.isEmpty(data.getFamilyHistory()) ? "" : data.getFamilyHistory());

            tvTentativeDiagnosis.setText(TextUtils.isEmpty(diagnosisBean.getPreliminaryDiagnosis()) ? "" : diagnosisBean.getPreliminaryDiagnosis());
            tvMedicalAdvice.setText(TextUtils.isEmpty(data.getDiagnosisSuggest()) ? "" : data.getDiagnosisSuggest());


            if ("1".equals(data.getNeedPrescription())) {
                String id = data.getDiagnosis().getId();
                mPresenter.getRecipeInfo(id);
            } else {
                rcvInquiryDetail.setVisibility(View.GONE);
                llPay.setVisibility(View.GONE);
            }

        } else {
            App.showToast(R.string.label_data_error);
        }

    }

    @Override
    public void getRecipeInfoSuccess(RecipeInfoResModel model) {
        showContent();
        if (model.getData() != null && model.getData().getPrescriptionDrugs().size() > 0) {

            drugsList = model.getData().getPrescriptionDrugs();
            llRecipeList.setVisibility(View.VISIBLE);

            dataBean = model.getData();

            tvRecipeDesc.setText(dataBean.getDescription());

            mAdapter.replice(model.getData().getPrescriptionDrugs());

            if (Common.CommonStr.RECIPE_INFO_TYPE1.equals(dataBean.getVaildType())) {
                tvRecipeStatus.setText(getString(R.string.notice_period_of_validity) + dataBean.getValidity() + getString(R.string.label_day));
                tvRecipeStatus.setTextColor(getResources().getColor(R.color.textColor2));
                llPay.setVisibility(View.VISIBLE);
            } else if (Common.CommonStr.RECIPE_INFO_TYPE2.equals(dataBean.getVaildType())) {
                tvRecipeStatus.setText(getString(R.string.label_has_submit_order));
                tvRecipeStatus.setTextColor(getResources().getColor(R.color.textColor2));
                llPay.setVisibility(View.GONE);
            } else {
                tvRecipeStatus.setText(getString(R.string.notice_has_loss_effect));
                tvRecipeStatus.setTextColor(getResources().getColor(R.color.textColor6));
                llPay.setVisibility(View.GONE);
            }
        } else {
            Log.i("lqi","-------------------");
            llRecipeList.setVisibility(View.GONE);
            llPay.setVisibility(View.GONE);
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

    List<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> checkDataList;
    private boolean isCheckedAll = false;

    private void notifyPriceAll() {

        if (checkDataList == null) {
            checkDataList = new ArrayList<>();
        } else {
            checkDataList.clear();
        }

        double priceAll = 0;
        List<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> items = mAdapter.getItems();

        for (RecipeInfoResModel.DataBean.PrescriptionDrugsBean item : items) {
            if (item.isCheck()) {
                priceAll += item.getPrice() * item.getQuantity();
                checkDataList.add(item);
            }
        }
        if (checkDataList.size() == items.size()) {
            isCheckedAll = true;
            ivCheckAll.setSelected(true);
        } else {
            isCheckedAll = false;
            ivCheckAll.setSelected(false);
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        tvPriceAll.setText("¥" + decimalFormat.format(priceAll));
    }

    private void checkAll() {

        for (RecipeInfoResModel.DataBean.PrescriptionDrugsBean item : drugsList) {
            item.setCheck(!isCheckedAll);
        }
        mAdapter.replice(drugsList);
        isCheckedAll = !isCheckedAll;
        ivCheckAll.setSelected(isCheckedAll);
        notifyPriceAll();
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> {

        @BindView(R.id.iv_select_drug)
        ImageView ivSelectDrug;
        @BindView(R.id.tv_drug_name)
        TextView tvDrugName;
        @BindView(R.id.tv_price_single)
        TextView tvPriceSingle;
        @BindView(R.id.tv_drug_spec)
        TextView tvDrugSpec;
        @BindView(R.id.tv_drug_num)
        TextView tvDrugNum;
        @BindView(R.id.tv_drug_num_all)
        TextView tvDrugNumAll;
        @BindView(R.id.tv_drug_price_all)
        TextView tvDrugPriceAll;
        @BindView(R.id.tv_usage)
        TextView tvUsage;
        @BindView(R.id.tv_dosage)
        TextView tvDosage;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_frequency)
        TextView tvFrequency;
        @BindView(R.id.tv_unit)
        TextView tvUnit;


        public MyViewHolder(View itemView) {
            super(itemView);
//            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_inquiry_detail, null);
        }

        @Override
        protected void onBind(RecipeInfoResModel.DataBean.PrescriptionDrugsBean dataBean) {

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            ivSelectDrug.setSelected(dataBean.isCheck());
            tvDrugName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
            tvPriceSingle.setText("¥ " + decimalFormat.format(dataBean.getPrice()));
            tvDrugNum.setText("X" + dataBean.getQuantity());
            tvDrugNumAll.setText(getString(R.string.label_together) + dataBean.getQuantity() + dataBean.getUnit());
            tvDrugPriceAll.setText(getResources().getString(R.string.label_total_price) + " ¥ " + decimalFormat.format(dataBean.getQuantity() * dataBean.getPrice()));
            tvUsage.setText(TextUtils.isEmpty(dataBean.getUsageMethod()) ? "" : dataBean.getUsageMethod());
            tvDuration.setText(dataBean.getDaysTaken() + getString(R.string.label_day));
            tvFrequency.setText(TextUtils.isEmpty(dataBean.getFrequency()) ? "" : dataBean.getFrequency());


            tvDosage.setText(dataBean.getOnceMetering() + "");
            tvUnit.setText(TextUtils.isEmpty(dataBean.getOnceUnit()) ? "" : dataBean.getOnceUnit());
            tvDrugSpec.setText(TextUtils.isEmpty(dataBean.getSpecification()) ? "" : dataBean.getSpecification());
        }

        @OnClick()
        public void onViewClicked() {
            mData.setCheck(!mData.isCheck());
            mAdapter.notifyItemChanged(getAdapterPosition());
            notifyPriceAll();
        }
    }

    class MyViewHolder1 extends BaseRecyclerAdapter.ViewHolder<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> {

        @BindView(R.id.tv_drug_name)
        TextView tvDrugName;
        @BindView(R.id.tv_price_single)
        TextView tvPriceSingle;
        @BindView(R.id.tv_drug_spec)
        TextView tvDrugSpec;
        @BindView(R.id.tv_drug_num)
        TextView tvDrugNum;
        @BindView(R.id.tv_drug_num_all)
        TextView tvDrugNumAll;
        @BindView(R.id.tv_drug_price_all)
        TextView tvDrugPriceAll;
        @BindView(R.id.tv_usage)
        TextView tvUsage;
        @BindView(R.id.tv_dosage)
        TextView tvDosage;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_frequency)
        TextView tvFrequency;
        @BindView(R.id.tv_unit)
        TextView tvUnit;

        public MyViewHolder1(View itemView) {
            super(itemView);

            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_inquiry_detail, null);
        }

        @Override
        protected void onBind(RecipeInfoResModel.DataBean.PrescriptionDrugsBean dataBean) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvDrugName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
            tvPriceSingle.setText("¥ " + decimalFormat.format(dataBean.getPrice()));
            tvDrugNum.setText("X" + dataBean.getQuantity());
            tvDrugPriceAll.setText(getResources().getString(R.string.label_total_price) + " ¥ " + decimalFormat.format(dataBean.getQuantity() * dataBean.getPrice()));
            tvDrugNumAll.setText(getString(R.string.label_together) + dataBean.getQuantity() + getString(R.string.piece));
            tvUsage.setText(TextUtils.isEmpty(dataBean.getUsageMethod()) ? "" : dataBean.getUsageMethod());
            tvDuration.setText(dataBean.getDaysTaken() + getString(R.string.label_day));
            tvFrequency.setText(TextUtils.isEmpty(dataBean.getFrequency()) ? "" : dataBean.getFrequency());

            tvDosage.setText(dataBean.getOnceMetering() + "");
            tvUnit.setText(TextUtils.isEmpty(dataBean.getOnceUnit()) ? "" : dataBean.getOnceUnit());
            tvDrugSpec.setText(TextUtils.isEmpty(dataBean.getSpecification()) ? "" : dataBean.getSpecification());
        }

    }

    class MyViewHolder2 extends BaseRecyclerAdapter.ViewHolder<RecipeInfoResModel.DataBean.PrescriptionDrugsBean> {

        @BindView(R.id.tv_drug_name)
        TextView tvDrugName;
        @BindView(R.id.tv_price_single)
        TextView tvPriceSingle;
        @BindView(R.id.tv_drug_spec)
        TextView tvDrugSpec;
        @BindView(R.id.tv_drug_num)
        TextView tvDrugNum;
        @BindView(R.id.tv_drug_price_all)
        TextView tvDrugPriceAll;
        @BindView(R.id.tv_usage)
        TextView tvUsage;
        @BindView(R.id.tv_dosage)
        TextView tvDosage;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_frequency)
        TextView tvFrequency;
        @BindView(R.id.tv_unit)
        TextView tvUnit;


        public MyViewHolder2(View itemView) {
            super(itemView);

            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_inquiry_detail, null);
        }

        @Override
        protected void onBind(RecipeInfoResModel.DataBean.PrescriptionDrugsBean dataBean) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvDrugName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
            tvPriceSingle.setText("¥ " + decimalFormat.format(dataBean.getPrice()));
            tvDrugNum.setText("X" + dataBean.getQuantity());
            tvDrugPriceAll.setText(getResources().getString(R.string.label_total_price) +"¥ " + decimalFormat.format(dataBean.getQuantity() * dataBean.getPrice()));
            tvUsage.setText(TextUtils.isEmpty(dataBean.getUsageMethod()) ? "" : dataBean.getUsageMethod());
            tvDuration.setText(dataBean.getDaysTaken() + getString(R.string.label_day));
            tvFrequency.setText(TextUtils.isEmpty(dataBean.getFrequency()) ? "" : dataBean.getFrequency());

            tvDosage.setText(dataBean.getOnceMetering() + "");
            tvUnit.setText(TextUtils.isEmpty(dataBean.getOnceUnit()) ? "" : dataBean.getOnceUnit());
            tvDrugSpec.setText(TextUtils.isEmpty(dataBean.getSpecification()) ? "" : dataBean.getSpecification());
        }

    }
}
