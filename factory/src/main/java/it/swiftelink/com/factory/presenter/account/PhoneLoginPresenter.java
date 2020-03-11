package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/12 11:01
 */
public class PhoneLoginPresenter extends BasePresenter<PhoneLoginContract.View> implements PhoneLoginContract.Presenter {
    public PhoneLoginPresenter(PhoneLoginContract.View view) {
        super(view);
    }


    @Override
    public void getPhoneCode(GetPhoneCodeModel model) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().getPhoneCode(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_PHONE_CODE, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPhoneCodeResModelSuccess();
                } else {
                    mView.showError(Common.UrlApi.GET_PHONE_CODE,resModel.getCode() ,resModel.getMessage());
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
                mView.showError(Common.UrlApi.LOGIN_BY_PHONE, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(LoginResModel resModel) {
                if (resModel.isSuccess()) {
                    mView.phoneLoginSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.LOGIN_BY_PHONE,resModel.getCode() ,resModel.getMessage());
                }
            }
        });
    }
}


