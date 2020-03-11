package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.health.EditHealthDataModel;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HealthDataPresenter extends BasePresenter<HealthDataContract.View> implements HealthDataContract.Presenter {
    public HealthDataPresenter(HealthDataContract.View view) {
        super(view);
    }


    @Override
    public void editHealthData(String id, EditHealthDataModel model) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().editVitalSigns(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.EDIT_HEALTH_DATA,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.editHealthDataSuccess();
                } else {
                    mView.showError(Common.UrlApi.EDIT_HEALTH_DATA,resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }


}
