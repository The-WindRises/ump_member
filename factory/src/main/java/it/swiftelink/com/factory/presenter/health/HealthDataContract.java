package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.health.EditHealthDataModel;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;

public class HealthDataContract {

    public interface View extends BaseContract.View<HealthDataContract.Presenter> {

        void editHealthDataSuccess();

    }

    public interface Presenter extends BaseContract.Presenter {
        void editHealthData(String id , EditHealthDataModel model);

    }
}
