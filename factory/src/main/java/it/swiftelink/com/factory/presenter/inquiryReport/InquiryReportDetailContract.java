package it.swiftelink.com.factory.presenter.inquiryReport;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.model.InquiryReport.InquiryReportResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;

public class InquiryReportDetailContract {

    public interface View extends BaseContract.View<InquiryReportDetailContract.Presenter> {

        void getInquiryInfoSuccess(InquiryReportResModel model);

        void getRecipeInfoSuccess(RecipeInfoResModel model);

    }

    public interface Presenter extends BaseContract.Presenter {
        void getInquiryInfo(String id);

        void getRecipeInfo(String id);

    }
}
