package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.AddressListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class AddressListPresenter extends BasePresenter<AddressListContract.View> implements AddressListContract.Presenter {
    public AddressListPresenter(AddressListContract.View view) {
        super(view);
    }

    @Override
    public void getAddressList(int currPage ,int pageSize) {

        start();

        Observable<AddressListResModel> observable = NetWork.getRequest().getAddressList(currPage,pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<AddressListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_ADDRESS_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(AddressListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getAddressListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_ADDRESS_LIST,resModel.getCode(),resModel.getMessage());
                }

            }
        });
    }

    @Override
    public void setAddressDefaultSuccess(String id) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().setAddressDefault(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.SET_ADDRESS_DEFAULT,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.setAddressDefaultSuccess();
                }else {
                    mView.showError(Common.UrlApi.SET_ADDRESS_DEFAULT,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }
}
