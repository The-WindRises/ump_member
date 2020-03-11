package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.recipe.RecipeListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class RecipeListPresenter extends BasePresenter<RecipeListContract.View> implements RecipeListContract.Presenter {

    public RecipeListPresenter(RecipeListContract.View view) {
        super(view);
    }

    @Override
    public void geRecipeList(int currPage, int pageSize) {

        if (currPage == 1) {
            start();
        }
        Observable<RecipeListResModel> observable = NetWork.getRequest().getRecipeList(currPage, pageSize);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RecipeListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_RECIPE_LIST, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(RecipeListResModel recipeListResModel) {

                if (recipeListResModel.isSuccess()) {
                    mView.getRecipeListSuccess(recipeListResModel);
                } else {
                    mView.showError(Common.UrlApi.GET_RECIPE_LIST,recipeListResModel.getCode(), recipeListResModel.getMessage());
                }
            }
        });
    }
}
