package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.RegisterModel;

public class ForgetPsdContract {

    public interface View extends BaseContract.View<ForgetPsdContract.Presenter> {
        void changePsdSuccess(ChangePsdResModel model);

        void getPhoneCodeResModelSuccess();
    }

    public interface Presenter extends BaseContract.Presenter {

        void changePsd(RegisterModel model);

        void getPhoneCode(GetPhoneCodeModel model);
    }
}
