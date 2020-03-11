package it.swiftelink.com.factory.presenter.main;

import java.io.File;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.user.EditUserInfoModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.net.NetWork;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

public class UserInfoPresenter extends BasePresenter<UserInfoContract.View> implements UserInfoContract.Presenter {
    public UserInfoPresenter(UserInfoContract.View view) {
        super(view);
    }

    @Override
    public void getUserInfo() {

        start();

        Observable<UserInfoResModel> observable = NetWork.getRequest().getUserInfo();

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<UserInfoResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_USER_INFO, e.getCode() ,e.getMessage());
            }

            @Override
            public void onSuccess(UserInfoResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getUserInfoSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_USER_INFO, resModel.getCode() ,resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void EditUserInfo(String userId, EditUserInfoModel model) {

        start();


        Observable<BaseResModel> observable = NetWork.getRequest().editUserInfo(userId, model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.EDIT_USER_INFO,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.EditUserInfoSuccess();
                }else{
                    mView.showError(Common.UrlApi.EDIT_USER_INFO,resModel.getCode(),resModel.getMessage());
                }
            }
        });

    }

    @Override
    public void uploadImage(String path) {
        File file = new File(path);
        if (file != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("files[]", file.getName(), requestFile);
            Observable<UploadResModel> observable = NetWork.getRequest().uploadImage(filePart);
            NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<UploadResModel>() {
                @Override
                public void onError(ApiException e) {
                    mView.showError(Common.UrlApi.GET_UPLOAD,e.getCode(),e.getMessage());
                }

                @Override
                public void onSuccess(UploadResModel uploadResModel) {
                    if (uploadResModel != null) {
                        mView.uploadImageSuccess(uploadResModel);
                    } else {
                        mView.showError(Common.UrlApi.GET_UPLOAD, 200,"图片上传失败");
                    }
                }
            });

        }
    }
}
