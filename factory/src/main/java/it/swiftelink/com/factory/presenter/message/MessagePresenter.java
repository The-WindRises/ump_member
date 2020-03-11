package it.swiftelink.com.factory.presenter.message;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.message.MessageListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {
    public MessagePresenter(MessageContract.View view) {
        super(view);
    }

    @Override
    public void getMessageList(int currPage, int pageSize) {
        if (currPage == 1) {
            start();
        }
        Observable<MessageListResModel> observable = NetWork.getRequest().getMessageList(currPage, pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<MessageListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_MSG_LIST, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(MessageListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getMessageListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_MSG_LIST,resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void readMessage(String id) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().readMessage(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.MSG_READ,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.readMessageSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.MSG_READ,resModel.getCode(), resModel.getMessage());
                }
            }
        });

    }
}
