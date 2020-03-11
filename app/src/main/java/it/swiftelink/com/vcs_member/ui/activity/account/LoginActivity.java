package it.swiftelink.com.vcs_member.ui.activity.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.utils.LocalManageUtil;
import it.swiftelink.com.common.utils.LogcatHelper;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.presenter.account.LoginContract;
import it.swiftelink.com.factory.presenter.account.LoginPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.MainActivity;
import it.swiftelink.com.vcs_member.wxapi.WechatUtils;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

public class LoginActivity extends BasePresenterActivity<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_phone_type)
    TextView tvPhoneType;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_language1)
    TextView tvLanguage1;
    @BindView(R.id.tv_language2)
    TextView tvLanguage2;
    @BindView(R.id.tv_language3)
    TextView tvLanguage3;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_change_login_type)
    TextView tvChangeLoginType;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.tv_phone_send)
    TextView tvPhoneSend;
    @BindView(R.id.ll_phone_send)
    LinearLayout llPhoneSend;

    private final int LanguageType1 = 1;
    private final int LanguageType2 = 2;
    private final int LanguageType3 = 3;

    private final int LoginType1 = 0x001;
    private final int LoginType2 = 0x002;


    private int loginType = LoginType2;

    public int defoultSelectPos = 1;
    private boolean isSmsSend = false;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        //获取当前系统语言
        String selectLanguage = LocalManageUtil.getSelectLanguage(this);
        if (selectLanguage.equals(getString(R.string.language_cn))) {
            defoultSelectPos = LanguageType1;
        } else if (selectLanguage.equals(getString(R.string.language_traditional))) {
            defoultSelectPos = LanguageType2;
        } else if (selectLanguage.equals(getString(R.string.language_en))) {
            defoultSelectPos = LanguageType3;
        }

        tvLanguage1.setSelected(true);

    }

    @Override
    protected void initData() {
        super.initData();

//        setLanguage(defoultSelectPos);
        App.getSPUtils().put("accessToken", "");
        etUsername.setText("");
        etPassword.setText("");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        initData();
    }

    @OnClick({R.id.ll_phone_type, R.id.tv_language1, R.id.tv_language2, R.id.tv_language3,
            R.id.btn_login, R.id.btn_register, R.id.tv_forget_psd, R.id.ll_login_weixin,
            R.id.tv_to_main, R.id.tv_change_login_type, R.id.tv_get_verification_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_phone_type:
                selectPhoneType();
                break;
            case R.id.tv_language1:
                setLanguage(LanguageType1);
                break;
            case R.id.tv_language2:
                setLanguage(LanguageType2);
                break;
            case R.id.tv_language3:
                setLanguage(LanguageType3);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                RegisterActivity.show(this);
                break;
            case R.id.tv_forget_psd:
                ForgetPsdActivity.show(this);
                break;
            case R.id.ll_login_weixin:
                WechatUtils.getInstance(this).weichatLogin();
                break;
            case R.id.tv_to_main:
                MainActivity.show(this);
                finish();
                break;
            case R.id.tv_change_login_type:
                if (LoginType1 == loginType) {
                    loginType = LoginType2;
                } else {
                    loginType = LoginType1;
                }
                changeLoginType(loginType);
                break;
            case R.id.tv_get_verification_code:
                getPhoneCode();
                break;

        }
    }

    private void changeLoginType(int loginType) {

        if (loginType == LoginType1) {
            tvChangeLoginType.setText(R.string.label_login_ems);
            etUsername.setHint(getResources().getString(R.string.please_enter_phone_or_account));

            tvGetVerificationCode.setVisibility(View.GONE);
            llPhoneSend.setVisibility(View.GONE);
            etPassword.setHint(R.string.tips_input_password);
        }

        if (loginType == LoginType2) {
            tvChangeLoginType.setText(R.string.login_by_psd);
            etUsername.setHint(getResources().getString(R.string.please_enter_account_number));
            tvGetVerificationCode.setVisibility(View.VISIBLE);
            if(isSmsSend){
                llPhoneSend.setVisibility(View.VISIBLE);
            }
            etPassword.setHint(R.string.please_input_verifycode);
        }

    }

    private void login() {
        String userName = etUsername.getText().toString().trim();
        String passWord = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            if(loginType == LoginType1){
                App.showToast(R.string.please_enter_phone_or_account);
            }else if(loginType == LoginType2){
                App.showToast(R.string.please_input_phone);
            }

            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            if (loginType == LoginType2) {
                App.showToast(R.string.please_input_verifycode);
            } else {
                App.showToast(R.string.please_input_password);
            }
            return;
        }

        String phoneType = tvPhoneType.getText().toString().trim();

        if (loginType == LoginType2) {

            LoginByPhoneModel model = new LoginByPhoneModel();
            if (phoneType.equals("+852")) {
                model.setMobile("852 " + userName);
            } else if (phoneType.equals("+853")) {
                model.setMobile("853 " + userName);
            }else {
                model.setMobile(userName);
            }
            model.setSmscode(passWord);
            model.setType("Patient");
            mPresenter.phoneLogin(model);
        } else {
            if (phoneType.equals("+852")) {
                mPresenter.login("852 " + userName, passWord);
            } else if (phoneType.equals("+853")) {
                mPresenter.login("853 " + userName, passWord);
            } else {
                mPresenter.login(userName, passWord);
            }
        }


    }

    private void setLanguage(int type) {

        tvLanguage1.setSelected(false);
        tvLanguage2.setSelected(false);
        tvLanguage3.setSelected(false);
        switch (type) {
            case LanguageType1:
                tvLanguage1.setSelected(true);
                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                break;
            case LanguageType2:
                tvLanguage2.setSelected(true);
                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE2);
                break;
            case LanguageType3:
                tvLanguage3.setSelected(true);
                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE3);
                break;
        }
        if (type != defoultSelectPos) {
            selectLanguage(type);
        }


    }

    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(Application.getInstance(), select);
