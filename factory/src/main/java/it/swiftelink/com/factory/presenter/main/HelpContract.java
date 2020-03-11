package it.swiftelink.com.factory.presenter.main;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.mian.HelpListResModel;

public class HelpContract {

    public interface View extends BaseContract.View<HelpContract.Presenter> {

        void getHelpListSuccess(HelpListResModel model);



    }

    public interface Presenter extends BaseContract.Presenter {

        void getHelpList(String language );

    }
}
