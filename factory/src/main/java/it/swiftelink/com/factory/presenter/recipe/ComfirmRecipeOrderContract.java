package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderModel;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderDetailResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderIdResModel;

public class ComfirmRecipeOrderContract {


    public interface View extends BaseContract.View<ComfirmRecipeOrderContract.Presenter> {

        void getOrderInfoFromDetailSuccess(OrderInfoFromOrderDetailResModel model);


        void getOrderInfoFromOrderIdSuccess(OrderInfoFromOrderIdResModel model);

        void getWeixinPayConfigSuccess(WeixinPayConfigResModel resModel);
        void getZhifuboPayConfigSuccess(ZhifubaoPayConfigResModel resModel);

        void confirmRecipeOrderSuccess(CofirmRecipeOrderResModel resModel);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getOrderInfoFromDetail(String prescriptionId , String prescriptionDrugIds);

        void getOrderInfoFromOrderId(String OrderId);

        void getWeixinPayConfig(String orderId);
        void getZhifuboPayConfig(String orderId);

        void confirmRecipeOrder(CofirmRecipeOrderModel model);
    }
}
