package it.swiftelink.com.factory.presenter.main;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;

public class MainCotract {

    public interface View extends BaseContract.View<MainCotract.Presenter> {

        void checkVersionSuccess(VersionResModel versionResModel);

//        void getIsMemberSuccess(BooleanResModel model);
        void loginSuccess(LoginResModel resModel);

        void saveEvaluateSuccess();

        void getEvaluateLabelSuccess(EvaluateLabelResModel model);

        void getTrtcSuccess(TrtcConfigResModel resModel);

        void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel);

    }
    public interface Presenter extends BaseContract.Presenter {

        void saveEvaluate(SaveEvaluateModel model);

        void getEvaluateLabel(EvaluateLabelModel model);

        void checkVersion(String device, String type);

        void getTrtc(String userId);

        void getInquiryValidPackageCard();

        void loginTest();

    }
}
