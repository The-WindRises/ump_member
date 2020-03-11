package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.recipe.RecipeOrderListResModel;

public class RecipeOrderListContract {

    public interface View extends BaseContract.View<RecipeOrderListContract.Presenter> {

        void getRecipeOrderListSuccess(RecipeOrderListResModel model);

        void confirmOrderReceivedSuccess();

        void refundOrerderSuccess();

    }

    public interface Presenter extends BaseContract.Presenter {
        void getRecipeOrderList(int currPage,int pageSize,String status);

        void confirmOrderReceived(String orderId);

        void refundOrder(String OrderId);

    }
}
