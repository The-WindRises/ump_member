package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.health.HealthHistoryListResModel;

public class HealthHistoryListContract {

    public interface View extends BaseContract.View<HealthHistoryListContract.Presenter> {

        void getHealthHistoryListSuccess(HealthHistoryListResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getHealthHistoryList(String type);


    }
}
