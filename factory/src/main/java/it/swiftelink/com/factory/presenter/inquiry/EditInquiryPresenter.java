package it.swiftelink.com.factory.presenter.inquiry;


import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.doctor.DiagnosisDoctorResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryTagResModel;
import it.swiftelink.com.factory.model.inquiry.ToInquiryResModel;
import it.swiftelink.com.factory.model.order.IsHKResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;


/**
 * Created by Administrator on 2018/8/21.
 */

public class EditInquiryPresenter extends BasePresenter<EditInquiryContract.View>
        implements EditInquiryContract.Presenter {


    public EditInquiryPresenter(EditInquiryContract.View view) {
        super(view);
    }


    @Override
    public void toInquiry() {
        Observable<ToInquiryResModel> observable = NetWork.getRequest().ToInquiry();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ToInquiryResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.TO_INQUIRY,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ToInquiryResModel resModel) {
                if(resModel.isSuccess()){
                    mView.toInquirySuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.TO_INQUIRY,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void editInquiry(EditInquiryModel model) {

        start();//默认开启进度条


        Observable<EditInquiryResModel> observable = NetWork.getRequest().EditInquiry(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EditInquiryResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.EDIT_INQUIRY,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(EditInquiryResModel resModel) {
                if (resModel.isSuccess()) {
                    mView.editInquirySuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.EDIT_INQUIRY, resModel.getCode(),resModel.getMessage());

                }
            }
        });

    }

    @Override
    public void getEditInquiryTag(int page ,int pageSize) {

        String slectLaguage = Application.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE);

        Observable<EditInquiryTagResModel> observable = NetWork.getRequest().getEditInquiryTag(page,pageSize,slectLaguage);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EditInquiryTagResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EDIT_INQUIRY_TAG,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(EditInquiryTagResModel model) {
                if (model.isSuccess()) {
                    mView.getEditInquirySuccess(model);
                } else {
                    mView.showError(Common.UrlApi.GET_EDIT_INQUIRY_TAG, model.getCode(),model.getMessage());

                }
            }
        });
    }

    @Override
    public void getCollectDoctor(int pageSize, int curPage) {
        Observable<DiagnosisDoctorResModel> observable = NetWork.getRequest().getDiagnosisDoctorList(pageSize,curPage);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<DiagnosisDoctorResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EDIT_INQUIRY_TAG,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(DiagnosisDoctorResModel model) {
                if (model.isSuccess()) {
                    mView.getCollectDoctorSuccess(model);
                } else {
                    mView.showError(Common.UrlApi.GET_EDIT_INQUIRY_TAG, model.getCode(),model.getMessage());
                }
            }
        });
    }

    @Override
    public void getIsHK(String region) {
        Observable<IsHKResModel> observable = NetWork.getRequest().getIshk(region);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<IsHKResModel>() {
            @Override
            public void onError(ApiException e) {

            }

            @Override
            public void onSuccess(IsHKResModel model) {
                mView.getIsHK(model);
            }
        });
    }
}
