package it.swiftelink.com.factory.model.order;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class CouponListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576662239000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"154","last_updated_by":"154","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"ea8f0871217a11eab0c8506b4b231e38","last_updated_date":1576662239000,"status":"Not_Use","no":"02","language":"zh_CN"},{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576659513000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"30","last_updated_by":"30","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"91babdc1217411eab0c8506b4b231e38","last_updated_date":1576659513000,"status":"Not_Use"},{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576661339000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"154","last_updated_by":"154","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"d1fe09de217811eab0c8506b4b231e38","last_updated_date":1576661339000,"status":"Not_Use"},{"couponName":"季卡","orderAmount":680,"patientId":"2c9380846ef7ca72016ef8209e0c0000","faceValue":680,"newUser":"Yes","expireDate":"2020-12-06","remark":"1213","id":"2c9380846ef7ca72016ef8209e130001","creation_date":1576120918000,"couponId":"583cd4cffc7611e9b4600242c0a82002","last_updated_date":1576120918000,"status":"Not_Use"},{"couponName":"50元价值优惠券","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"满60可直接使用","creation_date":1576228004000,"couponId":"9cee6357fb8211e9b4600242c0a82002","created_by":"30","last_updated_by":"30","orderAmount":60,"faceValue":50,"newUser":"No","expireDate":"2020-01-12","id":"e27d66aa1d8711eab0c8506b4b231e38","last_updated_date":1576228004000,"status":"Not_Use"},{"no":"02","couponName":"gfd","patientId":"2c9380846ef7ca72016ef8209e0c0000","language":"zh_CN","remark":"fdsaf","creation_date":1576228064000,"couponId":"65cba9d3faf811e9b4600242c0a82002","created_by":"30","last_updated_by":"30","orderAmount":100,"faceValue":90,"newUser":"No","expireDate":"2020-01-12","id":"063c53681d8811eab0c8506b4b231e38","last_updated_date":1576228064000,"status":"Not_Use"}],"totalPages":1,"dataCount":6}
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
         * dataList : [{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576662239000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"154","last_updated_by":"154","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"ea8f0871217a11eab0c8506b4b231e38","last_updated_date":1576662239000,"status":"Not_Use"},{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576659513000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"30","last_updated_by":"30","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"91babdc1217411eab0c8506b4b231e38","last_updated_date":1576659513000,"status":"Not_Use"},{"couponName":"安安","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"房贷首付","creation_date":1576661339000,"couponId":"0287bc62217411eab0c8506b4b231e38","created_by":"154","last_updated_by":"154","orderAmount":300,"faceValue":75,"newUser":"No","expireDate":"2020-01-17","id":"d1fe09de217811eab0c8506b4b231e38","last_updated_date":1576661339000,"status":"Not_Use"},{"couponName":"季卡","orderAmount":680,"patientId":"2c9380846ef7ca72016ef8209e0c0000","faceValue":680,"newUser":"Yes","expireDate":"2020-12-06","remark":"1213","id":"2c9380846ef7ca72016ef8209e130001","creation_date":1576120918000,"couponId":"583cd4cffc7611e9b4600242c0a82002","last_updated_date":1576120918000,"status":"Not_Use"},{"couponName":"50元价值优惠券","patientId":"2c9380846ef7ca72016ef8209e0c0000","remark":"满60可直接使用","creation_date":1576228004000,"couponId":"9cee6357fb8211e9b4600242c0a82002","created_by":"30","last_updated_by":"30","orderAmount":60,"faceValue":50,"newUser":"No","expireDate":"2020-01-12","id":"e27d66aa1d8711eab0c8506b4b231e38","last_updated_date":1576228004000,"status":"Not_Use"},{"no":"02","couponName":"gfd","patientId":"2c9380846ef7ca72016ef8209e0c0000","language":"zh_CN","remark":"fdsaf","creation_date":1576228064000,"couponId":"65cba9d3faf811e9b4600242c0a82002","created_by":"30","last_updated_by":"30","orderAmount":100,"faceValue":90,"newUser":"No","expireDate":"2020-01-12","id":"063c53681d8811eab0c8506b4b231e38","last_updated_date":1576228064000,"status":"Not_Use"}]
         * totalPages : 1
         * dataCount : 6
         */

        private int totalPages;
        private int dataCount;
        private List<CouponListBean> dataList;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getDataCount() {
            return dataCount;
        }

        public void setDataCount(int dataCount) {
            this.dataCount = dataCount;
        }

        public List<CouponListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<CouponListBean> dataList) {
            this.dataList = dataList;
        }

        public static class CouponListBean {
            /**
             * couponName : 安安
             * patientId : 2c9380846ef7ca72016ef8209e0c0000
             * remark : 房贷首付
             * creation_date : 1576662239000
             * couponId : 0287bc62217411eab0c8506b4b231e38
             * created_by : 154
             * last_updated_by : 154
             * orderAmount : 300
             * faceValue : 75
             * newUser : No
             * expireDate : 2020-01-17
             * id : ea8f0871217a11eab0c8506b4b231e38
             * last_updated_date : 1576662239000
             * status : Not_Use
             * no : 02
             * language : zh_CN
             */

            private String couponName;
            private String patientId;
            private String remark;
            private long creation_date;
            private String couponId;
            private String created_by;
            private String last_updated_by;
            private int orderAmount;
            private int faceValue;
            private String newUser;
            private String expireDate;
            private String id;
            private long last_updated_date;
            private String status;
            private String no;
            private String language;

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getCreation_date() {
                return creation_date;
            }

            public void setCreation_date(long creation_date) {
                this.creation_date = creation_date;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public String getCreated_by() {
                return created_by;
            }

            public void setCreated_by(String created_by) {
                this.created_by = created_by;
            }

            public String getLast_updated_by() {
                return last_updated_by;
            }

            public void setLast_updated_by(String last_updated_by) {
                this.last_updated_by = last_updated_by;
            }

            public int getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(int orderAmount) {
                this.orderAmount = orderAmount;
            }

            public int getFaceValue() {
                return faceValue;
            }

            public void setFaceValue(int faceValue) {
                this.faceValue = faceValue;
            }

            public String getNewUser() {
                return newUser;
            }

            public void setNewUser(String newUser) {
                this.newUser = newUser;
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

            public long getLast_updated_date() {
                return last_updated_date;
            }

            public void setLast_updated_date(long last_updated_date) {
                this.last_updated_date = last_updated_date;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }
        }
    }
}

