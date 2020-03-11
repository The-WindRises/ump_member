package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.AgrreementResModel;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.RegisterModel;
import it.swiftelink.com.factory.model.account.RegisterResModel;

public class RegisterConract {

    public interface View extends BaseContract.View<RegisterConract.Presenter> {

        void registerSuccess(RegisterResModel resModel);

        void getPhoneCodeResModelSuccess();
        void getAgrreementSuess(AgrreementResModel agrreementResModel);

    }

    public interface Presenter extends BaseContract.Presenter {

        void getPhoneCode(GetPhoneCodeModel model);
        void register(RegisterModel registerModel);
        void getAgrreement(String language, String type);
    }
}
