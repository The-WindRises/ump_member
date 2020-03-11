package it.swiftelink.com.vcs_member.ui.activity.recipe;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.recipe.RecipeListResModel;
import it.swiftelink.com.factory.presenter.recipe.RecipeListContract;
import it.swiftelink.com.factory.presenter.recipe.RecipeListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

public class MyRecipeActivity extends BasePresenterActivity<RecipeListContract.Presenter> implements RecipeListContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_my_recipe)
    RecyclerView rcvMyRecipe;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<RecipeListResModel.DataBean.DataListBean> mAdapter;

    private int currentPage = 1;
    private int pageSize = 10;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, MyRecipeActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_recipe;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_my_recipe));

        rcvMyRecipe.setLayoutManager(new LinearLayoutManager(this));

        rcvMyRecipe.setAdapter(mAdapter = new BaseRecyclerAdapter<RecipeListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    RecipeListResModel.DataBean.DataListBean item = getItem(pos);
                    if (item != null && item.getVaildType().equals("NORMAL")) {
                        return R.layout.item_rcv_my_recipe;
                    } else {
                        return R.layout.item_rcv_my_recipe_loss_effect;
                    }
                }


            }

            @Override
            protected ViewHolder<RecipeListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(RecipeListResModel.DataBean.DataListBean dataListBean, ViewHolder<RecipeListResModel.DataBean.DataListBean> viewHolder) {

            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvMyRecipe.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
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
        mPresenter.geRecipeList(currentPage, pageSize);
    }

    @Override
    protected RecipeListContract.Presenter initPresenter() {
        return new RecipeListPresenter(this);
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
    public void getRecipeListSuccess(RecipeListResModel model) {

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
    public void retry(int type) {
        loadData(1);
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        if (type == Common.UrlApi.GET_RECIPE_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_RECIPE_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<RecipeListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_medical_record_no)
        TextView tvMedicalRecordNo;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_division)
        TextView tvDivision;
        @BindView(R.id.tv_tentative_diagnosis)
        TextView tvTentativeDiagnosis;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_inquiry_id)
        TextView tvOrderId;

        public MyViewHolder(View itemView) {
            super(itemView);
//            LayoutInflater.from(this).inflate(R.layout.item_rcv_my_recipe, null);
        }

        @Override
        protected void onBind(RecipeListResModel.DataBean.DataListBean dataListBean) {
            RecipeListResModel.DataBean.DataListBean.DiagnosisBean diagnosis = dataListBean.getDiagnosis();
            if (diagnosis != null) {
                tvOrderId.setText(TextUtils.isEmpty(diagnosis.getNo()) ? "" : diagnosis.getNo());
                tvUserName.setText(TextUtils.isEmpty(diagnosis.getPatientName()) ? "" : diagnosis.getPatientName());
                tvMedicalRecordNo.setText(TextUtils.isEmpty(diagnosis.getMedicalRecord()) ? "" : diagnosis.getMedicalRecord());
                tvDoctorName.setText(TextUtils.isEmpty(diagnosis.getDoctorName()) ? "" : diagnosis.getDoctorName());
                tvDivision.setText(TextUtils.isEmpty(diagnosis.getSectionOfficeName()) ? "" : diagnosis.getSectionOfficeName());
                tvTentativeDiagnosis.setText(dataListBean.getDescription());
                tvTime.setText(DateTimeUtils.getDateToString(dataListBean.getCreationDate() + ""));
            }
        }

        @OnClick(R.id.tv_look_inquiry_report)
        public void onViewClicked() {
            if ("NORMAL".equals(mData.getVaildType())) {
                RecipeDetailActivity.show(MyRecipeActivity.this, mData.getDiagnosis().getId());
            }
        }
    }
}
