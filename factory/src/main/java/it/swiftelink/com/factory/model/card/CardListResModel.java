package it.swiftelink.com.factory.model.card;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

/**
 * @Description: 我的套餐卡
 * @Author: klk
 * @CreateDate: 2019/12/27 13:57
 */
public class CardListResModel extends BaseResModel {


    /**
     * data : {"total":10,"pages":10,"list":[{"id":"087b3ddbcf2542cabf2e0842aeb719a7","patientId":"2590c1dc8dda469d98a2d7a3676f4ccf","packageSnapshotId":"6a5e95721cba11eab0c8506b4b231e38","type":2,"expireDate":1577721600000,"useNum":0,"residueNum":1,"validFlag":1,"activateStatus":0,"remark":"备注","price":0,"discountPrice":0,"title":"测试体验卡","validateDays":20,"inquiryDuration":30,"inquiryNum":10,"week":"3,4,5,6","inquiryStartTime":"03:00","inquiryEndTime":"07:00","packageSnapshotInfos":[{"id":"53c661821cbd11eab0c8506b4b231e38","packageSnapshotId":"6a5e95721cba11eab0c8506b4b231e38","name":"测试体验卡","language":"zh_CN","description":"测试体验卡","equityExplain":"测试体验卡","buyNotice":"测试体验卡","commonProblem":"测试体验卡"}]}]}
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
         * total : 10
         * pages : 10
         * list : [{"id":"087b3ddbcf2542cabf2e0842aeb719a7","patientId":"2590c1dc8dda469d98a2d7a3676f4ccf","packageSnapshotId":"6a5e95721cba11eab0c8506b4b231e38","type":2,"expireDate":1577721600000,"useNum":0,"residueNum":1,"validFlag":1,"activateStatus":0,"remark":"备注","price":0,"discountPrice":0,"title":"测试体验卡","validateDays":20,"inquiryDuration":30,"inquiryNum":10,"week":"3,4,5,6","inquiryStartTime":"03:00","inquiryEndTime":"07:00","packageSnapshotInfos":[{"id":"53c661821cbd11eab0c8506b4b231e38","packageSnapshotId":"6a5e95721cba11eab0c8506b4b231e38","name":"测试体验卡","language":"zh_CN","description":"测试体验卡","equityExplain":"测试体验卡","buyNotice":"测试体验卡","commonProblem":"测试体验卡"}]}]
         */

        private int total;
        private int pages;
        private List<CardItemBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<CardItemBean> getList() {
            return list;
        }

        public void setList(List<CardItemBean> list) {
            this.list = list;
        }

        public static class CardItemBean {
            /**
             * id : 087b3ddbcf2542cabf2e0842aeb719a7
             * patientId : 2590c1dc8dda469d98a2d7a3676f4ccf
             * packageSnapshotId : 6a5e95721cba11eab0c8506b4b231e38
             * type : 2
             * expireDate : 1577721600000
             * useNum : 0
             * residueNum : 1
             * validFlag : 1
             * activateStatus : 0
             * remark : 备注
             * price : 0
             * discountPrice : 0
             * title : 测试体验卡
             * validateDays : 20
             * inquiryDuration : 30
             * inquiryNum : 10
             * week : 3,4,5,6
             * inquiryStartTime : 03:00
             * inquiryEndTime : 07:00
             * packageSnapshotInfos : [{"id":"53c661821cbd11eab0c8506b4b231e38","packageSnapshotId":"6a5e95721cba11eab0c8506b4b231e38","name":"测试体验卡","language":"zh_CN","description":"测试体验卡","equityExplain":"测试体验卡","buyNotice":"测试体验卡","commonProblem":"测试体验卡"}]
             */

            private String id;
            private String patientId;
            private String packageSnapshotId;
            private int type;
            private long expireDate;
            private int useNum;
            private int residueNum;
            private int validFlag;
            private int activateStatus;
            private String remark;
            private int price;
            private int discountPrice;
            private String title;
            private int validateDays;
            private int inquiryDuration;
            private int inquiryNum;
            private String week;
            private String inquiryStartTime;
            private String inquiryEndTime;
            private List<PackageSnapshotInfosBean> packageSnapshotInfos;

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

            public String getPackageSnapshotId() {
                return packageSnapshotId;
            }

            public void setPackageSnapshotId(String packageSnapshotId) {
                this.packageSnapshotId = packageSnapshotId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(long expireDate) {
                this.expireDate = expireDate;
            }

            public int getUseNum() {
                return useNum;
            }

            public void setUseNum(int useNum) {
                this.useNum = useNum;
            }

            public int getResidueNum() {
                return residueNum;
            }

            public void setResidueNum(int residueNum) {
                this.residueNum = residueNum;
            }

            public int getValidFlag() {
                return validFlag;
            }

            public void setValidFlag(int validFlag) {
                this.validFlag = validFlag;
            }

            public int getActivateStatus() {
                return activateStatus;
            }

            public void setActivateStatus(int activateStatus) {
                this.activateStatus = activateStatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(int discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getValidateDays() {
                return validateDays;
            }

            public void setValidateDays(int validateDays) {
                this.validateDays = validateDays;
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

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
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

            public List<PackageSnapshotInfosBean> getPackageSnapshotInfos() {
                return packageSnapshotInfos;
            }

            public void setPackageSnapshotInfos(List<PackageSnapshotInfosBean> packageSnapshotInfos) {
                this.packageSnapshotInfos = packageSnapshotInfos;
            }

            public static class PackageSnapshotInfosBean {
                /**
                 * id : 53c661821cbd11eab0c8506b4b231e38
                 * packageSnapshotId : 6a5e95721cba11eab0c8506b4b231e38
                 * name : 测试体验卡
                 * language : zh_CN
                 * description : 测试体验卡
                 * equityExplain : 测试体验卡
                 * buyNotice : 测试体验卡
                 * commonProblem : 测试体验卡
                 */

                private String id;
                private String packageSnapshotId;
                private String name;
                private String language;
                private String description;
                private String equityExplain;
                private String buyNotice;
                private String commonProblem;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPackageSnapshotId() {
                    return packageSnapshotId;
                }

                public void setPackageSnapshotId(String packageSnapshotId) {
                    this.packageSnapshotId = packageSnapshotId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLanguage() {
                    return language;
                }

                public void setLanguage(String language) {
                    this.language = language;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getEquityExplain() {
                    return equityExplain;
                }

                public void setEquityExplain(String equityExplain) {
                    this.equityExplain = equityExplain;
                }

                public String getBuyNotice() {
                    return buyNotice;
                }

                public void setBuyNotice(String buyNotice) {
                    this.buyNotice = buyNotice;
                }

                public String getCommonProblem() {
                    return commonProblem;
                }

                public void setCommonProblem(String commonProblem) {
                    this.commonProblem = commonProblem;
                }
            }
        }
    }
}
