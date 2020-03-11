package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.order.CouponListResModel;

public class CouponListContract {

    public interface View extends BaseContract.View<CouponListContract.Presenter> {

        void getCouponListSuccess(CouponListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getCouponList(int currentPage,int pageSize , String orderAmount, String type, String language);
    }
}
