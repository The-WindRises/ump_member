package it.swiftelink.com.factory.model.card;

import com.google.gson.annotations.SerializedName;

import it.swiftelink.com.common.factory.BaseResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/2 11:00
 */
public class InquiryValidCardResModel extends BaseResModel {

    /**
     * data : {"success":false,"type":2}
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
         * success : false
         * type : 2
         */

        @SerializedName("success")
        private boolean successX;
        private int type;

        public boolean isSuccessX() {
            return successX;
        }

        public void setSuccessX(boolean successX) {
            this.successX = successX;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
