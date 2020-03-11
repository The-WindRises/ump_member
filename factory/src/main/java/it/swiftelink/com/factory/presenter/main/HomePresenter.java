package it.swiftelink.com.factory.presenter.main;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.appointment.ShareH5MsgResModel;
import it.swiftelink.com.factory.model.card.FreePickPackageModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.message.MessageRemindCountResModel;
import it.swiftelink.com.factory.model.mian.BinnerListResModel;
import it.swiftelink.com.factory.model.mian.MarkWordsResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void getBinderList(String language,String type) {

        start();

        Observable<BinnerListResModel> observable = NetWork.getRequest().getBinnerList(language ,type);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BinnerListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_BINNER_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BinnerListResModel resModel) {
                if(resModel.isSuccess()){
                    mView.getBinderListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_BINNER_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void getSetMealList(String language  ,int currPage ,int pageSize) {

        Observable<SetMealListResModel> observable = NetWork.getRequest().getSetMealList(currPage,pageSize,language );

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<SetMealListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_SETMEAL_LIST ,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(SetMealListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getSetMealListSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_SETMEAL_LIST ,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getMessageRemind() {

        start();

        Observable<MessageRemindCountResModel> observable = NetWork.getRequest().getRemindMessageCount();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<MessageRemindCountResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_REMIND_MESSAGE_COUNT,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(MessageRemindCountResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getMessageRemindSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_REMIND_MESSAGE_COUNT,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void getMarkedWords() {
        Observable<MarkWordsResModel> observable = NetWork.getRequest().getMarkWords();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<MarkWordsResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_MARKEDWORDS,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(MarkWordsResModel resModel) {
                if(resModel.isSuccess()){
                    mView.getMarkedWordsSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_MARKEDWORDS,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void postFreePick(String id) {
        FreePickPackageModel freePickPackageModel=new FreePickPackageModel();
        freePickPackageModel.setId(id);
        Observable<ReceiveCardResModel> observable=NetWork.getRequest().postFreePick(freePickPackageModel);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ReceiveCardResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.POST_RECEIVIE_CARD,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ReceiveCardResModel resModel) {

                    mView.postFreePickSuccess(resModel);

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

    @Override
    public void checkVersion(String device, String type) {
        Observable<VersionResModel> observable = NetWork.getRequest().checkVersion(device, type);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<VersionResModel>() {
            @Override
            public void onError(ApiException e) {
            }

            @Override
            public void onSuccess(VersionResModel versionResModel) {
                Log.i("lqi","---------检查版本--");
                EventBus.getDefault().post(versionResModel);
            }
        });
    }

    @Override
    public void getUserInfo() {
        start();

        Observable<UserInfoResModel> observable = NetWork.getRequest().getUserInfo();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<UserInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_USER_INFO, e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(UserInfoResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getUserInfoSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_USER_INFO, resModel.getCode() ,resModel.getMessage());
                }
            }
        });
    }
}
