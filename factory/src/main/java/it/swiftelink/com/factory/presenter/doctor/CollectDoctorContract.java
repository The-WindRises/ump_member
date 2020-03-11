package it.swiftelink.com.factory.presenter.doctor;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.doctor.CollectDoctorResModel;

public class CollectDoctorContract {

    public interface View extends BaseContract.View<CollectDoctorContract.Presenter> {


        void getCollectDoctorSuccess(CollectDoctorResModel model);



    }

    public interface Presenter extends BaseContract.Presenter {

        void getCollectDoctor( int currPage,  int pageSize);


    }
}
