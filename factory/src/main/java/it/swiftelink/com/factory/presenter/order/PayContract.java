package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.factory.model.appointment.CheckPayPasswordResmodel;
import it.swiftelink.com.factory.model.appointment.PackConfirmModel;
import it.swiftelink.com.factory.model.appointment.PackageOrderConfirmInfoResModel;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;

public class PayContract {

    public interface View extends BaseContract.View<PayContract.Presenter> {

        void getWeixinPayConfigSuccess(WeixinPayConfigResModel resModel);

        void getZhifuboPayConfigSuccess(ZhifubaoPayConfigResModel resModel);

        void payAmountSuccess( BaseResModel resModel);

        void checkPasswordSuccess(CheckPayPasswordResmodel resModel);

        void getPackageOrderConfirmInfoSuccess(PackageOrderConfirmInfoResModel resModel);
        void getCouponListSuccess(CouponListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getWeixinPayConfig(String orderId);

        void getZhifuboPayConfig(String orderId);

        void checkPassword();

        void payAmount(String orderId, String password);

        void getPackageOrderConfirmInfo(PackConfirmModel model);
        void getCouponList(int currentPage,int pageSize , String orderAmount, String type, String language);

    }
}
