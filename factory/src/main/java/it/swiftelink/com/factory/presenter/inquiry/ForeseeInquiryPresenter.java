package it.swiftelink.com.factory.presenter.inquiry;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.appointment.ShareH5MsgResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class ForeseeInquiryPresenter extends BasePresenter<ForeseeInquiryContract.View> implements ForeseeInquiryContract.Presenter {
    public ForeseeInquiryPresenter(ForeseeInquiryContract.View view) {
        super(view);
    }

//    @Override
//    public void getIsMember() {
//
//
//        Observable<BooleanResModel> observable = NetWork.getRequest().getIsMember();
//
//        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BooleanResModel>() {
//            @Override
//            public void onError(ApiException e) {
//                mView.showError(Common.UrlApi.GET_ISMEMBER, e.getCode(), e.getMessage());
//            }
//
//            @Override
//            public void onSuccess(BooleanResModel resModel) {
//
//                if (resModel.isSuccess()) {
//                    mView.getIsMemberSuccess(resModel);
//                } else {
//                    mView.showError(Common.UrlApi.GET_ISMEMBER, resModel.getCode(), resModel.getMessage());
//                }
//
//            }
//        });
//
//    }

    @Override
    public void getForeseeInquiryImage(String language) {

        start();

        Observable<ForeseeInquiryImageResModel> observable = NetWork.getRequest().getForeseeInquiryImage(language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ForeseeInquiryImageResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_FORESEEINQUIRY_IMAGE, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(ForeseeInquiryImageResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getForeseeInquiryImageSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_FORESEEINQUIRY_IMAGE, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void saveEvaluate(SaveEvaluateModel model) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().saveEvaluate(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.SAVE_EVALUATE, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.saveEvaluateSuccess();
                } else {
                    mView.showError(Common.UrlApi.SAVE_EVALUATE, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getEvaluateLabel(EvaluateLabelModel model) {

        start();

        Observable<EvaluateLabelResModel> observable = NetWork.getRequest().getEvaluateLabel(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EvaluateLabelResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EVALUATION_LABEL, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(EvaluateLabelResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getEvaluateLabelSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_EVALUATION_LABEL, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getShareH5Msg(String type, String language) {

        Observable<ShareH5MsgResModel> observable = NetWork.getRequest().getShareH5(type, language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ShareH5MsgResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_SHARE_H5, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(ShareH5MsgResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getShareH5MsgSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_SHARE_H5,resModel.getCode(), resModel.getMessage());
                }

            }
        });
    }

    @Override
    public void getInquiryValidPackageCard() {
        Observable<InquiryValidCardResModel> observable=NetWork.getRequest().getInquiryValidCard();
        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<InquiryValidCardResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_INQUIRYVALID_CARD, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(InquiryValidCardResModel inquiryValidCardResModel) {

                mView.getInquiryValidPackageCardSuccess(inquiryValidCardResModel);
            }
        });
    }


}
