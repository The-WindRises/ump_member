package it.swiftelink.com.factory.presenter.main;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.mian.HelpListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HelpPresenter extends BasePresenter<HelpContract.View> implements HelpContract.Presenter {
    public HelpPresenter(HelpContract.View view) {
        super(view);
    }

    @Override
    public void getHelpList(String language) {
        start();


        Observable<HelpListResModel> observable = NetWork.getRequest().getHelpList( language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<HelpListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_HELP_LIST,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(HelpListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getHelpListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_HELP_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
