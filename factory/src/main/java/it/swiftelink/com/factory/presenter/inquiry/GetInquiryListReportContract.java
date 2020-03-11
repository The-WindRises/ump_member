package it.swiftelink.com.factory.presenter.inquiry;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.inquiry.InquiryListResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryReportListResModel;

public class GetInquiryListReportContract {

    public interface View extends BaseContract.View<Presenter> {

        void getInquiryReportListSuccess(InquiryReportListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getInquiryReportList(int pageSize, int currPage);

    }
}
