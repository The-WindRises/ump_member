package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.card.FreePickPackageModel;
import it.swiftelink.com.factory.model.order.LogisticsResModel;
import it.swiftelink.com.factory.model.order.PackageInfoResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

/**
 * @Description: 套餐详情
 * @Author: klk
 * @CreateDate: 2019/12/25 12:08
 */
public class PackageInfoPresenter extends BasePresenter<PackageInfoContract.View> implements PackageInfoContract.Presenter {

    public PackageInfoPresenter(PackageInfoContract.View view) {
        super(view);
    }

    @Override
    public void getPackageCarInfo(String id,String language) {
         start();
         Observable<PackageInfoResModel> observable = NetWork.getRequest().getPackageInfoDetail(id,language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<PackageInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_LOGISTICS, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(PackageInfoResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getPackageCarInfoSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_LOGISTICS, resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void postFreePick(String id) {
        FreePickPackageModel freePickPackageModel=new FreePickPackageModel();
        freePickPackageModel.setId(id);
        Observable<ReceiveCardResModel> observable=NetWork.getRequest().postFreePick(freePickPackageModel);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ReceiveCardResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.POST_RECEIVIE_CARD,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(ReceiveCardResModel resModel) {

                mView.postFreePickSuccess(resModel);

            }
        });
    }
}
