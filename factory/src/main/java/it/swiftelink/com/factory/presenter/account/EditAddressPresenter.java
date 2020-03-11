package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.AddressDetailResModel;
import it.swiftelink.com.factory.model.account.SaveAddressModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class EditAddressPresenter extends BasePresenter<EditAddressContract.View> implements EditAddressContract.Presenter {
    public EditAddressPresenter(EditAddressContract.View view) {
        super(view);
    }

    @Override
    public void getAddressDetail(String id) {
        start();

        Observable<AddressDetailResModel> observable = NetWork.getRequest().getAddressDetail(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<AddressDetailResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_ADDRESS_DETAIL,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(AddressDetailResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getAddressDetailSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.GET_ADDRESS_DETAIL,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void saveAddress(SaveAddressModel model) {
        start();

        Observable<BaseResModel> observable = NetWork.getRequest().saveAddress(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.SAVE_ADDRESS,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.saveAddressSuccess(resModel);
                }else {
                    mView.showError(Common.UrlApi.SAVE_ADDRESS,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void deleteAddress(String id) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().deleteAddress(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.DELETE_ADDRESS,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel.isSuccess()){
                    mView.deleteAddressSuccess();
                }else {
                    mView.showError(Common.UrlApi.DELETE_ADDRESS,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }
}
