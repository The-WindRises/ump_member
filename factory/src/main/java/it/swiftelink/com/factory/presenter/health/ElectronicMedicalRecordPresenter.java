package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.health.RecordListResmodel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class ElectronicMedicalRecordPresenter extends BasePresenter<ElectronicMedicalRecordContract.View> implements ElectronicMedicalRecordContract.Presenter {
    public ElectronicMedicalRecordPresenter(ElectronicMedicalRecordContract.View view) {
        super(view);
    }

    @Override
    public void getRecordList(int currPage) {

        Observable<RecordListResmodel> observable = NetWork.getRequest().getRecordList(5,currPage);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RecordListResmodel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_RECORD_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(RecordListResmodel resmodel) {

                if(resmodel.isSuccess()){
                    mView.getRecordListSuccess(resmodel);
                }else{
                    mView.showError(Common.UrlApi.GET_RECORD_LIST,resmodel.getCode(),resmodel.getMessage());
                }
            }
        });

    }

    @Override
    public void getUserInfo() {

        Observable<UserInfoResModel> observable = NetWork.getRequest().getUserInfo();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<UserInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_USER_INFO,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(UserInfoResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getUserInfoSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_USER_INFO,resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }
}
