package it.swiftelink.com.factory.presenter.recipe;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.InquiryReport.InquiryReportResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryDetailResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class RecipeDetailPresenter extends BasePresenter<RecipeDetailContract.View> implements RecipeDetailContract.Presenter {
    public RecipeDetailPresenter(RecipeDetailContract.View view) {
        super(view);
    }

    @Override
    public void getRecipeDetail(String id) {

        start();

        Observable<RecipeInfoResModel> observable = NetWork.getRequest().getRecipeInfo(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RecipeInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_RECIPE_INFO,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(RecipeInfoResModel model) {

                if(model.isSuccess()){
                    mView.getRecipeDetailSuccess(model);
                }else {
                    mView.showError(Common.UrlApi.GET_RECIPE_INFO,model.getCode(),model.getMessage());
                }
            }
        });

    }

    @Override
    public void getInquiryInfo(String id) {

        start();

        Observable<InquiryDetailResModel> observable = NetWork.getRequest().getInquiryInfo(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<InquiryDetailResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_INQUIRY_INFO,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(InquiryDetailResModel model) {

                if(model.isSuccess()){
                    mView.getInquiryInfoSuccess(model);
                }else{
                    mView.showError(Common.UrlApi.GET_INQUIRY_INFO,model.getCode(),model.getMessage());
                }
            }
        });

    }
}
