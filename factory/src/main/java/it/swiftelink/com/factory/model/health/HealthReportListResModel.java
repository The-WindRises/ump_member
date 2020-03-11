package it.swiftelink.com.factory.model.health;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class HealthReportListResModel extends BaseResModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * patientId : 1
         * type : 1
         * name : 影像报告数据
         * fileId : null
         * imageLocation : img
         * hospital : xxx医院
         * sectionOfficeId : 6610b6f2-a15a-11e9-8fe8-0242c0a82002
         * appearance : 影像
         * conclusion : 影像数据
         * createdBy : null
         * creationDate : 1563868377000
         * lastUpdatedBy : null
         * lastUpdatedDate : 1562261853000
         */

        private String id;
        private String patientId;
        private String type;
        private String name;
        private String fileId;
        private String imageLocation;
        private String hospital;
        private String sectionOfficeId;
        private String sectionOfficeName;
        private String appearance;
        private String conclusion;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getImageLocation() {
            return imageLocation;
        }

        public void setImageLocation(String imageLocation) {
            this.imageLocation = imageLocation;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getSectionOfficeId() {
            return sectionOfficeId;
        }

        public void setSectionOfficeId(String sectionOfficeId) {
            this.sectionOfficeId = sectionOfficeId;
        }

        public String getAppearance() {
            return appearance;
        }

        public void setAppearance(String appearance) {
            this.appearance = appearance;
        }

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
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

        public String getSectionOfficeName() {
            return sectionOfficeName;
        }

        public void setSectionOfficeName(String sectionOfficeName) {
            this.sectionOfficeName = sectionOfficeName;
        }
    }
}
