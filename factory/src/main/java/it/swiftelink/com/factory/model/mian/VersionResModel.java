package it.swiftelink.com.factory.model.mian;

import it.swiftelink.com.common.factory.BaseResModel;

public class VersionResModel extends BaseResModel {


    /**
     * data : {"id":"105ddd94c42411e998960242c0a82003","appDownloadUrl":"/url","appVersionNumber":"8","appVersionName":"1.0.7","isForce":1,"device":"android","type":"doctor","updateLog":"1、更新已知BUG\\n2、更新XXXX\\n3、呵呵哒","createTime":1569657056000}
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
         * id : 105ddd94c42411e998960242c0a82003
         * appDownloadUrl : /url
         * appVersionNumber : 8
         * appVersionName : 1.0.7
         * isForce : 1
         * device : android
         * type : doctor
         * updateLog : 1、更新已知BUG\n2、更新XXXX\n3、呵呵哒
         * createTime : 1569657056000
         */

        private String id;
        private String appDownloadUrl;
        private String appVersionNumber;
        private String appVersionName;
        private int isForce;
        private String device;
        private String type;
        private String updateLog;
        private long createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAppDownloadUrl() {
            return appDownloadUrl;
        }

        public void setAppDownloadUrl(String appDownloadUrl) {
            this.appDownloadUrl = appDownloadUrl;
        }

        public String getAppVersionNumber() {
            return appVersionNumber;
        }

        public void setAppVersionNumber(String appVersionNumber) {
            this.appVersionNumber = appVersionNumber;
        }

        public String getAppVersionName() {
            return appVersionName;
        }

        public void setAppVersionName(String appVersionName) {
            this.appVersionName = appVersionName;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateLog() {
            return updateLog;
        }

        public void setUpdateLog(String updateLog) {
            this.updateLog = updateLog;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
