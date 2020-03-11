package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
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
import it.swiftelink.com.factory.model.doctor.CollectDoctorResModel;
import it.swiftelink.com.factory.presenter.doctor.CollectDoctorContract;
import it.swiftelink.com.factory.presenter.doctor.CollectDoctorPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class CollectDoctorActivity extends BasePresenterActivity<CollectDoctorContract.Presenter> implements CollectDoctorContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rvc_collect_doctor)
    RecyclerView rvcCollectDoctor;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<CollectDoctorResModel.DataBean.DataListBean> mAdapter;
    private int currentPage = 1;
    private int pageSize = 10;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, CollectDoctorActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_doctor;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_doctor_collect));

        rvcCollectDoctor.setLayoutManager(new LinearLayoutManager(this));
        rvcCollectDoctor.setAdapter(mAdapter = new BaseRecyclerAdapter<CollectDoctorResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {
                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_collect_doctor;
                }
            }

            @Override
            protected ViewHolder<CollectDoctorResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(CollectDoctorResModel.DataBean.DataListBean dataListBean, ViewHolder<CollectDoctorResModel.DataBean.DataListBean> viewHolder) {

            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rvcCollectDoctor.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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

        mPresenter.getCollectDoctor(currentPage, pageSize);
    }

    @Override
    protected CollectDoctorContract.Presenter initPresenter() {
        return new CollectDoctorPresenter(this);
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
    public void getCollectDoctorSuccess(CollectDoctorResModel model) {

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
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);

        if (type == Common.UrlApi.GET_COLLECT_DOCTOR_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_COLLECT_DOCTOR_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<CollectDoctorResModel.DataBean.DataListBean> {

        @BindView(R.id.iv_doctor_header)
        ImageView ivDoctorHeader;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_doctor_post)
        TextView tvDoctorPost;
        @BindView(R.id.tv_doctor_division)
        TextView tvDoctorDivision;
        @BindView(R.id.tv_doctor_hospital)
        TextView tvDoctorHospital;
        @BindView(R.id.tv_doctor_introduce)
        TextView tvDoctorIntroduce;
        @BindView(R.id.tv_doctor_grade)
        TextView tvDoctorGrade;
        @BindView(R.id.rb_doctor_grade)
        AppCompatRatingBar rbDoctorGrade;
        @BindView(R.id.doctor_practice_year_tv)
        TextView doctorYearTv;


        public MyViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(CollectDoctorActivity.this).inflate(R.layout.item_rcv_collect_doctor, null);
        }

        @Override
        protected void onBind(CollectDoctorResModel.DataBean.DataListBean dataListBean) {


            GlideLoadUtils.getInstance().glideLoadCircle(CollectDoctorActivity.this, dataListBean.getHeadImg(), ivDoctorHeader, R.mipmap.icon_dc_man);

            tvDoctorName.setText(dataListBean.getName() + "");


            if (Common.CommonStr.JOB_TITLE1.equals(dataListBean.getJobTitle())) {
                tvDoctorPost.setText(getString(R.string.label_job_title1));
            }

            if (Common.CommonStr.JOB_TITLE2.equals(dataListBean.getJobTitle())) {
                tvDoctorPost.setText(getString(R.string.label_job_title2));
            }

            if (Common.CommonStr.JOB_TITLE3.equals(dataListBean.getJobTitle())) {
                tvDoctorPost.setText(getString(R.string.label_job_titile3));
            }

            if (Common.CommonStr.JOB_TITLE4.equals(dataListBean.getJobTitle())) {
                tvDoctorPost.setText(getString(R.string.label_job_titile4));
            }

            tvDoctorDivision.setText(TextUtils.isEmpty(dataListBean.getSectionOfficeName()) ? "" : dataListBean.getSectionOfficeName());
            tvDoctorHospital.setText(TextUtils.isEmpty(dataListBean.getHospital()) ? "" : dataListBean.getHospital());
            tvDoctorIntroduce.setText(TextUtils.isEmpty(dataListBean.getDescription()) ? "" : dataListBean.getDescription());
            tvDoctorGrade.setText(5 + getString(R.string.label_point));
            doctorYearTv.setText(getResources().getString(R.string.practise)+dataListBean.getPracticeYear()+getResources().getString(R.string.year));
            rbDoctorGrade.setProgress(dataListBean.getLevel());
        }

        @OnClick()
        public void onViewClicked() {
            DoctorDetailActivity.show(CollectDoctorActivity.this, mData.getDoctorId());
        }
    }
}
