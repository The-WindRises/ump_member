package it.swiftelink.com.factory.presenter.videoChat;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;

public class VideoChatContract {

    public interface View extends BaseContract.View<VideoChatContract.Presenter> {

        void endVideoChatSuccess();



        void getDoctorInfoSuccess(DoctorInfoResModel model);
    }

    public interface Presenter extends BaseContract.Presenter {
        void endVideoChat(String diagnosisId, boolean isPassive);
        void collectDoctor(String doctorId);
        void cancelCollectDoctor(String doctorId);
        void getDoctorInfo(String doctorId);

    }
}
