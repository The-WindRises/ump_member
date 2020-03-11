package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;

public class PayPsdContract {

    public interface View extends BaseContract.View<PayPsdContract.Presenter> {

        void setPayPsdSuccess();

        void getPhoneCodeResModelSuccess();

    }

    public interface Presenter extends BaseContract.Presenter {

        void getPhoneCode(GetPhoneCodeModel model);
        void setPayPsd(String password ,String mobile ,String smsCode);
    }
}
