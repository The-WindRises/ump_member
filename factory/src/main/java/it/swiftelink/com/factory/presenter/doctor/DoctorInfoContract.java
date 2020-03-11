package it.swiftelink.com.factory.presenter.doctor;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;

public class DoctorInfoContract  {

    public interface View extends BaseContract.View<DoctorInfoContract.Presenter> {


        void getDoctorInfoSuccess(DoctorInfoResModel model);

        void collectDoctorSuccess();

        void cancelCollectDSuccess();

        void getDoctorEvaluateSuccess(EvaluateListResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {

        void getDoctorInfo(String doctorId);

        void collectDoctor(String doctorId);

        void cancelCollectDoctor(String doctorId);

        void getDoctorEvaluate(int currPage, int pageSize, String patientStatus ,String doctorId);

    }
}
