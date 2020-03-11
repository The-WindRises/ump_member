package it.swiftelink.com.factory.model.order;

import com.google.gson.annotations.SerializedName;

import it.swiftelink.com.common.factory.BaseResModel;

public class WeixinPayConfigResModel extends BaseResModel {


    /**
     * data : {"order":{"package":"Sign=WXPay","appid":"wx03584f1a69b79581","sign":"CEE21CD3FBB76361A2123423A627DC9E","partnerid":"1487915292","prepayid":"wx061527351501623e0a4192581317513700","noncestr":"1908060727342930","timestamp":"1565076455"}}
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
         * order : {"package":"Sign=WXPay","appid":"wx03584f1a69b79581","sign":"CEE21CD3FBB76361A2123423A627DC9E","partnerid":"1487915292","prepayid":"wx061527351501623e0a4192581317513700","noncestr":"1908060727342930","timestamp":"1565076455"}
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * package : Sign=WXPay
             * appid : wx03584f1a69b79581
             * sign : CEE21CD3FBB76361A2123423A627DC9E
             * partnerid : 1487915292
             * prepayid : wx061527351501623e0a4192581317513700
             * noncestr : 1908060727342930
             * timestamp : 1565076455
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private String timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
