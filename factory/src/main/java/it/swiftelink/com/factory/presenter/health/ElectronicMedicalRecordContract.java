package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.health.RecordListResmodel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;

public class ElectronicMedicalRecordContract {

    public interface View extends BaseContract.View<ElectronicMedicalRecordContract.Presenter> {

        void getRecordListSuccess(RecordListResmodel resmodel);

        void getUserInfoSuccess(UserInfoResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getRecordList(int currPage);
        void getUserInfo();

    }
}
