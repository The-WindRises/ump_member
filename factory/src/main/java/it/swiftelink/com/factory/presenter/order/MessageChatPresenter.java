package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class MessageChatPresenter extends BasePresenter<MessageChatContract.View> implements MessageChatContract.Presenter {
    public MessageChatPresenter(MessageChatContract.View view) {
        super(view);
    }

    @Override
    public void endVideoChat(String diagnosisId , boolean isPassive) {
        Observable<BaseResModel> observable = NetWork.getRequest().endVideoInquiry(diagnosisId,isPassive);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.END_INQUIRY,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.endVideoChatSuccess();
                }else{
                    mView.showError(Common.UrlApi.END_INQUIRY,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

}
