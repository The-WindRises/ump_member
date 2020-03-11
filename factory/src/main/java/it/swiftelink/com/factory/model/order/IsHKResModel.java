package it.swiftelink.com.factory.model.order;

import it.swiftelink.com.common.factory.BaseResModel;

public class IsHKResModel extends BaseResModel {

    /**
     * data : {"patientPackageId":"06fdb66b56414b268a9b83a3d89a6429","errorMsg":{"zh_CN":"当前香港医生已下班，暂不可问诊。","zh_TW":"當前香港醫生已下班， 未能提供診症服務， 請稍後再試。 ","en_US":"No doctor is available at the moment, please try again later."}}
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
         * patientPackageId : 06fdb66b56414b268a9b83a3d89a6429
         * errorMsg : {"zh_CN":"当前香港医生已下班，暂不可问诊。","zh_TW":"當前香港醫生已下班， 未能提供診症服務， 請稍後再試。 ","en_US":"No doctor is available at the moment, please try again later."}
         */

        private String patientPackageId;
        private ErrorMsgBean errorMsg;

        public String getPatientPackageId() {
            return patientPackageId;
        }

        public void setPatientPackageId(String patientPackageId) {
            this.patientPackageId = patientPackageId;
        }

        public ErrorMsgBean getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(ErrorMsgBean errorMsg) {
            this.errorMsg = errorMsg;
        }

        public static class ErrorMsgBean {
            /**
             * zh_CN : 当前香港医生已下班，暂不可问诊。
             * zh_TW : 當前香港醫生已下班， 未能提供診症服務， 請稍後再試。
             * en_US : No doctor is available at the moment, please try again later.
             */

            private String zh_CN;
            private String zh_TW;
            private String en_US;

            public String getZh_CN() {
                return zh_CN;
            }

            public void setZh_CN(String zh_CN) {
                this.zh_CN = zh_CN;
            }

            public String getZh_TW() {
                return zh_TW;
            }

            public void setZh_TW(String zh_TW) {
                this.zh_TW = zh_TW;
            }

            public String getEn_US() {
                return en_US;
            }

            public void setEn_US(String en_US) {
                this.en_US = en_US;
            }
        }
    }
}
