package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.presenter.main.HomeContract;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/2 11:39
 */
public class SelectCardContract {
    public interface View extends BaseContract.View<SelectCardContract.Presenter> {

        void getSetMealListSuccess(SetMealListResModel model);
        void postFreePickSuccess(ReceiveCardResModel resModel);


    }
    public interface Presenter extends BaseContract.Presenter {

        void getSetMealList(String language , int  currPage ,int pageSize);
        void postFreePick(String id);



    }
}
