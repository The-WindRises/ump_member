package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.inquiry.InquiryReportListResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;

public class EvaluateListContract {

    public interface View extends BaseContract.View<Presenter> {

        void getEvaluateListSuccess(EvaluateListResModel model);

        void toEvaluateSuccess();

        void getEvaluateLabelSuccess(EvaluateLabelResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getEvaluateList(int currPage, int pageSize, String patientStatus ,String doctorId);

        void toEvaluate(EvaluateModel model);

        void getEvaluateLabel(EvaluateLabelModel model );
    }
}
