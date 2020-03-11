package it.swiftelink.com.factory.model.inquiry;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class InquiryReportListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"needPrescription":1,"diagnosis":{"no":"7000001000","patientName":"1","doctorName":null,"doctorId":"333","patientId":"test","preliminaryDiagnosis":"1","id":"111","creationDate":null,"symptomDescription":"1"},"id":"1","doctorHeadImg":null}],"totalPages":1,"dataCount":1}
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
         * dataList : [{"needPrescription":1,"diagnosis":{"no":"7000001000","patientName":"1","doctorName":null,"doctorId":"333","patientId":"test","preliminaryDiagnosis":"1","id":"111","creationDate":null,"symptomDescription":"1"},"id":"1","doctorHeadImg":null}]
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

        public static class DataListBean {
            /**
             * needPrescription : 1
             * diagnosis : {"no":"7000001000","patientName":"1","doctorName":null,"doctorId":"333","patientId":"test","preliminaryDiagnosis":"1","id":"111","creationDate":null,"symptomDescription":"1"}
             * id : 1
             * doctorHeadImg : null
             */

            private String needPrescription;
            private DiagnosisBean diagnosis;
            private String id;
            private String doctorHeadImg;

            public String getNeedPrescription() {
                return needPrescription;
            }

            public void setNeedPrescription(String needPrescription) {
                this.needPrescription = needPrescription;
            }

            public DiagnosisBean getDiagnosis() {
                return diagnosis;
            }

            public void setDiagnosis(DiagnosisBean diagnosis) {
                this.diagnosis = diagnosis;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDoctorHeadImg() {
                return doctorHeadImg;
            }

            public void setDoctorHeadImg(String doctorHeadImg) {
                this.doctorHeadImg = doctorHeadImg;
            }

            public static class DiagnosisBean {
                /**
                 * no : 7000001000
                 * patientName : 1
                 * doctorName : null
                 * doctorId : 333
                 * patientId : test
                 * preliminaryDiagnosis : 1
                 * id : 111
                 * creationDate : null
                 * symptomDescription : 1
                 */

                private String no;
                private String patientName;
                private String doctorName;
                private String doctorId;
                private String patientId;
                private String preliminaryDiagnosis;
                private String id;
                private String creationDate;
                private String symptomDescription;

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

                public String getDoctorId() {
                    return doctorId;
                }

                public void setDoctorId(String doctorId) {
                    this.doctorId = doctorId;
                }

                public String getPatientId() {
                    return patientId;
                }

                public void setPatientId(String patientId) {
                    this.patientId = patientId;
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
            }
        }
    }
}
