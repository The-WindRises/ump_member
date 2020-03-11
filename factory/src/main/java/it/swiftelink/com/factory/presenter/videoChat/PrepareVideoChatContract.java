package it.swiftelink.com.factory.presenter.videoChat;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.model.videoChat.VideoChatConfigResModel;

public class PrepareVideoChatContract {

    public interface View extends BaseContract.View<PrepareVideoChatContract.Presenter> {

        void editInquirySuccess(EditInquiryResModel model);

        void getTrtcConfigSuccess(TrtcConfigResModel model);

        void getVideoChatConfigSuccess(VideoChatConfigResModel model);

        void cancelInquirySuccess();
    }

    public interface Presenter extends BaseContract.Presenter {

        void editInquiry(EditInquiryModel model);

        void getTrtcConfig(String uuid,String userId);

        void getVideoChatConfig();

        void cancelInquiry(String diagnosisId ,String status);
    }
}
