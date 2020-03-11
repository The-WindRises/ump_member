package it.swiftelink.com.factory.model.appointment;

import it.swiftelink.com.common.factory.BaseResModel;

public class PackageOrderConfirmInfoResModel extends BaseResModel {


    /**
     * data : {"no":"VC2019080810052485"}
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
         * no : VC2019080810052485
         */

        private String no;
        private String id;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
