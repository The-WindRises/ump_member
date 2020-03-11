package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;

public class ChangePsdContract {

    public interface View extends BaseContract.View<ChangePsdContract.Presenter> {
        void changePsdSuccess(ChangePsdResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {

        void changePsd(String type ,String oldPsd ,String newPsd);
    }
}
