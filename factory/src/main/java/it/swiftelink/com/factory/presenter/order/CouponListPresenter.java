package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class CouponListPresenter extends BasePresenter<CouponListContract.View> implements CouponListContract.Presenter {
    public CouponListPresenter(CouponListContract.View view) {
        super(view);
    }

    @Override
    public void getCouponList(int currentPage,int pageSize , String orderAmount, String type, String language) {

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
