package it.swiftelink.com.factory.model.account;

import it.swiftelink.com.common.factory.BaseResModel;

public class AddressDetailResModel extends BaseResModel {


    /**
     * data : {"id":"2c9280856c176ccd016c177f0d320000","patientId":null,"name":"测试","mobile":"18828888996","province":"北京","city":"奉化市","town":"富阳市","county":"奉化市","addressDetail":"测试测试","isDefault":1,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}
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
         * id : 2c9280856c176ccd016c177f0d320000
         * patientId : null
         * name : 测试
         * mobile : 18828888996
         * province : 北京
         * city : 奉化市
         * town : 富阳市
         * county : 奉化市
         * addressDetail : 测试测试
         * isDefault : 1
         * createdBy : null
         * creationDate : null
         * lastUpdatedBy : null
         * lastUpdatedDate : null
         */

        private String id;
        private String patientId;
        private String name;
        private String mobile;
        private String province;
        private String city;
        private String town;
        private String county;
        private String addressDetail;
        private int isDefault;
        private String createdBy;
        private String creationDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }
    }
}
