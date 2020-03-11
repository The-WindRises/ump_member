package it.swiftelink.com.vcs_member.ui.activity.inquiry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.ObjectEvent;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.presenter.main.MainCotract;
import it.swiftelink.com.factory.presenter.main.MainPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.fragment.MyPagerAdapter;
import it.swiftelink.com.vcs_member.ui.fragment.inquiry.InquiryFragment;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class MyInquiryActivity extends BasePresenterActivity<MainCotract.Presenter> implements MainCotract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_my_inquiry)
    TabLayout tbMyInquiry;
    @BindView(R.id.vp_my_inquiry)
    ViewPager vpMyInquiry;
    @BindView(R.id.stateView)
    StateView stateView;


    private List<Fragment> fragmentList;
    private Integer[] list_Title = {R.string.tab_my_inquiry1, R.string.tab_my_inquiry2, R.string.tab_my_inquiry3};
    private List<String> spinnerData;

    private TagFlowLayout fl_dialog_evaluate;
    private boolean isDialogShow = false;
    private List<EvaluateLabelResModel.DataBean.DataListBean> dataList;

    public static void show(Activity activity, String type) {

        Intent intent = new Intent(activity, MyInquiryActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_inquiry;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_all_inquiry));

        fragmentList = new ArrayList<>();

        fragmentList.add(InquiryFragment.newInstance(Common.CommonStr.INQUIRY_TYPE1));
        fragmentList.add(InquiryFragment.newInstance(Common.CommonStr.INQUIRY_TYPE2));
        fragmentList.add(InquiryFragment.newInstance(Common.CommonStr.INQUIRY_TYPE3));


        vpMyInquiry.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), MyInquiryActivity.this, fragmentList, list_Title));
        tbMyInquiry.setupWithViewPager(vpMyInquiry);


    }


    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getStringExtra("type");

            if (Common.CommonStr.INQUIRY_TYPE1.equals(type)) {
                vpMyInquiry.setCurrentItem(0);
            }

            if (Common.CommonStr.INQUIRY_TYPE2.equals(type)) {
                vpMyInquiry.setCurrentItem(1);
            }

            if (Common.CommonStr.INQUIRY_TYPE3.equals(type)) {
                vpMyInquiry.setCurrentItem(2);
            }
        }

//        mPresenter.getIsMember();
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
    public void Event(ObjectEvent objectEvent) {
        switch (objectEvent.getType()) {
            case Common.EventbusType.END_VIDEO_INQUIRY:
                String videoEndType = App.getSPUtils().getString(Common.SPApi.VIDEO_END_TYPE);
                if (Common.CommonStr.ENTER_FROM_INQUIRY.equals(videoEndType)) {
                    SaveEvaluateModel evaluateModel = (SaveEvaluateModel) objectEvent.getObj();
                    startEvaluate(evaluateModel);
                    for (Fragment fragment : fragmentList) {
                        if (fragment instanceof InquiryFragment) {
                            InquiryFragment inquiryFragment = (InquiryFragment) fragment;
                            inquiryFragment.refreshData();
                        }
                    }
                }

                break;
            default:
                break;
        }
    }


    private void startEvaluate(final SaveEvaluateModel evaluateModel) {
        if (!isDialogShow) {
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

                            tv_doctor_name.setText(App.getSPUtils().getString(Common.SPApi.DOCTOR_NAME,""));

                            GlideLoadUtils.getInstance().glideLoadCircle(MyInquiryActivity.this,
                                    App.getSPUtils().getString(Common.SPApi.DOCTOR_HEADER_IMAGE),iv_doctor_header,R.mipmap.icon_dc_man);

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
                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();

                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());
                                    saveEvaluateModel.setPatientScore(rb_grade.getProgress());

                                    Set<Integer> selectedList = fl_dialog_evaluate.getSelectedList();
                                    StringBuffer buffer = new StringBuffer();
                                    for (Integer selectPos : selectedList) {
                                        buffer.append(dataList.get(selectPos).getName() + ",");
                                    }
                                    if (buffer.toString().equals("")) {
                                        App.showToast(R.string.plase_sel_tag);
                                        return;
                                    }
                                    saveEvaluateModel.setPatientEvaluation(buffer.toString());
                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE2);
                                    mPresenter.saveEvaluate(saveEvaluateModel);
                                    isDialogShow = false;
                                    dialog.dismiss();
                                }
                            });

                            iv_close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();

                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());

                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    mPresenter.saveEvaluate(saveEvaluateModel);

                                    isDialogShow = false;
                                    dialog.dismiss();

                                }
                            });

                        }
                    })
                    .setMargin(30)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
            isDialogShow = true;
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
    protected MainCotract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }



//    @Override
//    public void getIsMemberSuccess(BooleanResModel model) {
//        App.getSPUtils().put(Common.SPApi.IS_MEMBER, true);
//    }

    @Override
    public void checkVersionSuccess(VersionResModel versionResModel) {

    }

    @Override
    public void loginSuccess(LoginResModel resModel) {

    }

    @Override
    public void saveEvaluateSuccess() {
        showContent();
    }

    @Override
    public void getEvaluateLabelSuccess(EvaluateLabelResModel model) {
        showContent();
        dataList = model.getData().getDataList();

        final LayoutInflater mInflater = LayoutInflater.from(this);
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
    public void getTrtcSuccess(TrtcConfigResModel resModel) {

    }

    @Override
    public void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel) {

    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,  errorCode ,errorMsg);

        if (Common.UrlApi.GET_ISMEMBER == type) {
            showContent();
            App.getSPUtils().put(Common.SPApi.IS_MEMBER, false);
        } else {
            showContent();
        }
    }
}
