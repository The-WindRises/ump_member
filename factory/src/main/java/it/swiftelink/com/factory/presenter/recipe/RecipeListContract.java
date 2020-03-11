package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.recipe.RecipeListResModel;

public class RecipeListContract {

    public interface View extends BaseContract.View<RecipeListContract.Presenter> {

        void getRecipeListSuccess(RecipeListResModel model);


    }

    public interface Presenter extends BaseContract.Presenter {
        void geRecipeList(int currPage,int pageSize);

    }
}
