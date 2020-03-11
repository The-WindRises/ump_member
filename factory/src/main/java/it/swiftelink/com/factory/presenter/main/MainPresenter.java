package it.swiftelink.com.factory.presenter.main;

import android.util.Log;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class MainPresenter extends BasePresenter<MainCotract.View> implements MainCotract.Presenter {
    public MainPresenter(MainCotract.View view) {
        super(view);
    }

//    @Override
//    public void getIsMember() {
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
    public void saveEvaluate(SaveEvaluateModel model) {

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
    public void checkVersion(String device, String type) {
        Observable<VersionResModel> observable = NetWork.getRequest().checkVersion(device, type);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<VersionResModel>() {
            @Override
            public void onError(ApiException e) {
            }

            @Override
            public void onSuccess(VersionResModel versionResModel) {
                    mView.checkVersionSuccess(versionResModel);
            }
        });
    }

    @Override
    public void getTrtc(String userId) {

        Observable<TrtcConfigResModel> observable = NetWork.getRequest().getTrtcConfig("u12a3213", userId);

        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<TrtcConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_TRTC_CONFIG, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(TrtcConfigResModel resModel) {
                if (resModel.isSuccess()) {

                    mView.getTrtcSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_TRTC_CONFIG, 200, resModel.getMessage());
                }
            }
        });
    }


    /**
     * 校验用户套餐卡
     */
    @Override
    public void getInquiryValidPackageCard() {
        start();
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

    @Override
    public void loginTest() {
        LoginModel loginModel = new LoginModel();
        loginModel.setMobile("13265898489");
        loginModel.setPassword("123456");
        Observable<LoginResModel> observable = NetWork.getRequest().login(loginModel);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<LoginResModel>() {
            @Override
            public void onError(ApiException e) {


            }

            @Override
            public void onSuccess(LoginResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.loginSuccess(resModel);

                }

            }
        });
    }
}
