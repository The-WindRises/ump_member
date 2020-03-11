package it.swiftelink.com.factory.presenter.health;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.health.HealthReportListResModel;

public class HealthReportDetailContract {

    public interface View extends BaseContract.View<HealthReportDetailContract.Presenter> {

        void getHealthReportListSuccess(HealthReportListResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getHealthReportList(String type ,String id);
    }
}
