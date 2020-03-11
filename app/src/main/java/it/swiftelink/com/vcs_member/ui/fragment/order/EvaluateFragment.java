package it.swiftelink.com.vcs_member.ui.fragment.order;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.recycler.EndlessRecyclerOnScrollListener;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;
import it.swiftelink.com.factory.presenter.order.EvaluateListContract;
import it.swiftelink.com.factory.presenter.order.EvaluateListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends BasePresenterFragment<EvaluateListContract.Presenter> implements EvaluateListContract.View {


    @BindView(R.id.rcv_my_evaluate)
    RecyclerView rcvMyEvaluate;
    @BindView(R.id.stateView)
    StateView stateView;


    private BaseRecyclerAdapter<EvaluateListResModel.DataBean.DataListBean> mAdapter;
    private List<EvaluateLabelResModel.DataBean.DataListBean> dataList;
    private TagFlowLayout fl_dialog_evaluate;
    private String type;

    private int currentPage = 1;
    private int pageSize = 10;

    public EvaluateFragment() {
        // Required empty public constructor
    }

    public static EvaluateFragment newInstance(String type) {
        EvaluateFragment myFragment = new EvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evaluate;
    }

    @Override
    protected EvaluateListContract.Presenter initPresenter() {
        return new EvaluateListPresenter(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        rcvMyEvaluate.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvMyEvaluate.setAdapter(mAdapter = new BaseRecyclerAdapter<EvaluateListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {

                if (pos == getItemCount() - 1) {
                    return footLayout;
                } else {
                    return R.layout.item_rcv_my_evaluate;
                }

            }

            @Override
            protected ViewHolder<EvaluateListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                if (footLayout == viewType) {
                    return new FootViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }
            }

            @Override
            public void onUpdate(EvaluateListResModel.DataBean.DataListBean dataListBean, ViewHolder<EvaluateListResModel.DataBean.DataListBean> viewHolder) {
                startEvaluate(dataListBean);
            }
        });

        //添加上拉加载更多
        mAdapter.setLoadType();
        rcvMyEvaluate.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                mAdapter.setLoadState(BaseRecyclerAdapter.LOADING);
                loadData(currentPage);
            }
        });

    }

    private void startEvaluate(final EvaluateListResModel.DataBean.DataListBean dataListBean) {

        CustomDialog.
                newInstance(R.layout.dialog_evaluate)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        ImageView iv_doctor_header = viewGroup.findViewById(R.id.doctorIconIv);
                        TextView tv_doctor_name = viewGroup.findViewById(R.id.doctorNameTv);
                        final MaterialRatingBar rb_grade = viewGroup.findViewById(R.id.rb_grade);
                        fl_dialog_evaluate = viewGroup.findViewById(R.id.tabLayout);
                        Button btn_confirm_evaluate = viewGroup.findViewById(R.id.submit);
                        LinearLayout iv_close = viewGroup.findViewById(R.id.closeIv);

                        tv_doctor_name.setText(dataListBean.getDoctorName());

                        GlideLoadUtils.getInstance().glideLoadCircle(getActivity(), dataListBean.getDoctorHeadImg(), iv_doctor_header, R.mipmap.icon_dc_man);

                        getEvaluateLabelList(rb_grade.getProgress());
                        rb_grade.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                getEvaluateLabelList(rating);
                            }
                        });

                        btn_confirm_evaluate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EvaluateModel evaluateModel = new EvaluateModel();

                                evaluateModel.setId(dataListBean.getId());


                                evaluateModel.setPatientScore(rb_grade.getProgress());
                                evaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE2);


                                Set<Integer> selectedList = fl_dialog_evaluate.getSelectedList();

                                StringBuffer buffer = new StringBuffer();

                                for (Integer selectPos : selectedList) {
                                    buffer.append(dataList.get(selectPos).getName() + ",");
                                }
                                evaluateModel.setPatientEvaluation(buffer.toString());
                                if (buffer.toString().equals("")) {
                                    App.showToast(R.string.plase_sel_tag);
                                    return;
                                }
                                mPresenter.toEvaluate(evaluateModel);
                                dialog.dismiss();
                            }
                        });

                        iv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                            }
                        });


                    }
                })
                .setMargin(30)
                .setOutCancel(false)

                .show(getActivity().getSupportFragmentManager());
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        loadData(currentPage);
    }

    private void loadData(int currentPage) {
        this.currentPage = currentPage;
        if (!TextUtils.isEmpty(type)) {
            mPresenter.getEvaluateList(currentPage, pageSize, type, "");
        }
    }

    public void refreshData() {
        loadData(1);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.EVALUATE_SUCCESS:
                if (!TextUtils.isEmpty(type)) {
                    loadData(1);
                }
                break;
            default:
                break;
        }
    }

    private void getEvaluateLabelList(float score) {
        EvaluateLabelModel labelModel = new EvaluateLabelModel();
        labelModel.setType("Member");
        labelModel.setLanguage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE,Common.CommonStr.LANGUAGE1));
        labelModel.setScore((int) score);

        mPresenter.getEvaluateLabel(labelModel);
    }


    @Override
    protected StateView getStateView() {
        return stateView;
    }

    @Override
    public void getEvaluateListSuccess(EvaluateListResModel model) {

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
    public void toEvaluateSuccess() {

        showContent();
        App.showToast(R.string.label_evaluate_success);

        EventBus.getDefault().post(new MsgEvent(Common.EventbusType.EVALUATE_SUCCESS, ""));
    }


    @Override
    public void getEvaluateLabelSuccess(EvaluateLabelResModel model) {
        showContent();
        dataList = model.getData().getDataList();

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        if (dataList != null && dataList.size() > 0) {
            fl_dialog_evaluate.setAdapter(new TagAdapter<EvaluateLabelResModel.DataBean.DataListBean>(dataList) {
                @Override
                public View getView(FlowLayout parent, int position, EvaluateLabelResModel.DataBean.DataListBean dataListBean1) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_tv,
                            fl_dialog_evaluate, false);
                    tv.setText(dataListBean1.getName());
                    return tv;
                }
            });
        }

    }

    @Override
    public void retry(int type) {
        loadData(1);
    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type, errorCode,errorMsg);

        if (type == Common.UrlApi.GET_EVALUATION_LIST && currentPage > 1) {
            showContent();
            mAdapter.setLoadState(BaseRecyclerAdapter.LOADING_COMPLETE);
        } else if (type == Common.UrlApi.GET_EVALUATION_LIST && currentPage == 1) {
            mAdapter.clear();
            showRetry(type);
        } else {
            showContent();
        }
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<EvaluateListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_order_state)
        TextView tvOrderState;
        @BindView(R.id.tips_score)
        TextView tipsScore;
        @BindView(R.id.tips_attitude)
        TextView tipsAttitude;
        @BindView(R.id.tv_member_name)
        TextView tvMemberName;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R.id.tv_time_inquiry)
        TextView tvTimeInquiry;
        @BindView(R.id.tv_grade)
        TextView tvGrade;
        @BindView(R.id.rb_grade)
        AppCompatRatingBar rbGrade;
        @BindView(R.id.fl_service_attitude)
        TagFlowLayout flServiceAttitude;
        @BindView(R.id.view_line1)
        View viewLine1;
        @BindView(R.id.ll_evaluate_time)
        LinearLayout llEvaluateTime;
        @BindView(R.id.view_line2)
        View viewLine2;
        @BindView(R.id.ll_to_evaluate)
        LinearLayout llToEvaluate;
        @BindView(R.id.tv_evaluate_time)
        TextView tvEvaluateTime;

        public MyViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(getContext()).inflate(R.layout.item_rcv_my_evaluate, null);
        }

        @Override
        protected void onBind(EvaluateListResModel.DataBean.DataListBean dataBean) {
            tvOrderId.setText(dataBean.getNo());
            tvOrderState.setText(Common.CommonStr.EVALUATE_TYPE1.equals(dataBean.getPatientStatus()) ? getString(R.string.label_remain_to_evaluated) : getString(R.string.label_have_evaluation));
            tvMemberName.setText(dataBean.getPatientName());
            tvDoctorName.setText(dataBean.getDoctorName());
            tvTimeInquiry.setText(DateTimeUtils.getDateToString(dataBean.getDiagnosisStartTime() + ""));

            if (Common.CommonStr.EVALUATE_TYPE2.equals(dataBean.getPatientStatus())) {
                llEvaluateTime.setVisibility(View.VISIBLE);
                llToEvaluate.setVisibility(View.GONE);
                tipsScore.setVisibility(View.VISIBLE);
                tvGrade.setVisibility(View.VISIBLE);
                rbGrade.setVisibility(View.VISIBLE);
                tipsAttitude.setVisibility(View.VISIBLE);
                flServiceAttitude.setVisibility(View.VISIBLE);

                tvGrade.setText(dataBean.getPatientScore() + getString(R.string.label_point));
                rbGrade.setProgress(dataBean.getPatientScore());

                if (!TextUtils.isEmpty(dataBean.getPatientDate())) {
                    tvEvaluateTime.setText(DateTimeUtils.getDateToString(dataBean.getPatientDate() + ""));
                } else {
                    tvEvaluateTime.setText("");
                }


                if (dataBean != null && !TextUtils.isEmpty(dataBean.getPatientEvaluation())) {
                    String[] strs = dataBean.getPatientEvaluation().split(",");

                    final LayoutInflater inflater = LayoutInflater.from(getActivity());

                    flServiceAttitude.setAdapter(new TagAdapter<String>(strs) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            TextView tv = (TextView) inflater.inflate(R.layout.item_flow_evaluate,
                                    flServiceAttitude, false);
                            tv.setText(s);
                            return tv;
                        }
                    });
                }

            } else {
                llEvaluateTime.setVisibility(View.GONE);
                llToEvaluate.setVisibility(View.VISIBLE);
                tipsScore.setVisibility(View.GONE);
                tvGrade.setVisibility(View.GONE);
                rbGrade.setVisibility(View.GONE);
                tipsAttitude.setVisibility(View.GONE);
                flServiceAttitude.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.btn_to_evaluate)
        public void onViewClicked() {
            mAdapter.onUpdate(mData, this);
        }
    }
}
