package it.swiftelink.com.factory.presenter.inquiry;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryListResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;

public class GetInquiryListContract {

    public interface View extends BaseContract.View<Presenter> {

        void getInquiryListSuccess(InquiryListResModel model);

        void getInquiryListByTimeSuccess(InquiryListResModel model);

        void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getInquiryList(String status, int pageSize, int currPage);

        void getInquiryListByTime(String status,int pageSize,int currPage,String year,String month);
        void getInquiryValidPackageCard();

    }
}
