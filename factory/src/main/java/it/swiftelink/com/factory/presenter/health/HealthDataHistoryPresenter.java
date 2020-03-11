package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HealthDataHistoryPresenter extends BasePresenter<HealthDataHistoryContract.View> implements HealthDataHistoryContract.Presenter {
    public HealthDataHistoryPresenter(HealthDataHistoryContract.View view) {
        super(view);
    }

    @Override
    public void getHealthData(int currPage, int pageSize) {

        if (currPage == 1) {
            start();
        }

        Observable<HistoryHealthDataListResModel> observable = NetWork.getRequest().getHealthHistoryDataList(currPage, pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<HistoryHealthDataListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_HEALTH_HISTORY_DATA,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(HistoryHealthDataListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getHealthDataSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_HEALTH_HISTORY_DATA,resModel.getCode(), resModel.getMessage());
                }
            }
        });

    }
}
