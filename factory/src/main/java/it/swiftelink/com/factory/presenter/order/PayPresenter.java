package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.appointment.CheckPayPasswordResmodel;
import it.swiftelink.com.factory.model.appointment.PackConfirmModel;
import it.swiftelink.com.factory.model.appointment.PackageOrderConfirmInfoResModel;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class PayPresenter extends BasePresenter<PayContract.View> implements PayContract.Presenter {
    public PayPresenter(PayContract.View view) {
        super(view);
    }

    @Override
    public void getWeixinPayConfig(String orderId) {

        start();

        Observable<WeixinPayConfigResModel> observable = NetWork.getRequest().payWeixin(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<WeixinPayConfigResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_WEIXINPAY_CONFIG,e.getCode(),e.getMessage());
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
    public void checkPassword() {
        start();

        Observable<CheckPayPasswordResmodel> observable = NetWork.getRequest().checkPassword();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<CheckPayPasswordResmodel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CHECK_PASSWORD,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(CheckPayPasswordResmodel resModel) {

                if(resModel.isSuccess()){
                    mView.checkPasswordSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.CHECK_PASSWORD,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void payAmount(String orderId,String password) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().payByAmount(orderId, password);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.PAY_AMOUNT,e.getCode(),e.getMessage());
            }
            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.payAmountSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.PAY_AMOUNT,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void getPackageOrderConfirmInfo(PackConfirmModel  model) {
        start();

        Observable<PackageOrderConfirmInfoResModel> observable = NetWork.getRequest().getPackConfirmOrderInfo(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<PackageOrderConfirmInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_PACKAGEORDER_CONFIRMINFO,e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(PackageOrderConfirmInfoResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPackageOrderConfirmInfoSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_PACKAGEORDER_CONFIRMINFO,resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getCouponList(int currentPage, int pageSize, String orderAmount, String type, String language) {

        Observable<CouponListResModel> observable = NetWork.getRequest().getCouponsList(currentPage,pageSize ,type,orderAmount,language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<CouponListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_COUPON_LIST,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(CouponListResModel resModel) {
                if(resModel.isSuccess()){
                    mView.getCouponListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_COUPON_LIST,resModel.getCode(),resModel.getMessage());
                }

            }
        });
    }

}
