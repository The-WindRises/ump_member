package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.RecordListResmodel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.presenter.health.ElectronicMedicalRecordContract;
import it.swiftelink.com.factory.presenter.health.ElectronicMedicalRecordPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiryReport.InquiryReportDetailActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class ElectronicMedicalRecordActivity extends BasePresenterActivity<ElectronicMedicalRecordContract.Presenter> implements ElectronicMedicalRecordContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_birth)
    TextView tvUserBirth;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.tv_medical_record_no)
    TextView tvMedicalRecordNo;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_id_num)
    TextView tvUserIdNum;
    @BindView(R.id.tv_user_height)
    TextView tvUserHeight;
    @BindView(R.id.tv_animal_heat)
    TextView tvAnimalHeat;
    @BindView(R.id.tv_blood_pressure)
    TextView tvBloodPressure;
    @BindView(R.id.tv_head_circumference)
    TextView tvHeadCircumference;
    @BindView(R.id.tv_user_wight)
    TextView tvUserWight;
    @BindView(R.id.blood_glucose)
    TextView bloodGlucose;
    @BindView(R.id.tv_pulse)
    TextView tvPulse;
    @BindView(R.id.rcv_medical_record)
    RecyclerView rcvMedicalRecord;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.electronic_medical_sr)
    SmartRefreshLayout mSmartRefreshLayout;

    private int currPage;//当前页数
    private boolean isLoadMore; //就诊列表是否可以加载
    List<RecordListResmodel.DataBean.RecordInfoBean> recordInfoBeanList;//就诊记录List

    private BaseRecyclerAdapter<RecordListResmodel.DataBean.RecordInfoBean> mAdapter;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, ElectronicMedicalRecordActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_electronic_medical_record;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        initRefreshLayout();
        rcvMedicalRecord.setLayoutManager(new LinearLayoutManager(this));
        recordInfoBeanList = new ArrayList<>();
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        tvTitle.setText(getString(R.string.label_health_record));
        rcvMedicalRecord.setAdapter(mAdapter = new BaseRecyclerAdapter<RecordListResmodel.DataBean.RecordInfoBean>() {
            @Override
            public void onUpdate(RecordListResmodel.DataBean.RecordInfoBean recordInfoBean, ViewHolder<RecordListResmodel.DataBean.RecordInfoBean> viewHolder) {

            }

            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_medical_record;
            }

            @Override
            protected ViewHolder<RecordListResmodel.DataBean.RecordInfoBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }


        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        currPage = 1;
        mPresenter.getRecordList(currPage);
        mPresenter.getUserInfo();
    }

    @Override
    protected ElectronicMedicalRecordContract.Presenter initPresenter() {
        return new ElectronicMedicalRecordPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        showContent();
        App.showToast(errorMsg);
    }

    @OnClick({R.id.btn_back, R.id.ll_health_data, R.id.ll_emr_data1, R.id.ll_emr_data2,
            R.id.ll_emr_data3, R.id.ll_emr_data4, R.id.ll_emr_data5, R.id.ll_emr_data6, R.id.btn_to_look_history_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_health_data:
                HealthDataActivity.show(this);
                break;
            case R.id.ll_emr_data1:
                HealthHistoryListActivity.show(this, Common.CommonStr.HEALTH_HISTORY_TYPE1);
                break;
            case R.id.ll_emr_data2:
                HealthHistoryListActivity.show(this, Common.CommonStr.HEALTH_HISTORY_TYPE3);
                break;
            case R.id.ll_emr_data3:
                HealthHistoryListActivity.show(this, Common.CommonStr.HEALTH_HISTORY_TYPE2);
                break;
            case R.id.ll_emr_data4:
                HealthReportListActivity.show(this, Common.CommonStr.HEALTH_REPORT_TYPE3);
                break;
            case R.id.ll_emr_data5:
                HealthReportListActivity.show(this, Common.CommonStr.HEALTH_REPORT_TYPE1);
                break;
            case R.id.ll_emr_data6:
                HealthReportListActivity.show(this, Common.CommonStr.HEALTH_REPORT_TYPE2);
                break;
            case R.id.btn_to_look_history_data:
                HistoryHealthDataActivity.show(this);
                break;
        }
    }

    @Override
    public void getRecordListSuccess(RecordListResmodel resmodel) {

        showContent();
        //解析就诊记录返回数据
        if (resmodel.getData() != null) {
            //总共页数
            int totalPage = resmodel.getData().getTotalPages();
            if (totalPage > currPage) {
                isLoadMore = true;
                mSmartRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mSmartRefreshLayout.setNoMoreData(true);
            }
            if (currPage == 1) {
                recordInfoBeanList.clear();
            }
            recordInfoBeanList.addAll(resmodel.getData().getDataList());
            if (null != recordInfoBeanList && recordInfoBeanList.size() > 0) {
                mAdapter.replice(recordInfoBeanList);
            }

        }


    }

    @Override
    public void getUserInfoSuccess(UserInfoResModel model) {

        showContent();
        if (model.getData() != null) {
            UserInfoResModel.DataBean data = model.getData();

            App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, data.getHeadImg());
            App.getSPUtils().put(Common.SPApi.USER_NAME, data.getName());

            tvUserName.setText(TextUtils.isEmpty(data.getName()) ? "" : data.getName());
            tvUserName.setSelected(Common.CommonStr.GENDER_TYPE2.equals(data.getGender()));

            if (!TextUtils.isEmpty(data.getBirthday())) {
                String birthDay = DateTimeUtils.getDateToStringDay(data.getBirthday());
                tvUserBirth.setText(birthDay);
                String age = DateTimeUtils.getAge(birthDay, this);
                tvUserAge.setText(age);
            } else {
                tvUserBirth.setText("");
                tvUserAge.setText("");
            }

            tvMedicalRecordNo.setText(TextUtils.isEmpty(data.getMedicalRecord()) ? "" : data.getMedicalRecord());
            tvUserPhone.setText(TextUtils.isEmpty(data.getTel()) ? "" : data.getTel());
            tvUserIdNum.setText(TextUtils.isEmpty(data.getIdentityNumber()) ? "" : data.getIdentityNumber());
            if (TextUtils.isEmpty(data.getMaritalStatus())) {
                tvMaritalStatus.setText("");
            } else {
                tvMaritalStatus.setText(data.getMaritalStatus().equals("Y") ? getString(R.string.label_be_married) : getString(R.string.label_spinsterhood));
            }

            tvUserHeight.setText(TextUtils.isEmpty(data.getHeight()) ? "" : data.getHeight());
            tvUserWight.setText(TextUtils.isEmpty(data.getWeight()) ? "" : data.getWeight());


            tvBloodPressure.setText(TextUtils.isEmpty(data.getBloodPressure()) ? "" : data.getBloodPressure());

            if (Common.CommonStr.TEMPERATURE_TYPE1.equals(data.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(data.getTemperature()) ? "" : getString(R.string.label_temperature_type1) + data.getTemperature());
            }

            if (Common.CommonStr.TEMPERATURE_TYPE2.equals(data.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(data.getTemperature()) ? "" : getString(R.string.label_temperature_type2) + data.getTemperature());
            }

            if (Common.CommonStr.TEMPERATURE_TYPE3.equals(data.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(data.getTemperature()) ? "" : getString(R.string.label_temperature_type3) + data.getTemperature());
            }

            if (Common.CommonStr.BLOODSUGAR_TYPE1.equals(data.getBloodSugarType())) {
                bloodGlucose.setText(TextUtils.isEmpty(data.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type1) + data.getBloodSugar());
            }
            if (Common.CommonStr.BLOODSUGAR_TYPE2.equals(data.getBloodSugarType())) {
                bloodGlucose.setText(TextUtils.isEmpty(data.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type2) + data.getBloodSugar());
            }
            if (Common.CommonStr.BLOODSUGAR_TYPE3.equals(data.getBloodSugarType())) {
                bloodGlucose.setText(TextUtils.isEmpty(data.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type3) + data.getBloodSugar());
            }

            tvPulse.setText(TextUtils.isEmpty(data.getPulse()) ? "" : data.getPulse());
            tvHeadCircumference.setText(TextUtils.isEmpty(data.getHeadCircumference()) ? "" : data.getHeadCircumference());


        }
    }

    //下拉刷新 初始化
    private void initRefreshLayout() {
        mSmartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        BallPulseFooter ballPulseFooter = new BallPulseFooter(this);
        ballPulseFooter.setAnimatingColor(getResources().getColor(R.color.btn_color1));
        ballPulseFooter.setBackgroundColor(getResources().getColor(R.color.bgColor4));
        mSmartRefreshLayout.setRefreshFooter(ballPulseFooter);
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLoadMore) {
                    currPage++;
                    mPresenter.getRecordList(currPage);
                }
                refreshLayout.finishLoadMore(500);
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 1;
                mPresenter.getRecordList(currPage);
                mPresenter.getUserInfo();
                refreshLayout.finishRefresh(500);
            }
        });

    }


    @Override
    public void retry(int type) {

    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<RecordListResmodel.DataBean.RecordInfoBean> {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_rcv_medical_record, null);
        }

        @Override
        protected void onBind(RecordListResmodel.DataBean.RecordInfoBean recordInfoBean) {

            tvTime.setText(DateTimeUtils.getDateToString(recordInfoBean.getDiagnosisStartTime() + ""));
            tvDesc.setText(recordInfoBean.getSymptomDescription());
        }

        @OnClick()
        public void onViewClicked() {
            if (!TextUtils.isEmpty(mData.getDiagnosisReportId())) {
                InquiryReportDetailActivity.show(ElectronicMedicalRecordActivity.this, mData.getDiagnosisReportId());
            }
        }


    }
}
