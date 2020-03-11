package it.swiftelink.com.factory.presenter.setmeal;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.message.MessageRemindCountResModel;
import it.swiftelink.com.factory.model.mian.BinnerListResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;

public class SetMealBuyContract {

    public interface View extends BaseContract.View<SetMealBuyContract.Presenter> {


        void getSetMealListSuccess(SetMealListResModel model);

        void getForeseeInquiryImageSuccess(ForeseeInquiryImageResModel resModel);

    }

    public interface Presenter extends BaseContract.Presenter {

        void getSetMealList(String language, int currPage, int pageSize);

        void getForeseeInquiryImage(String language);


    }
}
