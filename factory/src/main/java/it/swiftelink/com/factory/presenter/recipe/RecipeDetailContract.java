package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.inquiry.InquiryDetailResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;

public class RecipeDetailContract {

    public interface View extends BaseContract.View<RecipeDetailContract.Presenter> {

        void getRecipeDetailSuccess(RecipeInfoResModel model);

        void getInquiryInfoSuccess(InquiryDetailResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void getRecipeDetail(String id);

        void getInquiryInfo(String id);

    }
}
