package it.swiftelink.com.factory.presenter.account;


import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginResModel;

/**
 * Created by Administrator on 2018/8/20.
 */

public class LoginContract extends BaseContract {

    public interface View extends BaseContract.View<Presenter> {
        //登录成功
        void loginSuccess(LoginResModel model);
        void getPhoneCodeResModelSuccess();
        void phoneLoginSuccess(LoginResModel loginResModel);
    }

    public interface Presenter extends BaseContract.Presenter {
        //发起一个登录
        void login(String userName, String password);
        void getPhoneCode(GetPhoneCodeModel model);
        void phoneLogin(LoginByPhoneModel model);
    }
}
