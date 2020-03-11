package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.order.PackageInfoResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/25 12:09
 */
public class PackageInfoContract {
    public interface View extends BaseContract.View<PackageInfoContract.Presenter> {
        void getPackageCarInfoSuccess(PackageInfoResModel packageInfoResModel);
        void postFreePickSuccess(ReceiveCardResModel resModel);

    }
    public interface Presenter extends BaseContract.Presenter {


        void getPackageCarInfo(String id,String language);
        void postFreePick(String id);

    }
}
