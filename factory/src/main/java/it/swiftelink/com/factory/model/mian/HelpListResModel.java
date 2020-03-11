package it.swiftelink.com.factory.model.mian;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class HelpListResModel extends BaseResModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : bc6e47d7-b9b1-11e9-a23c-0242c0a82002
         * name : 如何修改密码
         * platform : null
         * sort : 4
         * status : null
         * no : 04
         * language : zh_CN
         * createdBy : null
         * creationDate : null
         * lastUpdatedBy : null
         * lastUpdatedDate : null
         * helpList : [{"id":"32bdd6f6b9b211e9a23c0242c0a82002","helpCategoryId":"bc6e47d7-b9b1-11e9-a23c-0242c0a82002","title":"如何修改密码手册","platform":"MembershipSide","content":"http://test.umpclinic.com:8091/web/upload/6e4fe0dc94214a569ebde4bcefb7305c/SWUVD$KUBGJ9WF8A7J`L(NP.png","sort":9,"status":"Yes","no":"01","language":"zh_CN","createdBy":"33","creationDate":1565251061000,"lastUpdatedBy":"33","lastUpdatedDate":1565251061000}]
         */

        private String id;
        private String name;
        private String platform;
        private int sort;
        private String status;
        private String no;
        private String language;
        private String createdBy;
        private String creationDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private List<HelpListBean> helpList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public List<HelpListBean> getHelpList() {
            return helpList;
        }

        public void setHelpList(List<HelpListBean> helpList) {
            this.helpList = helpList;
        }

        public static class HelpListBean {
            /**
             * id : 32bdd6f6b9b211e9a23c0242c0a82002
             * helpCategoryId : bc6e47d7-b9b1-11e9-a23c-0242c0a82002
             * title : 如何修改密码手册
             * platform : MembershipSide
             * content : http://test.umpclinic.com:8091/web/upload/6e4fe0dc94214a569ebde4bcefb7305c/SWUVD$KUBGJ9WF8A7J`L(NP.png
             * sort : 9
             * status : Yes
             * no : 01
             * language : zh_CN
             * createdBy : 33
             * creationDate : 1565251061000
             * lastUpdatedBy : 33
             * lastUpdatedDate : 1565251061000
             */

            private String id;
            private String helpCategoryId;
            private String title;
            private String platform;
            private String content;
            private int sort;
            private String status;
            private String no;
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

            public String getHelpCategoryId() {
                return helpCategoryId;
            }

            public void setHelpCategoryId(String helpCategoryId) {
                this.helpCategoryId = helpCategoryId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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
