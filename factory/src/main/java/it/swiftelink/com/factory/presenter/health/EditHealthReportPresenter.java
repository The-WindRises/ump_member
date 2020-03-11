package it.swiftelink.com.factory.presenter.health;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.health.EditHealthReportModel;
import it.swiftelink.com.factory.model.health.HealthDepartmentsResModel;
import it.swiftelink.com.factory.net.NetWork;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

public class EditHealthReportPresenter extends BasePresenter<EditHealthReportContract.View> implements EditHealthReportContract.Presenter {
    public EditHealthReportPresenter(EditHealthReportContract.View view) {
        super(view);
    }

    @Override
    public void editHealthReport( EditHealthReportModel model) {


        start();

        Observable<BaseResModel> observable = NetWork.getRequest().editHealthReport( model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.EDIT_HEALTH_REPORT,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.editHealthReportSuccess();
                }else{
                    mView.showError(Common.UrlApi.EDIT_HEALTH_REPORT,resModel.getCode(),resModel.getMessage());
                }
            }
        });


    }

    @Override
    public void getDepartments(String language) {

        start();
        Observable<HealthDepartmentsResModel> observable = NetWork.getRequest().getDepartmentsList(language);

        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<HealthDepartmentsResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_DEPARTMENTS,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(HealthDepartmentsResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getDepartmentsSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_DEPARTMENTS,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void uploadImage(List<String> pathList) {

        if(pathList!=null&&pathList.size()>0){
            start();
            List<MultipartBody.Part> partList = new ArrayList<>();
            for(String path :pathList){
                File file =new File(path);
                if(file!=null){
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("files[]", file.getName(), requestFile);
                    partList.add(filePart);


                }
            }

            Observable<UploadResModel> observable = NetWork.getRequest().uploadImageList(partList);
            NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<UploadResModel>() {
                @Override
                public void onError(ApiException e) {

                    mView.showError(Common.UrlApi.GET_UPLOAD,e.getCode(),e.getMessage());
                }

                @Override
                public void onSuccess(UploadResModel resModel) {
                    if (resModel != null) {
                        mView.uploadImageSuccess(resModel);
                    } else {
                        mView.showError(Common.UrlApi.GET_UPLOAD, 200,"图片上传失败");
                    }
                }
            });


        }

    }
}
