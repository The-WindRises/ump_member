package it.swiftelink.com.factory.presenter.message;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.factory.model.message.MessageListResModel;

public class MessageContract {

    public interface View extends BaseContract.View<MessageContract.Presenter> {

        void getMessageListSuccess(MessageListResModel model);

        void readMessageSuccess(BaseResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getMessageList(int currPage,int pageSize);

        void readMessage(String id);


    }
}
