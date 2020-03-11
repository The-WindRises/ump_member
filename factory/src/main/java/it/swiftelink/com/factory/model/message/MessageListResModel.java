package it.swiftelink.com.factory.model.message;

import java.io.Serializable;
import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class MessageListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"id":"30ec6e62c59b11e998960242c0a82002","userId":"2c9380836cb3571e016cb6f1e7b700a6","title":"视频问诊App即将隆重登场","content":"【联合医务】视频问诊App即将隆重登场. ","status":"Unread","createdBy":"154","creationDate":1566560594000,"lastUpdatedBy":"154","lastUpdatedDate":1566560594000}],"totalPages":1,"dataCount":1}
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
         * dataList : [{"id":"30ec6e62c59b11e998960242c0a82002","userId":"2c9380836cb3571e016cb6f1e7b700a6","title":"视频问诊App即将隆重登场","content":"【联合医务】视频问诊App即将隆重登场. ","status":"Unread","createdBy":"154","creationDate":1566560594000,"lastUpdatedBy":"154","lastUpdatedDate":1566560594000}]
         * totalPages : 1
         * dataCount : 1
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
             * id : 30ec6e62c59b11e998960242c0a82002
             * userId : 2c9380836cb3571e016cb6f1e7b700a6
             * title : 视频问诊App即将隆重登场
             * content : 【联合医务】视频问诊App即将隆重登场.
             * status : Unread
             * createdBy : 154
             * creationDate : 1566560594000
             * lastUpdatedBy : 154
             * lastUpdatedDate : 1566560594000
             */

            private String id;
            private String userId;
            private String title;
            private String content;
            private String status;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
