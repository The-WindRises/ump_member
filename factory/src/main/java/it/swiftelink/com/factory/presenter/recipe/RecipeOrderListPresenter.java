package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.recipe.RecipeOrderListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class RecipeOrderListPresenter extends BasePresenter<RecipeOrderListContract.View> implements RecipeOrderListContract.Presenter {

    public RecipeOrderListPresenter(RecipeOrderListContract.View view) {
        super(view);
    }

    @Override
    public void getRecipeOrderList( int currPage, int pageSize,String status) {

        start();

        Observable<RecipeOrderListResModel> observable = NetWork.getRequest().getRecipeOrderList( currPage, pageSize,status);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RecipeOrderListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.RECIPE_ORDER_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(RecipeOrderListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getRecipeOrderListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.RECIPE_ORDER_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void confirmOrderReceived(String orderId) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().confirmOrderRecived(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.CONFIRM_ORDER_RECEIVED,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.confirmOrderReceivedSuccess();
                }else{
                    mView.showError(Common.UrlApi.CONFIRM_ORDER_RECEIVED,resModel.getCode(),resModel.getMessage());
                }

            }
        });

    }

    @Override
    public void refundOrder(String orderId) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().refundOrder(orderId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.REFUND_ORDER,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.refundOrerderSuccess();
                }else{
                    mView.showError(Common.UrlApi.REFUND_ORDER,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }
}
