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
import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.presenter.account.BindPhoneContract;
import it.swiftelink.com.factory.presenter.account.BindPhonePresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.MainActivity;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class BindPhoneActivity extends BasePresenterActivity<BindPhoneContract.Presenter> implements BindPhoneContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_phone_type)
    TextView tvPhoneType;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.tv_phone_send)
    TextView tvPhoneSend;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_phone_type)
    LinearLayout llPhoneType;
    private LoginModel loginModel;

    public static void show(Activity activity, LoginModel loginModel) {

        Intent intent = new Intent(activity, BindPhoneActivity.class);
        intent.putExtra("loginModel", loginModel);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_bind_phone));
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();

        if (intent != null) {
            loginModel = (LoginModel) intent.getSerializableExtra("loginModel");
        }
    }

    @Override
    protected BindPhoneContract.Presenter initPresenter() {
        return new BindPhonePresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.ll_phone_type, R.id.btn_bind, R.id.tv_get_verification_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_phone_type:
                selectPhoneType();
                break;
            case R.id.btn_bind:
                bindPhoneConfirm();
                break;
            case R.id.tv_get_verification_code:
                getPhoneCode();
                break;
        }
    }

    private void bindPhoneConfirm() {
        String phone = etPhone.getText().toString().trim();

        String phoneCode = etVerificationCode.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            App.showToast(R.string.please_input_phone);
            return;
        }

        if (!TxtUtils.isPhoneLegal(phone)) {
            App.showToast(R.string.please_input_phone_right);
            return;
        }

        if (TextUtils.isEmpty(phoneCode)) {
            App.showToast(R.string.please_input_verifycode);
            return;
        }
        String phoneType = tvPhoneType.getText().toString().trim();
        if (phoneType.equals("+852")) {
            loginModel.setMobile("852 " + phone);
        } else {
            loginModel.setMobile(phone);
        }
        loginModel.setSmsCode(phoneCode);
        mPresenter.bindPhone(loginModel);
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
        } else if (phoneType.equals("+853")) {
            model.setMobile("853 " + phone);
        } else {
            model.setMobile(phone);
        }
        model.setType(1);
        llPhoneType.setVisibility(View.VISIBLE);
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
                tvGetVerificationCode.setText(getString(R.string.label_remaining) + " " + aLong + " " + getString(R.string.label_seconds));
            }
        });
    }

    @Override
    public void bindPhoneSuccess(LoginResModel resModel) {
        showContent();
        if (resModel.getData() != null) {
            App.getSPUtils().put(Common.SPApi.TOKEN, resModel.getData().getAccessToken());
            App.getSPUtils().put(Common.SPApi.ID, resModel.getData().getUserId());
            App.getSPUtils().put(Common.SPApi.LOGINID, resModel.getData().getLoginId());
            App.getSPUtils().put(Common.SPApi.USER_NAME, resModel.getData().getUserName());
            App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, resModel.getData().getHeadImg());
            JPushInterface.setAlias(this,0,resModel.getData().getLoginId());
            finish();
            MainActivity.show(this);
        } else {
            App.showToast(R.string.label_bind_error);
        }
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        App.showToast(errorMsg);
        showContent();


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
