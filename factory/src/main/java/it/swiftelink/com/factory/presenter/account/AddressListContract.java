package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.AddressListResModel;

public class AddressListContract {

    public interface View extends BaseContract.View<AddressListContract.Presenter> {


        void getAddressListSuccess(AddressListResModel model);

        void setAddressDefaultSuccess();


    }

    public interface Presenter extends BaseContract.Presenter {

        void getAddressList(int currPage ,int pageSize);

        void setAddressDefaultSuccess(String id);

    }
}
