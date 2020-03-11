package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.SaveAddressModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;

public class MessageChatContract {

    public interface View extends BaseContract.View<Presenter> {

        void endVideoChatSuccess();
    }

    public interface Presenter extends BaseContract.Presenter {

        void endVideoChat(String diagnosisId , boolean isPassive);


    }
}
