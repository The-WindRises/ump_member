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
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;
import it.swiftelink.com.factory.presenter.health.HealthDataHistoryContract;
import it.swiftelink.com.factory.presenter.health.HealthDataHistoryPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class HistoryHealthDataActivity extends BasePresenterActivity<HealthDataHistoryContract.Presenter> implements HealthDataHistoryContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_history_health_data)
    RecyclerView rcvHistoryHealthData;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<HistoryHealthDataListResModel.DataBean.DataListBean> mAdapter;
    private int currentPage = 1;
    private int pageSize = 10;

    public static void show(Activity activity) {

        activity.startActivity(new Intent(activity, HistoryHealthDataActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_health_data;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_history_health_data));
        rcvHistoryHealthData.setLayoutManager(new LinearLayoutManager(this));

        rcvHistoryHealthData.setAdapter(mAdapter = new BaseRecyclerAdapter<HistoryHealthDataListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_history_health_data_list;
                }

            }

            @Override
            protected ViewHolder<HistoryHealthDataListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(HistoryHealthDataListResModel.DataBean.DataListBean dataBean, ViewHolder<HistoryHealthDataListResModel.DataBean.DataListBean> viewHolder) {

            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvHistoryHealthData.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        mPresenter.getHealthData(currentPage, pageSize);
    }

    @Override
    protected HealthDataHistoryContract.Presenter initPresenter() {
        return new HealthDataHistoryPresenter(this);
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
    public void getHealthDataSuccess(HistoryHealthDataListResModel resModel) {

        if (currentPage == 1) {
            if (resModel.getData().getDataList().size() > 0) {
                mAdapter.replice(resModel.getData().getDataList());
                showContent();
                if ((resModel.getData().getDataList().size() == resModel.getData().getDataCount())) {
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
            mAdapter.addData(resModel.getData().getDataList());
            showContent();
            if (resModel.getData().getDataList() == null || resModel.getData().getDataList().size() == 0) {
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
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode, errorMsg);
        if (type == Common.UrlApi.GET_HEALTH_HISTORY_DATA && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_HEALTH_HISTORY_DATA && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }

    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<HistoryHealthDataListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
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
        @BindView(R.id.tv_blood_glucose)
        TextView tvBloodGlucose;
        @BindView(R.id.tv_user_pulse)
        TextView tvUserPulse;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_history_health_data_list, null);
        }

        @Override
        protected void onBind(HistoryHealthDataListResModel.DataBean.DataListBean dataBean) {
            tvCreateTime.setText(DateTimeUtils.getDateToString(dataBean.getCreationDate() + ""));
            tvUserHeight.setText(dataBean.getHeight());

            tvHeadCircumference.setText(dataBean.getHeadCircumference());
            tvUserWight.setText(dataBean.getWeight());
            tvUserPulse.setText(dataBean.getPulse());

            tvBloodPressure.setText(TextUtils.isEmpty(dataBean.getBloodPressure()) ? "" : dataBean.getBloodPressure());

            if (Common.CommonStr.TEMPERATURE_TYPE1.equals(dataBean.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(dataBean.getTemperature()) ? "" : getString(R.string.label_temperature_type1)+dataBean.getTemperature());
            }

            if (Common.CommonStr.TEMPERATURE_TYPE2.equals(dataBean.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(dataBean.getTemperature()) ? "" : getString(R.string.label_temperature_type2)+dataBean.getTemperature());
            }

            if (Common.CommonStr.TEMPERATURE_TYPE3.equals(dataBean.getTemperatureType())) {
                tvAnimalHeat.setText(TextUtils.isEmpty(dataBean.getTemperature()) ? "" : getString(R.string.label_temperature_type3)+dataBean.getTemperature());
            }


            if (Common.CommonStr.BLOODSUGAR_TYPE1.equals(dataBean.getBloodSugarType())) {
                tvBloodGlucose.setText(TextUtils.isEmpty(dataBean.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type1)+dataBean.getBloodSugar());
            }
            if (Common.CommonStr.BLOODSUGAR_TYPE2.equals(dataBean.getBloodSugarType())) {
                tvBloodGlucose.setText(TextUtils.isEmpty(dataBean.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type2)+dataBean.getBloodSugar());
            }
            if (Common.CommonStr.BLOODSUGAR_TYPE3.equals(dataBean.getBloodSugarType())) {
                tvBloodGlucose.setText(TextUtils.isEmpty(dataBean.getBloodSugar()) ? "" : getString(R.string.label_bloodsugar_type3)+dataBean.getBloodSugar());
            }



        }
    }
}
