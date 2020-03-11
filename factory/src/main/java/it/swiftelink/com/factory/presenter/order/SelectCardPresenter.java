package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.card.FreePickPackageModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.net.NetWork;
import it.swiftelink.com.factory.presenter.main.HomeContract;
import rx.Observable;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/2 11:41
 */
public class SelectCardPresenter extends BasePresenter<SelectCardContract.View> implements SelectCardContract.Presenter {

    public SelectCardPresenter(SelectCardContract.View view) {
        super(view);
    }

    @Override
    public void getSetMealList(String language, int currPage, int pageSize) {
        Observable<SetMealListResModel> observable = NetWork.getRequest().getSetMealList(currPage, pageSize, language);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<SetMealListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_SETMEAL_LIST, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(SetMealListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getSetMealListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_SETMEAL_LIST, resModel.getCode(), resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void postFreePick(String id) {
        FreePickPackageModel freePickPackageModel = new FreePickPackageModel();
        freePickPackageModel.setId(id);
        Observable<ReceiveCardResModel> observable = NetWork.getRequest().postFreePick(freePickPackageModel);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<ReceiveCardResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.POST_RECEIVIE_CARD, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(ReceiveCardResModel resModel) {

                mView.postFreePickSuccess(resModel);

            }
        });
    }


}
