package it.swiftelink.com.factory.common;

import java.util.List;

public class UploadResModel  {

    private List<FilesBean> files;

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public static class FilesBean {
        /**
         * id : 2c9680826b9c3a7f016c667f2eb9042c
         * name : 1565087705619.jpg
         * type : .jpg
         * path : /home/GitStore/cloud-cms-script/V1.0.0/web/upload/80689d20c637470e950b8582cadec982/1565087705619.jpg
         * size : 14487
         * deleteType : null
         * deleteUrl : null
         * thumbnailUrl : null
         * url : /web/upload/80689d20c637470e950b8582cadec982/1565087705619.jpg
         * error : null
         * scope : null
         * appCode : null
         * creationDate : 1565087706795
         * lastUpdatedDate : 1565087706795
         * createdBy : null
         * lastUpdatedBy : null
         * deleted : false
         */

        private String id;
        private String name;
        private String type;
        private String path;
        private int size;
        private String deleteType;
        private String deleteUrl;
        private String thumbnailUrl;
        private String url;
        private String error;
        private String scope;
        private String appCode;
        private long creationDate;
        private long lastUpdatedDate;
        private String createdBy;
        private String lastUpdatedBy;
        private boolean deleted;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getDeleteType() {
            return deleteType;
        }

        public void setDeleteType(String deleteType) {
            this.deleteType = deleteType;
        }

        public String getDeleteUrl() {
            return deleteUrl;
        }

        public void setDeleteUrl(String deleteUrl) {
            this.deleteUrl = deleteUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
        }

        public long getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(long lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }
}
