package it.swiftelink.com.factory.model.order;

import java.io.Serializable;
import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class PackageOrderListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"orderNo":"111","quantity":12,"orderId":"22","validateDays":365,"discountPrice":699.99,"description":"365天有效","type":"PACKAGE","paymentAmount":140,"totalAmount":140,"price":1699,"expireDate":"2020-12-31","id":"38ba1c15-a797-11e9-8fe8-0242c0a82002","packageName":"年卡","status":"PAYMENT"}],"totalPages":3,"count":25}
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
         * dataList : [{"orderNo":"111","quantity":12,"orderId":"22","validateDays":365,"discountPrice":699.99,"description":"365天有效","type":"PACKAGE","paymentAmount":140,"totalAmount":140,"price":1699,"expireDate":"2020-12-31","id":"38ba1c15-a797-11e9-8fe8-0242c0a82002","packageName":"年卡","status":"PAYMENT"}]
         * totalPages : 3
         * count : 25
         */

        private int totalPages;
        private int count;
        private List<DataListBean> dataList;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * orderNo : 111
             * quantity : 12
             * orderId : 22
             * validateDays : 365
             * discountPrice : 699.99
             * description : 365天有效
             * type : PACKAGE
             * paymentAmount : 140
             * totalAmount : 140
             * price : 1699
             * expireDate : 2020-12-31
             * id : 38ba1c15-a797-11e9-8fe8-0242c0a82002
             * packageName : 年卡
             * status : PAYMENT
             */

            private String orderNo;
            private int quantity;
            private String orderId;
            private int validateDays;
            private double discountPrice;
            private String description;
            private String type;
            private double paymentAmount;
            private double totalAmount;
            private double price;
            private String expireDate;
            private String id;
            private String packageName;
            private String status;
            private String creationDate;
            private String couponId;
            private double couponAmount;

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getValidateDays() {
                return validateDays;
            }

            public void setValidateDays(int validateDays) {
                this.validateDays = validateDays;
            }

            public double getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getPaymentAmount() {
                return paymentAmount;
            }

            public void setPaymentAmount(double paymentAmount) {
                this.paymentAmount = paymentAmount;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(String expireDate) {
                this.expireDate = expireDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(String creationDate) {
                this.creationDate = creationDate;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public double getCouponAmount() {
                return couponAmount;
            }

            public void setCouponAmount(double couponAmount) {
                this.couponAmount = couponAmount;
            }
        }
    }
}
