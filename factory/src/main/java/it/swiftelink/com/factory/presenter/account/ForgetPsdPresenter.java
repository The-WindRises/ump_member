package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.RegisterModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class ForgetPsdPresenter extends BasePresenter<ForgetPsdContract.View> implements ForgetPsdContract.Presenter {
    public ForgetPsdPresenter(ForgetPsdContract.View view) {
        super(view);
    }

    @Override
    public void changePsd(RegisterModel model) {

        start();

        Observable<ChangePsdResModel> observable = NetWork.getRequest().forgetPsd(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ChangePsdResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.FORGET_PSD, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ChangePsdResModel resModel) {

                if(resModel.isSuccess()){
                    mView.changePsdSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.FORGET_PSD,resModel.getCode(),resModel.getMessage());
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
                mView.showError(Common.UrlApi.GET_PHONE_CODE, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPhoneCodeResModelSuccess();
                } else {
                    mView.showError(Common.UrlApi.GET_PHONE_CODE, resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
