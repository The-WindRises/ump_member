package it.swiftelink.com.vcs_member.ui.fragment.main;


import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import java.text.DecimalFormat;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.utils.LocalManageUtil;
import it.swiftelink.com.common.utils.Utils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.presenter.main.UserInfoContract;
import it.swiftelink.com.factory.presenter.main.UserInfoPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.ui.activity.account.LoginActivity;
import it.swiftelink.com.vcs_member.ui.activity.health.ElectronicMedicalRecordActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.MyInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiryReport.InquiryReportListActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.CollectDoctorActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.HelpActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.MyPackageCardActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.ShareActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.WalletActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.EvaluateCenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.OrderListActivity;
import it.swiftelink.com.vcs_member.ui.activity.recipe.MyRecipeActivity;
import it.swiftelink.com.vcs_member.ui.activity.user.MyCouponActivity;
import it.swiftelink.com.vcs_member.ui.activity.user.PersonInfoActivity;
import it.swiftelink.com.vcs_member.utils.LogUpload;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BasePresenterFragment<UserInfoContract.Presenter> implements UserInfoContract.View {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;
    @BindView(R.id.discount_coupon)
    TextView discountCoupon;
    @BindView(R.id.tv_package_card_value)
    TextView packageCardCountTv;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_my8)
    TextView tvMy8;
    @BindView(R.id.tv_person_info)
    TextView tvPersonInfo;
    @BindView(R.id.tv_my9)
    TextView tvMy9;
    @BindView(R.id.ll_package_card)
    LinearLayout packageCardCountLl;
    private int defoultSelectPos;

    public MyFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected UserInfoContract.Presenter initPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        tvTitle.setText(getString(R.string.label_my));
        btnBack.setVisibility(View.GONE);

        //获取当前系统语言
        String selectLanguage =  App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE);
        if (selectLanguage.equals(Common.CommonStr.LANGUAGE1)) {
            defoultSelectPos = LanguageType1;
        } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE2)) {
            defoultSelectPos = LanguageType2;
        } else if (selectLanguage.equals(Common.CommonStr.LANGUAGE3)) {
            defoultSelectPos = LanguageType3;
        }


        tvMy9.setText(Utils.getVersionName());
    }


    /**
     * 获取版本名称
     *
     * @return 版本名称
     */
    public String getVersionName() {

        //获取包管理器
        PackageManager pm = getContext().getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getContext().getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";

    }

    @Override
    public void onResume() {
        super.onResume();

        String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
        if (!TextUtils.isEmpty(token)) {
            tvMy8.setVisibility(View.VISIBLE);
            tvPersonInfo.setText(getString(R.string.label_user_info1));
        } else {
            tvMy8.setVisibility(View.GONE);
            tvPersonInfo.setText(getString(R.string.label_to_login));
        }
        loadData();
    }

    private void loadData() {
        mPresenter.getUserInfo();
    }

    @Override
    protected StateView getStateView() {
        return stateView;
    }

    @OnClick({R.id.tv_person_info, R.id.ll_account_balance, R.id.ll_discount_coupon, R.id.tv_open_report,
            R.id.tv_order_to_pay, R.id.tv_order_finish, R.id.tv_order_all, R.id.tv_to_inquiry,
            R.id.tv_finish_inquiry, R.id.tv_all_inquiry, R.id.tv_my1, R.id.tv_my2, R.id.tv_my3,
            R.id.tv_my4, R.id.tv_my5, R.id.tv_my6, R.id.tv_my7, R.id.tv_my8, R.id.version, R.id.ll_package_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_person_info:
                String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
                if (!TextUtils.isEmpty(token)) {
                    PersonInfoActivity.show(getActivity());
                } else {
                    LoginActivity.show(getActivity());
                }
                break;
            case R.id.ll_account_balance:
                WalletActivity.show(getActivity());
                break;
            case R.id.ll_discount_coupon:
                MyCouponActivity.show(getActivity(), "");
                break;
            case R.id.ll_package_card:
                MyPackageCardActivity.show(Objects.requireNonNull(getActivity()));
                break;

            case R.id.tv_open_report:
                InquiryReportListActivity.show(getActivity());
                break;
            case R.id.tv_order_to_pay:
                OrderListActivity.show(getActivity(), Common.CommonStr.SET_MEAL_TYPE1);
                break;
            case R.id.tv_order_finish:
                OrderListActivity.show(getActivity(), Common.CommonStr.SET_MEAL_TYPE2);
                break;
            case R.id.tv_order_all:
                OrderListActivity.show(getActivity(), Common.CommonStr.SET_MEAL_TYPE3);
                break;
            case R.id.tv_to_inquiry:
                MyInquiryActivity.show(getActivity(), Common.CommonStr.INQUIRY_TYPE1);
                break;
            case R.id.tv_finish_inquiry:
                MyInquiryActivity.show(getActivity(), Common.CommonStr.INQUIRY_TYPE2);
                break;
            case R.id.tv_all_inquiry:
                MyInquiryActivity.show(getActivity(), Common.CommonStr.INQUIRY_TYPE3);
                break;
            case R.id.tv_my1:
                MyRecipeActivity.show(Objects.requireNonNull(getActivity()));
                break;
            case R.id.tv_my2: //健康档案
                ElectronicMedicalRecordActivity.show(getActivity());
                break;
            case R.id.tv_my3:
                EvaluateCenterActivity.show(getActivity());
                break;
            case R.id.tv_my4:
                CollectDoctorActivity.show(getActivity());
                break;
            case R.id.tv_my5:
                HelpActivity.show(getActivity());
                break;
            case R.id.tv_my6:
                ShareActivity.show(getActivity());
                break;
            case R.id.tv_my7:
                setMaritalStatus();
                break;
            case R.id.tv_my8:
                //退出
                loginoutIm();
                App.getSPUtils().put(Common.SPApi.LOGINID,"");
                JPushInterface.deleteAlias(Objects.requireNonNull(getActivity()),0);
                LoginActivity.show(getActivity());
                getActivity().finish();

                break;
            case R.id.version:
                LogUpload.upload();
                break;
        }
    }

    void loginoutIm() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
            }

            @Override
            public void onSuccess() {
                //登出成功
                App.getSPUtils().put("imIsLogin", false);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getUserInfoSuccess(UserInfoResModel model) {

        showContent();
        UserInfoResModel.DataBean data = model.getData();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (data != null) {
            GlideLoadUtils.getInstance().glideLoadCircle(getActivity(), data.getHeadImg(), ivHeader, R.mipmap.icon_header);

            App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, data.getHeadImg());
            App.getSPUtils().put(Common.SPApi.USER_NAME, data.getName());
            tvUserName.setText(data.getName());
            tvAccountBalance.setText(decimalFormat.format(data.getAmount()) + "");
            discountCoupon.setText(data.getCouponNum() + "");
            packageCardCountTv.setText(data.getPackageNum() + "");

        }

    }


    @Override
    public void EditUserInfoSuccess() {

    }

    @Override
    public void uploadImageSuccess(UploadResModel resModel) {

    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        showContent();

    }

    private final int LanguageType1 = 1;
    private final int LanguageType2 = 2;
    private final int LanguageType3 = 3;

    private void setMaritalStatus() {
        CustomDialog.
                newInstance(R.layout.dialog_select_language2)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {
                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {
                        TextView tv_language1 = viewGroup.findViewById(R.id.tv_language1);
                        TextView tv_language2 = viewGroup.findViewById(R.id.tv_language2);
                        TextView tv_language3 = viewGroup.findViewById(R.id.tv_language3);

                        tv_language1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setLanguage(LanguageType1);
                                dialog.dismiss();
                            }
                        });

                        tv_language2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setLanguage(LanguageType2);
                                dialog.dismiss();
                            }
                        });

                        tv_language3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                setLanguage(LanguageType3);
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getActivity().getSupportFragmentManager());
    }


    private void setLanguage(int type) {

        switch (type) {
            case LanguageType1:

                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                break;
            case LanguageType2:

                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE2);
                break;
            case LanguageType3:

                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE3);
                break;
        }
        if (type != defoultSelectPos) {
            selectLanguage(type);
        }

    }

    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(Application.getInstance(), select);
        Objects.requireNonNull(getActivity()).recreate();
    }

}
