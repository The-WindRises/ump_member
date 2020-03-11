package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.order.PackageOrderListResModel;

public class PackageOrderListContract {

    public interface View extends BaseContract.View<PackageOrderListContract.Presenter> {

        void getPackageOrderListSuccess(PackageOrderListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getPackageOrderList( int currPage, int pageSize, String status, String type);


    }
}
