package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;

public class BindPhoneContract {

    public interface View extends BaseContract.View<BindPhoneContract.Presenter> {
        void bindPhoneSuccess(LoginResModel resModel);

        void getPhoneCodeResModelSuccess();

    }

    public interface Presenter extends BaseContract.Presenter {

        void bindPhone(LoginModel model);

        void getPhoneCode(GetPhoneCodeModel model);
    }
}
