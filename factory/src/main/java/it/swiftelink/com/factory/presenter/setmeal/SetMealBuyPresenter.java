package it.swiftelink.com.factory.presenter.setmeal;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class SetMealBuyPresenter extends BasePresenter<SetMealBuyContract.View> implements SetMealBuyContract.Presenter {
    public SetMealBuyPresenter(SetMealBuyContract.View view) {
        super(view);
    }

    @Override
    public void getSetMealList(String language, int currPage, int pageSize) {
        start();

        Observable<SetMealListResModel> observable = NetWork.getRequest().getSetMealList(currPage,pageSize,language );

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<SetMealListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_SETMEAL_LIST ,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(SetMealListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getSetMealListSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_SETMEAL_LIST ,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getForeseeInquiryImage(String language) {

        start();


        Observable<ForeseeInquiryImageResModel> observable = NetWork.getRequest().getForeseeInquiryImage(language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ForeseeInquiryImageResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_FORESEEINQUIRY_IMAGE,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(ForeseeInquiryImageResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getForeseeInquiryImageSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_FORESEEINQUIRY_IMAGE,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }
}
