package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.order.LogisticsResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class LogisticsPresenter extends BasePresenter<LogisticsContract.View> implements LogisticsContract.Presenter {
    public LogisticsPresenter(LogisticsContract.View view) {
        super(view);
    }

    @Override
    public void getLogistics(String orderId) {
        start();

        Observable<LogisticsResModel> observable = NetWork.getRequest().getLogistics(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<LogisticsResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_LOGISTICS, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(LogisticsResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getLogisticsSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_LOGISTICS, resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
