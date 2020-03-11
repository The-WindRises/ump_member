package it.swiftelink.com.factory.presenter.videoChat;

import org.greenrobot.eventbus.EventBus;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.event.CancleFavoriteDoctorEvent;
import it.swiftelink.com.factory.event.FavoriteDoctorEvent;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class VideoChatPresenter extends BasePresenter<VideoChatContract.View> implements VideoChatContract.Presenter {
    public VideoChatPresenter(VideoChatContract.View view) {
        super(view);
    }

    @Override
    public void endVideoChat(String diagnosisId, boolean isPassive) {

        Observable<BaseResModel> observable = NetWork.getRequest().endVideoInquiry(diagnosisId, isPassive);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.END_INQUIRY, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(null!=resModel) {
                    FavoriteDoctorEvent favoriteDoctorEvent = new FavoriteDoctorEvent();
                    favoriteDoctorEvent.setSuccess(resModel.isSuccess());
                    favoriteDoctorEvent.setMessage(resModel.getMessage());
                    EventBus.getDefault().post(favoriteDoctorEvent);
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
                if(null!=resModel) {
                    FavoriteDoctorEvent favoriteDoctorEvent = new FavoriteDoctorEvent();
                    favoriteDoctorEvent.setSuccess(resModel.isSuccess());
                    favoriteDoctorEvent.setMessage(resModel.getMessage());
                    EventBus.getDefault().post(favoriteDoctorEvent);
                }

            }
        });

    }

    @Override
    public void cancelCollectDoctor(String doctorId) {

        Observable<BaseResModel> observable = NetWork.getRequest().cancelCollectDoctor(doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CANCEL_COLLECT_DOCTOR,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {
                if(null!=resModel) {
                    CancleFavoriteDoctorEvent cancleFavoriteDoctorEvent = new CancleFavoriteDoctorEvent();
                    cancleFavoriteDoctorEvent.setSuccess(resModel.isSuccess());
                    cancleFavoriteDoctorEvent.setMessage(resModel.getMessage());
                    EventBus.getDefault().post(cancleFavoriteDoctorEvent);
                }
//                if(resModel.isSuccess()){
////                    mView.cancelCollectDSuccess();
//
//                }else{
//                    mView.showError(Common.UrlApi.CANCEL_COLLECT_DOCTOR,resModel.getCode(),resModel.getMessage());
//                }
            }
        });
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

}
