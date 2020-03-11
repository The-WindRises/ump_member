package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class ChangePsdPresenter extends BasePresenter<ChangePsdContract.View> implements ChangePsdContract.Presenter {
    public ChangePsdPresenter(ChangePsdContract.View view) {
        super(view);
    }

    @Override
    public void changePsd(String type, String oldPsd, String newPsd) {

        start();

        Observable<ChangePsdResModel> observable = NetWork.getRequest().changePsd(type, oldPsd, newPsd);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ChangePsdResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.CHANGE_PSD,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ChangePsdResModel resModel) {

                if(resModel.isSuccess()){
                    mView.changePsdSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.CHANGE_PSD,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }
}
