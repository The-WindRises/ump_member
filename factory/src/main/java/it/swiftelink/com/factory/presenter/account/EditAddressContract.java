package it.swiftelink.com.factory.presenter.account;


import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.factory.model.account.AddressDetailResModel;
import it.swiftelink.com.factory.model.account.SaveAddressModel;

/**
 * Created by Administrator on 2018/8/20.
 */

public class EditAddressContract extends BaseContract {

    public interface View extends BaseContract.View<Presenter> {


        void getAddressDetailSuccess(AddressDetailResModel model);
        void saveAddressSuccess(BaseResModel model);
        void deleteAddressSuccess();

    }

    public interface Presenter extends BaseContract.Presenter {

        void getAddressDetail(String id);

        void saveAddress(SaveAddressModel model);

        void deleteAddress(String id);
    }
}
