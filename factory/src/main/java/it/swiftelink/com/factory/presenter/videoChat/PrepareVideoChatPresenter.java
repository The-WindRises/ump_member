package it.swiftelink.com.factory.presenter.videoChat;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.model.videoChat.VideoChatConfigResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class PrepareVideoChatPresenter extends BasePresenter<PrepareVideoChatContract.View> implements PrepareVideoChatContract.Presenter {
    public PrepareVideoChatPresenter(PrepareVideoChatContract.View view) {
        super(view);
    }

    @Override
    public void editInquiry(EditInquiryModel model) {

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
    public void getTrtcConfig(String uuid, String userId) {

        Observable<TrtcConfigResModel> observable = NetWork.getRequest().getTrtcConfig(uuid, userId);

        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<TrtcConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_TRTC_CONFIG,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(TrtcConfigResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getTrtcConfigSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_TRTC_CONFIG,200,resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getVideoChatConfig() {


        Observable<VideoChatConfigResModel> observable = NetWork.getRequest().getVideoChatConfig();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<VideoChatConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_VIDEO_CHAT_CONFIG,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(VideoChatConfigResModel model) {

                if(model.isSuccess()&&model.getData()!=null){
                    mView.getVideoChatConfigSuccess(model);
                }else{
                    mView.showError(Common.UrlApi.GET_VIDEO_CHAT_CONFIG,model.getCode(),model.getMessage());
                }
            }
        });
    }

    @Override
    public void cancelInquiry(String diagnosisId ,String status) {


        Observable<BaseResModel> observable = NetWork.getRequest().cancelInquiry(diagnosisId ,status);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CANCEL_INQUIRY,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){

                        mView.cancelInquirySuccess();

                }else {
                    mView.showError(Common.UrlApi.CANCEL_INQUIRY,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }


}
