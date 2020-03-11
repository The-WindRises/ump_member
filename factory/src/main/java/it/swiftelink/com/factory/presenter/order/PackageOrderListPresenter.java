package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.order.PackageOrderListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class PackageOrderListPresenter extends BasePresenter<PackageOrderListContract.View> implements PackageOrderListContract.Presenter {
    public PackageOrderListPresenter(PackageOrderListContract.View view) {
        super(view);
    }

    @Override
    public void getPackageOrderList( int currPage, int pageSize, String status, String type) {

        start();

        Observable<PackageOrderListResModel> observable = NetWork.getRequest().getPackegeOrderList(currPage, pageSize, status, type );

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<PackageOrderListResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_PACKAGE_ORDER_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(PackageOrderListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getPackageOrderListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_PACKAGE_ORDER_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
