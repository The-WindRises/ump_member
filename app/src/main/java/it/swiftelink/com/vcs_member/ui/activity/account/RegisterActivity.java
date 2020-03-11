package it.swiftelink.com.vcs_member.ui.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.factory.model.account.AgrreementResModel;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.RegisterModel;
import it.swiftelink.com.factory.model.account.RegisterResModel;
import it.swiftelink.com.factory.presenter.account.RegisterConract;
import it.swiftelink.com.factory.presenter.account.RegisterPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.MainActivity;
import it.swiftelink.com.vcs_member.ui.activity.user.AgreementActivity;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class RegisterActivity extends BasePresenterActivity<RegisterConract.Presenter> implements RegisterConract.View {


    @BindView(R.id.agreementTV)
    TextView agreementTV;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_phone_type)
    TextView tvPhoneType;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_phone_send)
    TextView tvPhoneSend;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.et_input_psd)
    EditText etInputPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.iv_register_agree)
    ImageView ivRegisterAgree;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_phone_send)
    LinearLayout llPhoneSend;

    private String title;
    private String content;
    private String agrreementId;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, RegisterActivity.class));
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.titile_register));
    }

    @Override
    protected RegisterConract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getAgrreement(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE,Common.CommonStr.LANGUAGE1), "MembershipRegistrationAgreement");

    }

    @OnClick({R.id.btn_back, R.id.ll_phone_type, R.id.agreementTV, R.id.tv_get_verification_code,
            R.id.iv_register_agree, R.id.tv_register_agree, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_phone_type:
                selectPhoneType();
                break;
            case R.id.tv_get_verification_code:
                getPhoneCode();
                break;
            case R.id.iv_register_agree:
                ivRegisterAgree.setSelected(!ivRegisterAgree.isSelected());
                break;
            case R.id.tv_register_agree:


                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.agreementTV:
                Intent intent = new Intent(this, AgreementActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                this.startActivity(intent);
                break;

        }
    }

    private void register() {

        String psd = etInputPsd.getText().toString().trim();
        String confirmPsd = etConfirmPsd.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String phoneVerifyCode = etVerificationCode.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            App.showToast(getString(R.string.please_input_phone));
            return;
        }

        if (!TxtUtils.isPhoneLegal(phone)) {
            App.showToast(R.string.please_input_phone_right);
            return;
        }

        if (TextUtils.isEmpty(phoneVerifyCode)) {
            App.showToast(R.string.please_input_verifycode);
            return;
        }

        if (TextUtils.isEmpty(psd)) {
            App.showToast(R.string.please_input_password);
            return;
        }

        if (!TxtUtils.chickedPassword(psd)) {
            App.showToast("密码必须8-14，同时包含数字，大小写字母和特殊字符");
            return;
        }

        if (TextUtils.isEmpty(confirmPsd)) {
            App.showToast(R.string.please_input_psd_onece);
            return;
        }

        if (!psd.equals(confirmPsd)) {
            App.showToast(R.string.password_inconsistency);
            return;
        }

        if (!ivRegisterAgree.isSelected()) {
            App.showToast(R.string.please_read_agree);
            return;
        }


        RegisterModel registerModel = new RegisterModel();

        String phoneType = tvPhoneType.getText().toString().trim();
        if (phoneType.equals("+852")) {
            registerModel.setMobile("852 " + phone);
        } else if(phoneType.equals("+853")){
            registerModel.setMobile("853 " + phone);
        }else  {
            registerModel.setMobile(phone);
        }
        registerModel.setPassword(psd);
        registerModel.setSmsCode(phoneVerifyCode);
        registerModel.setType("2");
        registerModel.setAgrreementId(agrreementId);
        mPresenter.register(registerModel);
    }

    private void getPhoneCode() {

        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            App.showToast(getString(R.string.please_input_phone));
            return;
        }
        if (!TxtUtils.isPhoneLegal(phone)) {
            App.showToast(R.string.please_input_phone_right);
            return;
        }

        GetPhoneCodeModel model = new GetPhoneCodeModel();
        String phoneType = tvPhoneType.getText().toString().trim();
        if (phoneType.equals("+852")) {
            model.setMobile("852 " + phone);
        } else if(phoneType.equals("+853")){
            model.setMobile("853 " + phone);
        }else  {
            model.setMobile(phone);
        }
        model.setType(2);
        llPhoneSend.setVisibility(View.VISIBLE);
        tvPhoneSend.setText(phone);
        mPresenter.getPhoneCode(model);
    }

    private void startTimer() {

        final int count = 60;//倒计时10秒


        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Func1<Long, Long>() {

                    @Override
                    public Long call(Long aLong) {
                        return count - aLong;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        tvGetVerificationCode.setEnabled(false);
                        tvGetVerificationCode.setTextColor(Color.GRAY);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                tvGetVerificationCode.setEnabled(true);
                tvGetVerificationCode.setText(getString(R.string.label_get_code));
                tvGetVerificationCode.setTextColor(getResources().getColor(R.color.textColor2));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                tvGetVerificationCode.setText(getString(R.string.label_remaining) + " " + aLong + " " +getString(R.string.label_seconds));
            }
        });
    }

    @Override
    public void registerSuccess(RegisterResModel resModel) {
        App.getSPUtils().put(Common.SPApi.TOKEN, resModel.getData().getAccessToken());
        App.getSPUtils().put(Common.SPApi.ID, resModel.getData().getUserId());
        App.getSPUtils().put(Common.SPApi.LOGINID, resModel.getData().getLoginId());
        App.getSPUtils().put(Common.SPApi.USER_NAME, resModel.getData().getUserName());
        App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, resModel.getData().getHeadImg());

        showContent();
        App.showToast(R.string.msg_register_success);
        MainActivity.show(this);
    }

    @Override
    public void getPhoneCodeResModelSuccess() {
        showContent();
        App.showToast(R.string.msg_get_code_success);
        startTimer();
    }

    @Override
    public void getAgrreementSuess(AgrreementResModel agrreementResModel) {
        showContent();
        if (agrreementResModel.getData() == null)
            return;
        title = agrreementResModel.getData().getTitle();
        content = agrreementResModel.getData().getContent();
        agrreementId = agrreementResModel.getData().getId();
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        if (type == Common.UrlApi.GET_PHONE_CODE) {
            llPhoneSend.setVisibility(View.GONE);
        }
        showContent();
        App.showToast(errorMsg);
    }


    private void selectPhoneType() {
        CustomDialog.
                newInstance(R.layout.dialog_select_phone_type)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        final LinearLayout ll_phone_type1 = viewGroup.findViewById(R.id.ll_phone_type1);
                        final LinearLayout ll_phone_type2 = viewGroup.findViewById(R.id.ll_phone_type2);
                        final LinearLayout ll_phone_type3 = viewGroup.findViewById(R.id.ll_phone_type3);

                        ll_phone_type1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvPhoneType.setText("+86");
                                dialog.dismiss();

                            }
                        });

                        ll_phone_type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvPhoneType.setText("+852");
                                dialog.dismiss();
                            }
                        });
                        ll_phone_type3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvPhoneType.setText("+853");
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

}
