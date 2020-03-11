package it.swiftelink.com.factory.presenter.health;

import java.util.List;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.health.EditHealthReportModel;
import it.swiftelink.com.factory.model.health.HealthDepartmentsResModel;

public class EditHealthReportContract {

    public interface View extends BaseContract.View<EditHealthReportContract.Presenter> {

        void editHealthReportSuccess();

        void getDepartmentsSuccess(HealthDepartmentsResModel resModel);

        void uploadImageSuccess(UploadResModel resModel);

    }

    public interface Presenter extends BaseContract.Presenter {
        void editHealthReport(EditHealthReportModel model);

        void getDepartments(String language);

        void uploadImage(List<String> pathList);

    }
}
