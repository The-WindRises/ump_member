package it.swiftelink.com.factory.model.order;

import it.swiftelink.com.common.factory.BaseResModel;

public class ReceiveCardResModel extends BaseResModel {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
