package it.swiftelink.com.factory.presenter.account;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.account.BalanceListResModel;

public class BalanceContract  {

    public interface View extends BaseContract.View<BalanceContract.Presenter> {
        void getBalanceListSuccess(BalanceListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {

        void getBalanceList(int currentPage ,int pageSize);
    }
}
