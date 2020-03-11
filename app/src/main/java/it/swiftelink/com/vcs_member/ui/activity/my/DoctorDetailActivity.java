package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.presenter.doctor.DoctorInfoContract;
import it.swiftelink.com.factory.presenter.doctor.DoctorInfoPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class DoctorDetailActivity extends BasePresenterActivity<DoctorInfoContract.Presenter> implements DoctorInfoContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_doctor_header)
    ImageView ivDoctorHeader;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_post)
    TextView tvDoctorPost;
    @BindView(R.id.tv_years_of_working)
    TextView tvYearsOfWorking;
    @BindView(R.id.tv_time_video_inquiry)
    TextView tvTimeVideoInquiry;
    @BindView(R.id.tv_doctor_grade)
    TextView tvDoctorGrade;
    @BindView(R.id.rb_doctor_grade)
    AppCompatRatingBar rbDoctorGrade;
    @BindView(R.id.tv_doctor_introduce)
    TextView tvDoctorIntroduce;
    @BindView(R.id.rvc_doctor_detail)
    RecyclerView rvcDoctorDetail;
    @BindView(R.id.iv_doctor_collect)
    ImageView ivDoctorCollect;
    @BindView(R.id.tv_doctor_collect)
    TextView tvDoctorCollect;
    @BindView(R.id.stateView)
    StateView stateView;
//    @BindView(R.id.iv_doctor_level)
//    ImageView ivDoctorLevel;

    private BaseRecyclerAdapter<EvaluateListResModel.DataBean.DataListBean> mAdapter;

    private static boolean isDocotorCollect = false;
    private String doctorId;

    public static void show(Activity activity, String doctorId) {

        Intent intent = new Intent(activity, DoctorDetailActivity.class);
        intent.putExtra("doctorId", doctorId);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_doctor_detail));
        setDoctorCollectState(false);

        rvcDoctorDetail.setLayoutManager(new LinearLayoutManager(this));


        rvcDoctorDetail.setAdapter(mAdapter = new BaseRecyclerAdapter<EvaluateListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_doctor_detail;
            }

            @Override
            protected ViewHolder<EvaluateListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(EvaluateListResModel.DataBean.DataListBean dataListBean, ViewHolder<EvaluateListResModel.DataBean.DataListBean> viewHolder) {

            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();

        if (intent != null) {
            doctorId = intent.getStringExtra("doctorId");
            mPresenter.getDoctorInfo(doctorId);

            setDoctorCollectState(isDocotorCollect);
            mPresenter.getDoctorEvaluate(1, 10, Common.CommonStr.EVALUATE_TYPE2, doctorId);
        }

    }

    @Override
    protected DoctorInfoContract.Presenter initPresenter() {
        return new DoctorInfoPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.ll_doctor_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_doctor_collect:
                if (isDocotorCollect) {
                    mPresenter.cancelCollectDoctor(doctorId);
                } else {
                    mPresenter.collectDoctor(doctorId);
                }
                break;
        }
    }

    public void setDoctorCollectState(boolean isCollect) {
        isDocotorCollect = isCollect;
        ivDoctorCollect.setSelected(isCollect);
        tvDoctorCollect.setText(isCollect ? getString(R.string.cancel_collect) : getString(R.string.collect));
    }

    @Override
    public void getDoctorInfoSuccess(DoctorInfoResModel model) {

        showContent();
        if (model.getData() != null) {

            DoctorInfoResModel.DataBean data = model.getData();

            String headImgUrl=data.getHeadImg();
            if(!TextUtils.isEmpty(headImgUrl)){
                GlideLoadUtils.getInstance().glideLoadCircle(this, headImgUrl, ivDoctorHeader, R.mipmap.icon_dc_woman);
            }
            tvDoctorName.setText(TextUtils.isEmpty(data.getName()) ? "" : data.getName());
            tvDoctorGrade.setText(5 + getString(R.string.label_point));
            rbDoctorGrade.setProgress(data.getLevel());
            tvDoctorIntroduce.setText(data.getDescription());
            tvYearsOfWorking.setText(data.getPracticeYear() + getString(R.string.label_entire_period_of_actual_operation));

//            switch (data.getLevel()){
//                case 1:
//                    GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_lv1,ivDoctorLevel,R.mipmap.icon_lv5);
//                    break;
//                case 2:
//                    GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_lv2,ivDoctorLevel,R.mipmap.icon_lv5);
//                    break;
//                case 3:
//                    GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_lv3,ivDoctorLevel,R.mipmap.icon_lv5);
//                    break;
//                case 4:
//                    GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_lv4,ivDoctorLevel,R.mipmap.icon_lv5);
//                    break;
//                case 5:
//                default:
//                    GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_lv5,ivDoctorLevel,R.mipmap.icon_lv5);
//                    break;
//            }



            long onlineTime = data.getOnlineTime();

            long h = onlineTime / (60 * 60 * 1000);
            long i = (onlineTime - h * (60 * 60 * 1000)) / (60 * 1000);
            tvTimeVideoInquiry.setText(h + "h" + i + "min");

            if (Common.CommonStr.JOB_TITLE1.equals(data.getJobTitle())) {
                tvDoctorPost.setText("住院医师");
            }

            if (Common.CommonStr.JOB_TITLE2.equals(data.getJobTitle())) {
                tvDoctorPost.setText("主治医师");
            }

            if (Common.CommonStr.JOB_TITLE3.equals(data.getJobTitle())) {
                tvDoctorPost.setText("副主任医师");
            }

            if (Common.CommonStr.JOB_TITLE4.equals(data.getJobTitle())) {
                tvDoctorPost.setText("主任医师");
            }


            setDoctorCollectState(data.isFavorite());
        }

    }

    @Override
    public void collectDoctorSuccess() {

        showContent();
        setDoctorCollectState(true);
    }

    @Override
    public void cancelCollectDSuccess() {
        showContent();
        setDoctorCollectState(false);
    }

    @Override
    public void getDoctorEvaluateSuccess(EvaluateListResModel model) {
        showContent();
        if (model.getData() != null && model.getData().getDataList() != null && model.getData().getDataList().size() > 0) {
            mAdapter.replice(model.getData().getDataList());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<EvaluateListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_doctor_grade)
        TextView tvDoctorGrade;
        @BindView(R.id.rb_doctor_grade)
        AppCompatRatingBar rbDoctorGrade;
        @BindView(R.id.tv_evaluate_content)
        TextView tvEvaluateContent;
        @BindView(R.id.tv_evaluate_time)
        TextView tvEvaluateTime;

        public MyViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void onBind(EvaluateListResModel.DataBean.DataListBean dataListBean) {

            tvUserName.setText(dataListBean.getPatientName());
            tvDoctorGrade.setText(dataListBean.getPatientScore() + "分");
            rbDoctorGrade.setProgress(dataListBean.getPatientScore());
            tvEvaluateContent.setText(dataListBean.getPatientEvaluation());
            tvEvaluateTime.setText(DateTimeUtils.getDateToString(dataListBean.getPatientDate() + ""));

        }
    }
}