//        LoginActivity.reStart(this);
        recreate();
    }

    private void getPhoneCode() {

        String phone = etUsername.getText().toString().trim();

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
                        isSmsSend = true;
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                tvGetVerificationCode.setEnabled(true);
                tvGetVerificationCode.setText(getString(R.string.label_get_code));
                tvGetVerificationCode.setTextColor(getResources().getColor(R.color.textColor2));
                isSmsSend = false;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                tvGetVerificationCode.setText(getString(R.string.label_resend) + " " + aLong + " " +getString(R.string.label_seconds));
            }
        });
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    public void loginSuccess(LoginResModel model) {
        App.getSPUtils().put(Common.SPApi.TOKEN, model.getData().getAccessToken());
        App.getSPUtils().put(Common.SPApi.ID, model.getData().getUserId());
        App.getSPUtils().put(Common.SPApi.LOGINID, model.getData().getLoginId());
        App.getSPUtils().put(Common.SPApi.USER_NAME, model.getData().getUserName());
        App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, model.getData().getHeadImg());
        showContent();
        JPushInterface.setAlias(this,0,model.getData().getLoginId());
        App.showToast(R.string.login_success);
        MainActivity.show(this);
        LogcatHelper logcatHelper = LogcatHelper.getInstance(this,model.getData().getLoginId());
        logcatHelper.stop();
        logcatHelper.start();
        finish();
    }




    @Override
    public void getPhoneCodeResModelSuccess() {
        showContent();
        App.showToast(R.string.msg_get_code_success);
        startTimer();
    }

    @Override
    public void phoneLoginSuccess(LoginResModel loginResModel) {
        App.getSPUtils().put(Common.SPApi.TOKEN, loginResModel.getData().getAccessToken());
        App.getSPUtils().put(Common.SPApi.ID, loginResModel.getData().getUserId());
        App.getSPUtils().put(Common.SPApi.LOGINID, loginResModel.getData().getLoginId());
        App.getSPUtils().put(Common.SPApi.USER_NAME, loginResModel.getData().getUserName());
        App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, loginResModel.getData().getHeadImg());
        JPushInterface.setAlias(this,0,loginResModel.getData().getLoginId());
        showContent();
        App.showToast(R.string.login_success);
        MainActivity.show(this);
        LogcatHelper logcatHelper = LogcatHelper.getInstance(this,loginResModel.getData().getLoginId());
        logcatHelper.stop();
        logcatHelper.start();
        finish();
    }


    @Override
    public void retry(int type) {

        login();
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {

        showContent();
        App.showToast(errorMsg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent classifyMsgEvent) {
        switch (classifyMsgEvent.getType()) {
            case Common.EventbusType.LOGIN_WX:
                finish();
                MainActivity.show(this);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ConfirmDialog.newInstance(getString(R.string.quit_the_application),
                    getString(R.string.cancel), getString(R.string.confirm),true)
                    .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                        @Override
                        public void onClickCancel() {
                        }

                        @Override
                        public void onClickQuery() {
                            App.getInstance().finishAll();
                        }
                    }).setMargin(50)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());

            return false;
        }
        return super.onKeyDown(keyCode, event);
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
