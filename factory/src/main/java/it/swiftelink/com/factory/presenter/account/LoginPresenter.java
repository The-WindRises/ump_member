package it.swiftelink.com.factory.presenter.account;


import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;


/**
 * Created by Administrator on 2018/8/21.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {


    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(final String userName, final String password) {

        start();//默认开启进度条

        final LoginModel loginModel = new LoginModel();

        loginModel.setMobile(userName);
        loginModel.setPassword(password);

        Observable<LoginResModel> observable = NetWork.getRequest().login(loginModel);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<LoginResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_LOGIN, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(final LoginResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.loginSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_LOGIN, resModel.getCode(), resModel.getMessage());

                }

            }
        });

    }

    @Override
    public void getPhoneCode(GetPhoneCodeModel model) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().getPhoneCode(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_PHONE_CODE, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPhoneCodeResModelSuccess();
                } else {
                    mView.showError(Common.UrlApi.GET_PHONE_CODE, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void phoneLogin(LoginByPhoneModel model) {

        start();

        Observable<LoginResModel> observable = NetWork.getRequest().loginByPhone(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<LoginResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.LOGIN_BY_PHONE, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(LoginResModel resModel) {
                if (resModel.isSuccess()) {
                    mView.phoneLoginSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.LOGIN_BY_PHONE, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

}
