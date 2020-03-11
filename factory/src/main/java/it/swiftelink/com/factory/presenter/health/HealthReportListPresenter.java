package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.health.HealthReportListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HealthReportListPresenter extends BasePresenter<HealthReportListContract.View> implements HealthReportListContract.Presenter {
    public HealthReportListPresenter(HealthReportListContract.View view) {
        super(view);
    }

    @Override
    public void getHealthReportList(String type) {

        start();

        Observable<HealthReportListResModel> observable = NetWork.getRequest().getHealthReportList(type,"");

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<HealthReportListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_HEALTH_REPORT_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(HealthReportListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getHealthReportListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_HEALTH_REPORT_LIST,resModel.getCode(),resModel.getMessage());
                }

            }
        });
    }
}
