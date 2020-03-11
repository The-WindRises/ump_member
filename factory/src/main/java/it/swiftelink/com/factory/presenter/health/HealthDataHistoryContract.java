package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;

public class HealthDataHistoryContract {
    public interface View extends BaseContract.View<HealthDataHistoryContract.Presenter> {



        void getHealthDataSuccess(HistoryHealthDataListResModel resModel);


    }

    public interface Presenter extends BaseContract.Presenter {


        void getHealthData( int currPage, int pageSize);

    }
}
