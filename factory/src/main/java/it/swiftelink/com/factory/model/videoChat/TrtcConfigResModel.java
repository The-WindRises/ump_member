package it.swiftelink.com.factory.model.videoChat;

import java.io.Serializable;
import java.util.List;

public class TrtcConfigResModel implements Serializable {


    /**
     * sdkappid : 1400242259
     * roomId : 1739
     * users : [{"userId":"DR000018","userToken":"eJw1j8tugzAUBf*FbatiwA5xpS4gL0UiLEpSIW8sxzbpVR5Y1EUmVf*91CLbmcWZ8xPsi*pFGAOKC8uTTgWvAQqePdbOQKe5aKzuRhwRQmKEHhaUvllowLvl*yhQNJ-cF5xGuFsdFtslTXIaSTlkLt3cnTqG4AZW72hVD9eOQTLrm1Q*lZh9ZLDK*vJYLNr81J9bEPeMreflxciCbA*fm73Nq9CBSCGsJcNvjzF15v7BfyNGKMZxTOgkLVy1b5*llCYRxhMXUrbfN8vtYLS--PsHfGZQVw__"}]
     * code : 0
     * message :
     * errorMessage : null
     * success : true
     */

    private int sdkappid;
    private int roomId;
    private int code;
    private String message;
    private String errorMessage;
    private boolean success;
    private List<UsersBean> users;

    public int getSdkappid() {
        return sdkappid;
    }

    public void setSdkappid(int sdkappid) {
        this.sdkappid = sdkappid;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * userId : DR000018
         * userToken : eJw1j8tugzAUBf*FbatiwA5xpS4gL0UiLEpSIW8sxzbpVR5Y1EUmVf*91CLbmcWZ8xPsi*pFGAOKC8uTTgWvAQqePdbOQKe5aKzuRhwRQmKEHhaUvllowLvl*yhQNJ-cF5xGuFsdFtslTXIaSTlkLt3cnTqG4AZW72hVD9eOQTLrm1Q*lZh9ZLDK*vJYLNr81J9bEPeMreflxciCbA*fm73Nq9CBSCGsJcNvjzF15v7BfyNGKMZxTOgkLVy1b5*llCYRxhMXUrbfN8vtYLS--PsHfGZQVw__
         */

        private String userId;
        private String userToken;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }
}
