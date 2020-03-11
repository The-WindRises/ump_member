package it.swiftelink.com.factory.model.inquiry;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class InquiryListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"no":"7000003000","patientName":"test","doctorName":null,"patientId":"test","doctorId":null,"preliminaryDiagnosis":null,"id":"4028800b6c07bf8e016c07de3b990000","creationDate":1563500100000,"symptomDescription":"症状描述","status":"DRAFT","disgnosisReportId":"112","prescriptionId":"efe"},{"no":"7000002000","patientName":"test","doctorName":null,"patientId":"test","doctorId":null,"preliminaryDiagnosis":null,"id":"4028800b6c040257016c040cbff60000","creationDate":1563436040000,"symptomDescription":null,"status":"FINISH","disgnosisReportId":"344","prescriptionId":"ef3e"}],"totalPages":1,"dataCount":2}
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
         * dataList : [{"no":"7000003000","patientName":"test","doctorName":null,"patientId":"test","doctorId":null,"preliminaryDiagnosis":null,"id":"4028800b6c07bf8e016c07de3b990000","creationDate":1563500100000,"symptomDescription":"症状描述","status":"DRAFT","disgnosisReportId":"112","prescriptionId":"efe"},{"no":"7000002000","patientName":"test","doctorName":null,"patientId":"test","doctorId":null,"preliminaryDiagnosis":null,"id":"4028800b6c040257016c040cbff60000","creationDate":1563436040000,"symptomDescription":null,"status":"FINISH","disgnosisReportId":"344","prescriptionId":"ef3e"}]
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

        public static class DataListBean {
            /**
             * no : 7000003000
             * patientName : test
             * doctorName : null
             * patientId : test
             * doctorId : null
             * preliminaryDiagnosis : null
             * id : 4028800b6c07bf8e016c07de3b990000
             * creationDate : 1563500100000
             * symptomDescription : 症状描述
             * status : DRAFT
             * disgnosisReportId : 112
             * prescriptionId : efe
             */

            private String no;
            private String patientName;
            private String doctorName;
            private String patientId;
            private String doctorId;
            private String preliminaryDiagnosis;
            private String id;
            private String creationDate;
            private String lastUpdatedDate;
            private String symptomDescription;
            private String status;
            private String disgnosisReportId;
            private String prescriptionId;
            private String diagnosisStartTime;

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public String getPatientName() {
                return patientName;
            }

            public void setPatientName(String patientName) {
                this.patientName = patientName;
            }

            public String getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
            }

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
            }

            public String getPreliminaryDiagnosis() {
                return preliminaryDiagnosis;
            }

            public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
                this.preliminaryDiagnosis = preliminaryDiagnosis;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(String creationDate) {
                this.creationDate = creationDate;
            }

            public String getSymptomDescription() {
                return symptomDescription;
            }

            public void setSymptomDescription(String symptomDescription) {
                this.symptomDescription = symptomDescription;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDisgnosisReportId() {
                return disgnosisReportId;
            }

            public void setDisgnosisReportId(String disgnosisReportId) {
                this.disgnosisReportId = disgnosisReportId;
            }

            public String getPrescriptionId() {
                return prescriptionId;
            }

            public void setPrescriptionId(String prescriptionId) {
                this.prescriptionId = prescriptionId;
            }

            public String getLastUpdatedDate() {
                return lastUpdatedDate;
            }

            public void setLastUpdatedDate(String lastUpdatedDate) {
                this.lastUpdatedDate = lastUpdatedDate;
            }
            public String getDiagnosisStartTime() {
                return diagnosisStartTime;
            }
            public void setDiagnosisStartTime(String diagnosisStartTime) {
                this.diagnosisStartTime = diagnosisStartTime;
            }
        }
    }
}
