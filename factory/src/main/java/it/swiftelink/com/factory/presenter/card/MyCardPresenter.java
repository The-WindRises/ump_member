package it.swiftelink.com.factory.presenter.card;


import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;

import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.card.CardListResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/27 13:54
 */
public class MyCardPresenter  extends BasePresenter<MyCardContract.View> implements MyCardContract.Presenter {

    public MyCardPresenter(MyCardContract.View view) {
        super(view);
    }

    @Override
    public void getMyCardList(int currPage,int pageSize,String validFlag) {
        Observable<CardListResModel> observable = NetWork.getRequest().getMyPackageCard(currPage,pageSize,validFlag);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<CardListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_MY_PKCKAGECARD ,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(CardListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getMyCardListSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_MY_PKCKAGECARD ,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }


}
