package it.swiftelink.com.factory.model.account;

import java.io.Serializable;
import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class AddressListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"id":"2c9380826c7172b0016c7177d6d10002","patientId":"4028801c6c69b344016c69ca145f0003","name":"啦啦","mobile":"18028089896","province":"北京","city":"海宁市","town":"拱墅区","county":"富阳市","addressDetail":"哈哈哈","isDefault":0,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null},{"id":"2c9380826c718370016c7431895b0062","patientId":"4028801c6c69b344016c69ca145f0003","name":"啦啦啦","mobile":"18729663966","province":"北京(郊区)","city":"海宁市","town":"富阳市","county":"富阳市","addressDetail":"啦啦","isDefault":0,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}],"totalPages":1,"dataCount":2}
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
         * dataList : [{"id":"2c9380826c7172b0016c7177d6d10002","patientId":"4028801c6c69b344016c69ca145f0003","name":"啦啦","mobile":"18028089896","province":"北京","city":"海宁市","town":"拱墅区","county":"富阳市","addressDetail":"哈哈哈","isDefault":0,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null},{"id":"2c9380826c718370016c7431895b0062","patientId":"4028801c6c69b344016c69ca145f0003","name":"啦啦啦","mobile":"18729663966","province":"北京(郊区)","city":"海宁市","town":"富阳市","county":"富阳市","addressDetail":"啦啦","isDefault":0,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}]
         * totalPages : 1
         * dataCount : 2
         */

        private int totalPages;
        private int dataCount;
        private List<DataListBean> dataList;

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

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * id : 2c9380826c7172b0016c7177d6d10002
             * patientId : 4028801c6c69b344016c69ca145f0003
             * name : 啦啦
             * mobile : 18028089896
             * province : 北京
             * city : 海宁市
             * town : 拱墅区
             * county : 富阳市
             * addressDetail : 哈哈哈
             * isDefault : 0
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
}
