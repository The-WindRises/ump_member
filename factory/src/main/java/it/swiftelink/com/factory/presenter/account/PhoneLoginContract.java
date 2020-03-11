package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/12 10:56
 */
public class PhoneLoginContract  {

    public interface View extends BaseContract.View<PhoneLoginContract.Presenter> {
        void getPhoneCodeResModelSuccess();
        void phoneLoginSuccess(LoginResModel loginResModel);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getPhoneCode(GetPhoneCodeModel model);
        void phoneLogin(LoginByPhoneModel model);
    }
}
