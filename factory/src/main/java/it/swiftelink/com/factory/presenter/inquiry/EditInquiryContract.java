package it.swiftelink.com.factory.presenter.inquiry;


import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.doctor.DiagnosisDoctorResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryTagResModel;
import it.swiftelink.com.factory.model.inquiry.ToInquiryResModel;
import it.swiftelink.com.factory.model.order.IsHKResModel;

/**
 * Created by Administrator on 2018/8/20.
 */

public class EditInquiryContract extends BaseContract {

    public interface View extends BaseContract.View<Presenter> {

        void toInquirySuccess(ToInquiryResModel model);

        void editInquirySuccess(EditInquiryResModel model);


        void getEditInquirySuccess(EditInquiryTagResModel model);

        void getCollectDoctorSuccess(DiagnosisDoctorResModel diagnosisDoctorResModel);

        void getIsHK(IsHKResModel isHKResModel);
    }

    public interface Presenter extends BaseContract.Presenter {
        void toInquiry();

        void editInquiry(EditInquiryModel model);

        void getEditInquiryTag(int page ,int pageSize);

        void getCollectDoctor(int pageSize,int curPage);

        void getIsHK(String region);
    }
}
