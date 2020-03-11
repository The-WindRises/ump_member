package it.swiftelink.com.factory.model.InquiryReport;

import it.swiftelink.com.common.factory.BaseResModel;

public class CofirmRecipeOrderResModel extends BaseResModel {


    /**
     * data : {"id":"订单ID","no":"订单号"}
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
         * id : 订单ID
         * no : 订单号
         */

        private String id;
        private String no;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }
    }
}
