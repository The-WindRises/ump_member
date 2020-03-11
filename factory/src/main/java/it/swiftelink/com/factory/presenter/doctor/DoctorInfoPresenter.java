package it.swiftelink.com.factory.presenter.doctor;

import org.greenrobot.eventbus.EventBus;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.event.CancleFavoriteDoctorEvent;
import it.swiftelink.com.factory.event.FavoriteDoctorEvent;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class DoctorInfoPresenter extends BasePresenter<DoctorInfoContract.View> implements DoctorInfoContract.Presenter {
    public DoctorInfoPresenter(DoctorInfoContract.View view) {
        super(view);
    }

    @Override
    public void getDoctorInfo(String doctorId) {


        Observable<DoctorInfoResModel> observable = NetWork.getRequest().getDoctorInfo(doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<DoctorInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_DOCTOR_INFO,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(DoctorInfoResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getDoctorInfoSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_DOCTOR_INFO,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void collectDoctor(String doctorId) {


        Observable<BaseResModel> observable = NetWork.getRequest().collectDoctor(doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.COLLECT_DOCTOR,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    FavoriteDoctorEvent favoriteDoctorEvent=new FavoriteDoctorEvent();
                    favoriteDoctorEvent.setSuccess(resModel.isSuccess());
                    EventBus.getDefault().post(favoriteDoctorEvent);
                    mView.collectDoctorSuccess();
                }else{
                    mView.showError(Common.UrlApi.COLLECT_DOCTOR,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void cancelCollectDoctor(String doctorId) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().cancelCollectDoctor(doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CANCEL_COLLECT_DOCTOR,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    CancleFavoriteDoctorEvent cancleFavoriteDoctorEvent=new CancleFavoriteDoctorEvent();
                    cancleFavoriteDoctorEvent.setSuccess(resModel.isSuccess());
                    EventBus.getDefault().post(cancleFavoriteDoctorEvent);
                    mView.cancelCollectDSuccess();
                }else{
                    mView.showError(Common.UrlApi.CANCEL_COLLECT_DOCTOR,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getDoctorEvaluate(int currPage, int pageSize, String patientStatus, String doctorId) {

        start();

        Observable<EvaluateListResModel> observable = NetWork.getRequest().getEvaluateList(currPage,pageSize,"",patientStatus,doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EvaluateListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EVALUATION_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(EvaluateListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getDoctorEvaluateSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_EVALUATION_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
