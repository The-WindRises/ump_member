package it.swiftelink.com.factory.presenter.main;

import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.user.EditUserInfoModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;

public class UserInfoContract {

    public interface View extends BaseContract.View<UserInfoContract.Presenter> {

        void getUserInfoSuccess(UserInfoResModel model);

        void EditUserInfoSuccess();
        void uploadImageSuccess(UploadResModel resModel);

    }

    public interface Presenter extends BaseContract.Presenter {

        void getUserInfo();

        void EditUserInfo(String userId ,EditUserInfoModel model);

        void uploadImage(String path);
    }
}
