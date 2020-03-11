package it.swiftelink.com.factory.presenter.main;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.message.MessageRemindCountResModel;
import it.swiftelink.com.factory.model.mian.BinnerListResModel;
import it.swiftelink.com.factory.model.mian.MarkWordsResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;

public class HomeContract {

    public interface View extends BaseContract.View<HomeContract.Presenter> {

        void getBinderListSuccess(BinnerListResModel model);

        void getSetMealListSuccess(SetMealListResModel model);

        void getMessageRemindSuccess(MessageRemindCountResModel model);

        void getMarkedWordsSuccess(MarkWordsResModel resModel);

        void postFreePickSuccess(ReceiveCardResModel resModel);

        void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel);

        void getUserInfoSuccess(UserInfoResModel model);
    }

    public interface Presenter extends BaseContract.Presenter {
        void getBinderList(String language,String type);

        void getSetMealList(String language , int  currPage ,int pageSize);

        void getMessageRemind();

        void getMarkedWords();

        void postFreePick(String id);

        void getInquiryValidPackageCard();

        void checkVersion(String device, String type);

        void getUserInfo();
    }
}
