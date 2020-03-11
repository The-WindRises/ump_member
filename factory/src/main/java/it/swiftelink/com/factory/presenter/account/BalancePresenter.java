package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.BalanceListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class BalancePresenter extends BasePresenter<BalanceContract.View> implements BalanceContract.Presenter {
    public BalancePresenter(BalanceContract.View view) {
        super(view);
    }

    @Override
    public void getBalanceList(int currentPage, int pageSize) {

        if (currentPage == 1) {
            start();
        }

        Observable<BalanceListResModel> observable = NetWork.getRequest().getBalanceList(currentPage, pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BalanceListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_BALANCE_LIST, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BalanceListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getBalanceListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_BALANCE_LIST,resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }
}
