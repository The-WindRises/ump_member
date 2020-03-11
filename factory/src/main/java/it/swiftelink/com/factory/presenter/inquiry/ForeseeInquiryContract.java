package it.swiftelink.com.factory.presenter.inquiry;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.appointment.ShareH5MsgResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;

public class ForeseeInquiryContract {

    public interface View extends BaseContract.View<ForeseeInquiryContract.Presenter> {

//        void getIsMemberSuccess(BooleanResModel model);

        void getForeseeInquiryImageSuccess(ForeseeInquiryImageResModel resModel);

        void saveEvaluateSuccess();
        void getEvaluateLabelSuccess(EvaluateLabelResModel model);

        void getShareH5MsgSuccess(ShareH5MsgResModel resModel);

        void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel);


    }

    public interface Presenter extends BaseContract.Presenter {
//        void getIsMember();


        void getForeseeInquiryImage(String language);

        void saveEvaluate(SaveEvaluateModel model);

        void getEvaluateLabel(EvaluateLabelModel model);

        void getShareH5Msg(String type ,String language);

        void getInquiryValidPackageCard();



    }
}
