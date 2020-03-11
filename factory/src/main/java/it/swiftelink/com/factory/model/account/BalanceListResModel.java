package it.swiftelink.com.factory.model.account;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class BalanceListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"id":"402880fc6cc26416016cc26fb8210007","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082415033724","name":"18729553976","amount":1001,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":6599,"createdBy":null,"creationDate":1566630197000,"lastUpdatedBy":null,"lastUpdatedDate":1566630197000},{"id":"402880fc6cc26416016cc26795f30001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414543520","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7600,"createdBy":null,"creationDate":1566629664000,"lastUpdatedBy":null,"lastUpdatedDate":1566629664000},{"id":"402880fc6cc25aee016cc25fe1260001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414453529","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7750,"createdBy":null,"creationDate":1566629159000,"lastUpdatedBy":null,"lastUpdatedDate":1566629159000},{"id":"402880fc6cc250d1016cc255ca380004","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414340182","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7900,"createdBy":null,"creationDate":1566628498000,"lastUpdatedBy":null,"lastUpdatedDate":1566628498000},{"id":"402880fc6cc250d1016cc25513cb0001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414343642","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":8050,"createdBy":null,"creationDate":1566628451000,"lastUpdatedBy":null,"lastUpdatedDate":1566628451000},{"id":"2c9380836cc2035d016cc24ca7a3000a","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414247835","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":8200,"createdBy":null,"creationDate":1566627899000,"lastUpdatedBy":null,"lastUpdatedDate":1566627899000}],"totalPages":1,"dataCount":6}
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
         * dataList : [{"id":"402880fc6cc26416016cc26fb8210007","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082415033724","name":"18729553976","amount":1001,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":6599,"createdBy":null,"creationDate":1566630197000,"lastUpdatedBy":null,"lastUpdatedDate":1566630197000},{"id":"402880fc6cc26416016cc26795f30001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414543520","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7600,"createdBy":null,"creationDate":1566629664000,"lastUpdatedBy":null,"lastUpdatedDate":1566629664000},{"id":"402880fc6cc25aee016cc25fe1260001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414453529","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7750,"createdBy":null,"creationDate":1566629159000,"lastUpdatedBy":null,"lastUpdatedDate":1566629159000},{"id":"402880fc6cc250d1016cc255ca380004","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414340182","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":7900,"createdBy":null,"creationDate":1566628498000,"lastUpdatedBy":null,"lastUpdatedDate":1566628498000},{"id":"402880fc6cc250d1016cc25513cb0001","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414343642","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":8050,"createdBy":null,"creationDate":1566628451000,"lastUpdatedBy":null,"lastUpdatedDate":1566628451000},{"id":"2c9380836cc2035d016cc24ca7a3000a","userId":"2c9380836cb3571e016cb6f1e7b700a6","type":"EXPEND","no":"VC2019082414247835","name":"18729553976","amount":150,"method":"余额支付","bank":null,"receiptAccount":null,"applicationTime":null,"reviewTime":null,"status":null,"rejectReason":null,"doctorAmount":8200,"createdBy":null,"creationDate":1566627899000,"lastUpdatedBy":null,"lastUpdatedDate":1566627899000}]
         * totalPages : 1
         * dataCount : 6
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

        public static class DataListBean {
            /**
             * id : 402880fc6cc26416016cc26fb8210007
             * userId : 2c9380836cb3571e016cb6f1e7b700a6
             * type : EXPEND
             * no : VC2019082415033724
             * name : 18729553976
             * amount : 1001.0
             * method : 余额支付
             * bank : null
             * receiptAccount : null
             * applicationTime : null
             * reviewTime : null
             * status : null
             * rejectReason : null
             * doctorAmount : 6599.0
             * createdBy : null
             * creationDate : 1566630197000
             * lastUpdatedBy : null
             * lastUpdatedDate : 1566630197000
             */

            private String id;
            private String userId;
            private String type;
            private String no;
            private String name;
            private double amount;
            private String method;
            private String bank;
            private String receiptAccount;
            private String applicationTime;
            private String reviewTime;
            private String status;
            private String rejectReason;
            private double doctorAmount;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getReceiptAccount() {
                return receiptAccount;
            }

            public void setReceiptAccount(String receiptAccount) {
                this.receiptAccount = receiptAccount;
            }

            public String getApplicationTime() {
                return applicationTime;
            }

            public void setApplicationTime(String applicationTime) {
                this.applicationTime = applicationTime;
            }

            public String getReviewTime() {
                return reviewTime;
            }

            public void setReviewTime(String reviewTime) {
                this.reviewTime = reviewTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRejectReason() {
                return rejectReason;
            }

            public void setRejectReason(String rejectReason) {
                this.rejectReason = rejectReason;
            }

            public double getDoctorAmount() {
                return doctorAmount;
            }

            public void setDoctorAmount(double doctorAmount) {
                this.doctorAmount = doctorAmount;
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
}
