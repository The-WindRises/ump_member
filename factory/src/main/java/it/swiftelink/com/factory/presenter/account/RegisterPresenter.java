package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.AgrreementResModel;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.RegisterModel;
import it.swiftelink.com.factory.model.account.RegisterResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class RegisterPresenter extends BasePresenter<RegisterConract.View> implements RegisterConract.Presenter {
    public RegisterPresenter(RegisterConract.View view) {
        super(view);
    }

    @Override
    public void getPhoneCode(GetPhoneCodeModel model) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().getPhoneCode(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_PHONE_CODE,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPhoneCodeResModelSuccess();
                } else {
                    mView.showError(Common.UrlApi.GET_PHONE_CODE,resModel.getCode(), resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void register(RegisterModel registerModel) {

        start();

        Observable<RegisterResModel> observable = NetWork.getRequest().register(registerModel);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RegisterResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.POST_REGISTER,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(RegisterResModel resModel) {

                if(resModel.isSuccess()){
                    mView.registerSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.POST_REGISTER,resModel.getCode(),resModel.getMessage());
                }

            }
        });

    }

    @Override
    public void getAgrreement(String language, String type) {
        start();
        Observable<AgrreementResModel> observable = NetWork.getRequest().getAgrreement(language, type);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<AgrreementResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_AGEERRMENT,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(AgrreementResModel agrreementResModel) {

                if (agrreementResModel.isSuccess()) {

                    mView.getAgrreementSuess(agrreementResModel);
                } else {
                    mView.showError(Common.UrlApi.GET_AGEERRMENT,agrreementResModel.getCode() , agrreementResModel.getMessage());

                }


            }
        });

    }
}
