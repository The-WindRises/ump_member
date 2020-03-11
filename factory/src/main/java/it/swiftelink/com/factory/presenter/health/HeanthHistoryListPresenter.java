package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.health.HealthHistoryListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HeanthHistoryListPresenter extends BasePresenter<HealthHistoryListContract.View> implements HealthHistoryListContract.Presenter {
    public HeanthHistoryListPresenter(HealthHistoryListContract.View view) {
        super(view);
    }

    @Override
    public void getHealthHistoryList(String type) {

        start();

        Observable<HealthHistoryListResModel> observable = NetWork.getRequest().getHealthHistoryList(type);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<HealthHistoryListResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_HEALTH_HISTORY_LIST,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(HealthHistoryListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getHealthHistoryListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_HEALTH_HISTORY_LIST, resModel.getCode(),resModel.getMessage());
                }

            }
        });

    }
}
