package it.swiftelink.com.factory.model.account;

import it.swiftelink.com.common.factory.BaseResModel;

public class RegisterResModel extends BaseResModel {


    /**
     * data : {"userId":1000066,"openId":null,"loginId":"2c9280856c23ee7e016c23f3bd650000","userName":"18729553976","userAccount":"18729553976","employeeNo":"18729553976","mobile":"18729553976","password":"44abeea1e3994f2e031b8031ed8bc1d3","type":"Patient","accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyYW5kb20iOjIwMDAwMCwibG9naW5JZCI6IjJjOTI4MDg1NmMyM2VlN2UwMTZjMjNmM2JkNjUwMDAwIiwidHlwZSI6IlBhdGllbnQiLCJleHAiOjE1NjY1MTg0MDAsImlhdCI6MTU2MzkyNjQwMH0.bQAOEnBkMPY1bp4XoMLxHeRYd_n-Fjt1Jrx8xK9rdSY","creationDate":1563971272002,"lastUpdatedDate":1563971272415,"smsCode":"209482","headImg":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1000066
         * openId : null
         * loginId : 2c9280856c23ee7e016c23f3bd650000
         * userName : 18729553976
         * userAccount : 18729553976
         * employeeNo : 18729553976
         * mobile : 18729553976
         * password : 44abeea1e3994f2e031b8031ed8bc1d3
         * type : Patient
         * accessToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyYW5kb20iOjIwMDAwMCwibG9naW5JZCI6IjJjOTI4MDg1NmMyM2VlN2UwMTZjMjNmM2JkNjUwMDAwIiwidHlwZSI6IlBhdGllbnQiLCJleHAiOjE1NjY1MTg0MDAsImlhdCI6MTU2MzkyNjQwMH0.bQAOEnBkMPY1bp4XoMLxHeRYd_n-Fjt1Jrx8xK9rdSY
         * creationDate : 1563971272002
         * lastUpdatedDate : 1563971272415
         * smsCode : 209482
         * headImg : null
         */

        private String userId;
        private String openId;
        private String loginId;
        private String userName;
        private String userAccount;
        private String employeeNo;
        private String mobile;
        private String password;
        private String type;
        private String accessToken;
        private long creationDate;
        private long lastUpdatedDate;
        private String smsCode;
        private String headImg;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getEmployeeNo() {
            return employeeNo;
        }

        public void setEmployeeNo(String employeeNo) {
            this.employeeNo = employeeNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
        }

        public long getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(long lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public String getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }
}
