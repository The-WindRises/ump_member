package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.order.LogisticsResModel;

public class LogisticsContract  {

    public interface View extends BaseContract.View<LogisticsContract.Presenter> {

        void getLogisticsSuccess(LogisticsResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getLogistics(String orderId);

    }
}
