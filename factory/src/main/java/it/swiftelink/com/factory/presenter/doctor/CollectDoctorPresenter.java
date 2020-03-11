package it.swiftelink.com.factory.presenter.doctor;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.doctor.CollectDoctorResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class CollectDoctorPresenter extends BasePresenter<CollectDoctorContract.View> implements CollectDoctorContract.Presenter {
    public CollectDoctorPresenter(CollectDoctorContract.View view) {
        super(view);
    }

    @Override
    public void getCollectDoctor( int currPage,  int pageSize) {
        start();

        Observable<CollectDoctorResModel> observable = NetWork.getRequest().getCollectDoctorList(currPage,pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<CollectDoctorResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_COLLECT_DOCTOR_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(CollectDoctorResModel resModel) {

                if(resModel.isSuccess()){

                    mView.getCollectDoctorSuccess(resModel);

                }else {
                    mView.showError(Common.UrlApi.GET_COLLECT_DOCTOR_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
