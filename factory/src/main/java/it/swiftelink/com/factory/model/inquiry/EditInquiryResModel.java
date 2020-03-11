package it.swiftelink.com.factory.model.inquiry;

import it.swiftelink.com.common.factory.BaseResModel;

public class EditInquiryResModel extends BaseResModel {


    /**
     * data : {"id":"efefea232412121132","no":"W0021312312","status":1,"usePackage":{"expireDate":1577173465387,"id":"患者套餐卡id","inquiryDuration":30,"inquiryNum":10,"validateDays":1,"inquiryStartTime":"10:00","inquiryEndTime":"11:00","residueNum":1,"useNum":1,"title":"套餐卡标题","type":1,"week":"1,2,3,4,5,6,7"}}
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
         * id : efefea232412121132
         * no : W0021312312
         * status : 1
         * usePackage : {"expireDate":1577173465387,"id":"患者套餐卡id","inquiryDuration":30,"inquiryNum":10,"validateDays":1,"inquiryStartTime":"10:00","inquiryEndTime":"11:00","residueNum":1,"useNum":1,"title":"套餐卡标题","type":1,"week":"1,2,3,4,5,6,7"}
         */

        private String id;
        private String no;
        private String status;
        private UsePackageBean usePackage;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UsePackageBean getUsePackage() {
            return usePackage;
        }

        public void setUsePackage(UsePackageBean usePackage) {
            this.usePackage = usePackage;
        }

        public static class UsePackageBean {
            /**
             * expireDate : 1577173465387
             * id : 患者套餐卡id
             * inquiryDuration : 30
             * inquiryNum : 10
             * validateDays : 1
             * inquiryStartTime : 10:00
             * inquiryEndTime : 11:00
             * residueNum : 1
             * useNum : 1
             * title : 套餐卡标题
             * type : 1
             * week : 1,2,3,4,5,6,7
             */

            private long expireDate;
            private String id;
            private int inquiryDuration;
            private int inquiryNum;
            private int validateDays;
            private String inquiryStartTime;
            private String inquiryEndTime;
            private int residueNum;
            private int useNum;
            private String title;
            private int type;
            private String week;

            public long getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(long expireDate) {
                this.expireDate = expireDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getInquiryDuration() {
                return inquiryDuration;
            }

            public void setInquiryDuration(int inquiryDuration) {
                this.inquiryDuration = inquiryDuration;
            }

            public int getInquiryNum() {
                return inquiryNum;
            }

            public void setInquiryNum(int inquiryNum) {
                this.inquiryNum = inquiryNum;
            }

            public int getValidateDays() {
                return validateDays;
            }

            public void setValidateDays(int validateDays) {
                this.validateDays = validateDays;
            }

            public String getInquiryStartTime() {
                return inquiryStartTime;
            }

            public void setInquiryStartTime(String inquiryStartTime) {
                this.inquiryStartTime = inquiryStartTime;
            }

            public String getInquiryEndTime() {
                return inquiryEndTime;
            }

            public void setInquiryEndTime(String inquiryEndTime) {
                this.inquiryEndTime = inquiryEndTime;
            }

            public int getResidueNum() {
                return residueNum;
            }

            public void setResidueNum(int residueNum) {
                this.residueNum = residueNum;
            }

            public int getUseNum() {
                return useNum;
            }

            public void setUseNum(int useNum) {
                this.useNum = useNum;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }
        }
    }
}
