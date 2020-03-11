package it.swiftelink.com.factory.presenter.inquiryReport;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.InquiryReport.InquiryReportResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class InquiryReportDetailPresenter extends BasePresenter<InquiryReportDetailContract.View> implements InquiryReportDetailContract.Presenter {


    public InquiryReportDetailPresenter(InquiryReportDetailContract.View view) {
        super(view);
    }

    @Override
    public void getInquiryInfo(String id) {
        start();

        Observable<InquiryReportResModel> observable = NetWork.getRequest().getInquiryReportInfo(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<InquiryReportResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_INQUIRY_REPORT_INFO,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(InquiryReportResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getInquiryInfoSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_INQUIRY_REPORT_INFO,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getRecipeInfo(String id) {
        start();

        Observable<RecipeInfoResModel> observable = NetWork.getRequest().getRecipeInfo(id);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<RecipeInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_RECIPE_INFO,e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(RecipeInfoResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getRecipeInfoSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_RECIPE_INFO,resModel.getCode(),resModel.getMessage());
                }

            }
        });
    }
}
