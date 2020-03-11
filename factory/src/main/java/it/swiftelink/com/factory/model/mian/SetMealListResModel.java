package it.swiftelink.com.factory.model.mian;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class SetMealListResModel extends BaseResModel {


    /**
     * data : {"list":[{"id":"5fa181c91bf411eab0c8506b4b231e38","validateDays":20,"expireDate":1577721600000,"price":0,"discountPrice":0,"validFlag":1,"title":"测试体验卡哟","type":2,"inquiryStartTime":"03:00","inquiryEndTime":"07:00","activityDueDate":1577721600000,"inquiryDuration":30,"inquiryNum":1,"week":"3,4,5,6","packageInfos":[{"id":"5fac0bd31bf411eab0c8506b4b231e38","packageId":null,"name":"Test experience card","language":"en_US","description":"Test experience card","equityExplain":"Test experience card<br />\r\n<br />","buyNotice":"Test experience card<br />\r\n<br />","commonProblem":"Test experience card<br />\r\n<br />","createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}]}],"pages":1,"total":3}
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
         * list : [{"id":"5fa181c91bf411eab0c8506b4b231e38","validateDays":20,"expireDate":1577721600000,"price":0,"discountPrice":0,"validFlag":1,"title":"测试体验卡哟","type":2,"inquiryStartTime":"03:00","inquiryEndTime":"07:00","activityDueDate":1577721600000,"inquiryDuration":30,"inquiryNum":1,"week":"3,4,5,6","packageInfos":[{"id":"5fac0bd31bf411eab0c8506b4b231e38","packageId":null,"name":"Test experience card","language":"en_US","description":"Test experience card","equityExplain":"Test experience card<br />\r\n<br />","buyNotice":"Test experience card<br />\r\n<br />","commonProblem":"Test experience card<br />\r\n<br />","createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}]}]
         * pages : 1
         * total : 3
         */

        private int pages;
        private int total;
        private List<PackageItemInfoBean> list;

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<PackageItemInfoBean> getList() {
            return list;
        }

        public void setList(List<PackageItemInfoBean> list) {
            this.list = list;
        }

        public static class PackageItemInfoBean implements Serializable {

            private static final long serialVersionUID = -8435629002483187863L;
            /**
             * id : 5fa181c91bf411eab0c8506b4b231e38
             * validateDays : 20
             * expireDate : 1577721600000
             * price : 0
             * discountPrice : 0
             * validFlag : 1
             * title : 测试体验卡哟
             * type : 2
             * inquiryStartTime : 03:00
             * inquiryEndTime : 07:00
             * activityDueDate : 1577721600000
             * inquiryDuration : 30
             * inquiryNum : 1
             * week : 3,4,5,6
             * packageInfos : [{"id":"5fac0bd31bf411eab0c8506b4b231e38","packageId":null,"name":"Test experience card","language":"en_US","description":"Test experience card","equityExplain":"Test experience card<br />\r\n<br />","buyNotice":"Test experience card<br />\r\n<br />","commonProblem":"Test experience card<br />\r\n<br />","createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}]
             */

            private String id;
            private int validateDays;
            private long expireDate;
            private double price;
            private double discountPrice;
            private int validFlag;
            private String title;
            private int type;
            private String inquiryStartTime;
            private String inquiryEndTime;
            private long activityDueDate;
            private int inquiryDuration;
            private int inquiryNum;
            private String week;
            private List<PackageInfosBean> packageInfos;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getValidateDays() {
                return validateDays;
            }

            public void setValidateDays(int validateDays) {
                this.validateDays = validateDays;
            }

            public long getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(long expireDate) {
                this.expireDate = expireDate;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
            }

            public int getValidFlag() {
                return validFlag;
            }

            public void setValidFlag(int validFlag) {
                this.validFlag = validFlag;
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

            public long getActivityDueDate() {
                return activityDueDate;
            }

            public void setActivityDueDate(long activityDueDate) {
                this.activityDueDate = activityDueDate;
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

            public List<PackageInfosBean> getPackageInfos() {
                return packageInfos;
            }

            public void setPackageInfos(List<PackageInfosBean> packageInfos) {
                this.packageInfos = packageInfos;
            }

            public static class PackageInfosBean implements Serializable {
                private static final long serialVersionUID = -7705350530686087960L;
                /**
                 * id : 5fac0bd31bf411eab0c8506b4b231e38
                 * packageId : null
                 * name : Test experience card
                 * language : en_US
                 * description : Test experience card
                 * equityExplain : Test experience card<br />
                 <br />
                 * buyNotice : Test experience card<br />
                 <br />
                 * commonProblem : Test experience card<br />
                 <br />
                 * createdBy : null
                 * creationDate : null
                 * lastUpdatedBy : null
                 * lastUpdatedDate : null
                 */

                private String id;
                private Object packageId;
                private String name;
                private String language;
                private String description;
                private String equityExplain;
                private String buyNotice;
                private String commonProblem;
                private Object createdBy;
                private Object creationDate;
                private Object lastUpdatedBy;
                private Object lastUpdatedDate;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Object getPackageId() {
                    return packageId;
                }

                public void setPackageId(Object packageId) {
                    this.packageId = packageId;
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

                public Object getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(Object createdBy) {
                    this.createdBy = createdBy;
                }

                public Object getCreationDate() {
                    return creationDate;
                }

                public void setCreationDate(Object creationDate) {
                    this.creationDate = creationDate;
                }

                public Object getLastUpdatedBy() {
                    return lastUpdatedBy;
                }

                public void setLastUpdatedBy(Object lastUpdatedBy) {
                    this.lastUpdatedBy = lastUpdatedBy;
                }

                public Object getLastUpdatedDate() {
                    return lastUpdatedDate;
                }

                public void setLastUpdatedDate(Object lastUpdatedDate) {
                    this.lastUpdatedDate = lastUpdatedDate;
                }
            }




        }
    }
}
