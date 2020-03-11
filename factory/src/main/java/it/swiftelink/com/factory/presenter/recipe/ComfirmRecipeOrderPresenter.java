package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderModel;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderDetailResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderIdResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class ComfirmRecipeOrderPresenter extends BasePresenter<ComfirmRecipeOrderContract.View> implements ComfirmRecipeOrderContract.Presenter {
    public ComfirmRecipeOrderPresenter(ComfirmRecipeOrderContract.View view) {
        super(view);
    }

    @Override
    public void getOrderInfoFromDetail(String prescriptionId, String prescriptionDrugIds) {
        start();

        Observable<OrderInfoFromOrderDetailResModel> observable = NetWork.getRequest().getOrderInfoFromDetail(prescriptionId, prescriptionDrugIds);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<OrderInfoFromOrderDetailResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_ORDERINFO_FROM_DETAIL,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(OrderInfoFromOrderDetailResModel resmodel) {

                if (resmodel.isSuccess()) {
                    mView.getOrderInfoFromDetailSuccess(resmodel);
                } else {
                    mView.showError(Common.UrlApi.GET_ORDERINFO_FROM_DETAIL,resmodel.getCode(), resmodel.getMessage());
                }
            }
        });

    }

    @Override
    public void getOrderInfoFromOrderId(String OrderId) {
        start();

        Observable<OrderInfoFromOrderIdResModel> observable = NetWork.getRequest().getOrderInfoFromOrderId(OrderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<OrderInfoFromOrderIdResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_ORDERINFO_FROM_ORDERID, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(OrderInfoFromOrderIdResModel model) {

                if(model.isSuccess()){
                    mView.getOrderInfoFromOrderIdSuccess(model);
                }else {
                    mView.showError(Common.UrlApi.GET_ORDERINFO_FROM_ORDERID,model.getCode(), model.getMessage());
                }
            }
        });


    }

    @Override
    public void getWeixinPayConfig(String orderId) {

        start();

        Observable<WeixinPayConfigResModel> observable = NetWork.getRequest().payWeixin(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<WeixinPayConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_WEIXINPAY_CONFIG,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(WeixinPayConfigResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getWeixinPayConfigSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_WEIXINPAY_CONFIG,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void getZhifuboPayConfig(String orderId) {

        start();

        Observable<ZhifubaoPayConfigResModel> observable = NetWork.getRequest().payZhifubao(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ZhifubaoPayConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_ZHIFUBOPAY_CONFIG,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ZhifubaoPayConfigResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getZhifuboPayConfigSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_ZHIFUBOPAY_CONFIG,resModel.getCode(),resModel.getMessage());
                }

            }
        });

    }

    @Override
    public void confirmRecipeOrder(CofirmRecipeOrderModel model) {

        start();

        Observable<CofirmRecipeOrderResModel> observable = NetWork.getRequest().confirmRecipeOrder(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<CofirmRecipeOrderResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CONFIRM_RECIPEORDER,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(CofirmRecipeOrderResModel resModel) {

                if(resModel.isSuccess()){
                    mView.confirmRecipeOrderSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.CONFIRM_RECIPEORDER,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
