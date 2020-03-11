package it.swiftelink.com.factory.model.appointment;

import it.swiftelink.com.common.factory.BaseResModel;

public class CheckPayPasswordResmodel  extends BaseResModel {

    /**
     * data : {"checkResult":"N"}
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
         * checkResult : N
         */

        private String checkResult;

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }
    }
}
