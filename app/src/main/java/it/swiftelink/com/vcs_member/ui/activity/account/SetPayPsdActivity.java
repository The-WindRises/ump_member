package it.swiftelink.com.vcs_member.ui.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.presenter.account.PayPsdContract;
import it.swiftelink.com.factory.presenter.account.PayPsdPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class SetPayPsdActivity extends BasePresenterActivity<PayPsdContract.Presenter> implements PayPsdContract.View {

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
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_phone_send)
    LinearLayout llPhoneSend;

    public static void show(Activity activity, String type) {
        Intent intent = new Intent(activity, SetPayPsdActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pay_psd;
    }


    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            String type = intent.getStringExtra("type");

            if (Common.CommonStr.Pay_PSD_TYPE1.equals(type)) {
                tvTitle.setText(getString(R.string.label_set_pay_psd));
            }

            if (Common.CommonStr.Pay_PSD_TYPE2.equals(type)) {
                tvTitle.setText(getString(R.string.label_change_pay_psd));
            }
        }
    }

    @Override
    protected PayPsdContract.Presenter initPresenter() {
        return new PayPsdPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.ll_phone_type, R.id.tv_get_verification_code,
            R.id.btn_register})
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
            case R.id.btn_register:
                confirmPayPsd();
                break;
        }
    }

    private void confirmPayPsd() {

        String phone = etPhone.getText().toString().trim();

        String psd = etInputPsd.getText().toString().trim();
        String verificationCode = etVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            App.showToast(R.string.please_input_phone);
            return;
        }

        if (!TxtUtils.isPhoneLegal(phone)) {
            App.showToast(R.string.please_input_phone_right);
            return;
        }

        if (TextUtils.isEmpty(verificationCode)) {
            App.showToast(R.string.please_input_verifycode);
            return;
        }

        if (TextUtils.isEmpty(psd)) {
            App.showToast(R.string.please_input_pay_psd);
            return;
        }

        String phoneType = tvPhoneType.getText().toString().trim();
        if (phoneType.equals("+852")) {
            mPresenter.setPayPsd(psd, "852 " + phone, verificationCode);
        } else if(phoneType.equals("+853")){
            mPresenter.setPayPsd(psd, "853 " + phone, verificationCode);
        }else {
            mPresenter.setPayPsd(psd, phone, verificationCode);
        }


    }

    private void getPhoneCode() {

        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            App.showToast(R.string.please_input_phone);
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
        } else  if(phoneType.equals("+853")){
            model.setMobile("853 " + phone);
        }else {
            model.setMobile(phone);
        }
        model.setType(4);
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
                tvGetVerificationCode.setText("剩余" + aLong + "秒");
            }
        });
    }


    @Override
    public void setPayPsdSuccess() {
        showContent();
        App.showToast(R.string.msg_setting_success);
        finish();
    }

    @Override
    public void getPhoneCodeResModelSuccess() {
        showContent();
        App.showToast(R.string.msg_get_code_success);
        startTimer();
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
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
