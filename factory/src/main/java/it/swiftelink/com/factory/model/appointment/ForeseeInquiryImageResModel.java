package it.swiftelink.com.factory.model.appointment;

import it.swiftelink.com.common.factory.BaseResModel;

public class ForeseeInquiryImageResModel extends BaseResModel {


    /**
     * data : {"id":"e06be497-9c9c-11e9-bb93-0242ac150003","propagandaImg":null,"detailsImg":"/web/upload/d1b4c55fd0894554908e0465adc09ead/banner@3x.png","packageCardImg":null,"productAdvantageImg":"/web/upload/f71657bb07674571919127e25c2b89f9/1.png","usageNoticeImg":"/web/upload/900b86e823ba45a3868de6bd94a7b743/使用须知@3x.png","commonProblemImg":"/web/upload/e7cb21728e494c05b17dc839776c8bd6/常见问题@3x.png","propagandaItemImg":null,"buyNoticeImg":null,"language":"zh_CN","createdBy":"32","creationDate":1562053320000,"lastUpdatedBy":"32","lastUpdatedDate":1562053320000}
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
         * id : e06be497-9c9c-11e9-bb93-0242ac150003
         * propagandaImg : null
         * detailsImg : /web/upload/d1b4c55fd0894554908e0465adc09ead/banner@3x.png
         * packageCardImg : null
         * productAdvantageImg : /web/upload/f71657bb07674571919127e25c2b89f9/1.png
         * usageNoticeImg : /web/upload/900b86e823ba45a3868de6bd94a7b743/使用须知@3x.png
         * commonProblemImg : /web/upload/e7cb21728e494c05b17dc839776c8bd6/常见问题@3x.png
         * propagandaItemImg : null
         * buyNoticeImg : null
         * language : zh_CN
         * createdBy : 32
         * creationDate : 1562053320000
         * lastUpdatedBy : 32
         * lastUpdatedDate : 1562053320000
         */

        private String id;
        private String propagandaImg;
        private String detailsImg;
        private String packageCardImg;
        private String productAdvantageImg;
        private String usageNoticeImg;
        private String commonProblemImg;
        private String propagandaItemImg;
        private String buyNoticeImg;
        private String language;
        private String createdBy;
        private long creationDate;
        private String lastUpdatedBy;
        private long lastUpdatedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPropagandaImg() {
            return propagandaImg;
        }

        public void setPropagandaImg(String propagandaImg) {
            this.propagandaImg = propagandaImg;
        }

        public String getDetailsImg() {
            return detailsImg;
        }

        public void setDetailsImg(String detailsImg) {
            this.detailsImg = detailsImg;
        }

        public String getPackageCardImg() {
            return packageCardImg;
        }

        public void setPackageCardImg(String packageCardImg) {
            this.packageCardImg = packageCardImg;
        }

        public String getProductAdvantageImg() {
            return productAdvantageImg;
        }

        public void setProductAdvantageImg(String productAdvantageImg) {
            this.productAdvantageImg = productAdvantageImg;
        }

        public String getUsageNoticeImg() {
            return usageNoticeImg;
        }

        public void setUsageNoticeImg(String usageNoticeImg) {
            this.usageNoticeImg = usageNoticeImg;
        }

        public String getCommonProblemImg() {
            return commonProblemImg;
        }

        public void setCommonProblemImg(String commonProblemImg) {
            this.commonProblemImg = commonProblemImg;
        }

        public String getPropagandaItemImg() {
            return propagandaItemImg;
        }

        public void setPropagandaItemImg(String propagandaItemImg) {
            this.propagandaItemImg = propagandaItemImg;
        }

        public String getBuyNoticeImg() {
            return buyNoticeImg;
        }

        public void setBuyNoticeImg(String buyNoticeImg) {
            this.buyNoticeImg = buyNoticeImg;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public long getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(long lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }
    }
}
