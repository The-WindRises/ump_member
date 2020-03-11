package it.swiftelink.com.factory.presenter.card;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.card.CardListResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/27 13:55
 */
public class MyCardContract {
    public interface View extends BaseContract.View<MyCardContract.Presenter> {

        void getMyCardListSuccess(CardListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getMyCardList(int currPage,int pageSize,String validFlag);

    }
}
