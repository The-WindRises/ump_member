package it.swiftelink.com.factory.model.recipe;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class RecipeListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"vaildType":"NORMAL","description":"额鹅鹅鹅","diagnosis":{"no":"7000001000","patientName":"18729553976","doctorName":"陈医生","doctorId":"4","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":"1222","preliminaryDiagnosis":"流行感冒","sectionOfficeName":"外科","id":"4028800b6c6f432d016c6f44e7240000","diagnosisStartTime":1565234882},"id":"c95e9c5fa4fe437d80129e0cbeccbe55","validity":3,"creationDate":1565347851000},{"vaildType":"NORMAL","description":"eee","diagnosis":{"no":"7000038000","patientName":"18729553976","doctorName":"13751116876","doctorId":"2c9380826c709981016c70c7ac66000b","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":"1111","preliminaryDiagnosis":"ee","sectionOfficeName":"外科","id":"2c9380826c7113f0016c715d11bb0017","diagnosisStartTime":1565271508},"id":"3ee69e22d39848c88d128aabf022e1f9","validity":3,"creationDate":1565347840000}],"totalPages":1,"dataCount":2}
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
         * dataList : [{"vaildType":"NORMAL","description":"额鹅鹅鹅","diagnosis":{"no":"7000001000","patientName":"18729553976","doctorName":"陈医生","doctorId":"4","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":"1222","preliminaryDiagnosis":"流行感冒","sectionOfficeName":"外科","id":"4028800b6c6f432d016c6f44e7240000","diagnosisStartTime":1565234882},"id":"c95e9c5fa4fe437d80129e0cbeccbe55","validity":3,"creationDate":1565347851000},{"vaildType":"NORMAL","description":"eee","diagnosis":{"no":"7000038000","patientName":"18729553976","doctorName":"13751116876","doctorId":"2c9380826c709981016c70c7ac66000b","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":"1111","preliminaryDiagnosis":"ee","sectionOfficeName":"外科","id":"2c9380826c7113f0016c715d11bb0017","diagnosisStartTime":1565271508},"id":"3ee69e22d39848c88d128aabf022e1f9","validity":3,"creationDate":1565347840000}]
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
             * vaildType : NORMAL
             * description : 额鹅鹅鹅
             * diagnosis : {"no":"7000001000","patientName":"18729553976","doctorName":"陈医生","doctorId":"4","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":"1222","preliminaryDiagnosis":"流行感冒","sectionOfficeName":"外科","id":"4028800b6c6f432d016c6f44e7240000","diagnosisStartTime":1565234882}
             * id : c95e9c5fa4fe437d80129e0cbeccbe55
             * validity : 3
             * creationDate : 1565347851000
             */

            private String vaildType;
            private String description;
            private DiagnosisBean diagnosis;
            private String id;
            private String validity;
            private long creationDate;

            public String getVaildType() {
                return vaildType;
            }

            public void setVaildType(String vaildType) {
                this.vaildType = vaildType;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getValidity() {
                return validity;
            }

            public void setValidity(String validity) {
                this.validity = validity;
            }

            public long getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(long creationDate) {
                this.creationDate = creationDate;
            }

            public static class DiagnosisBean {
                /**
                 * no : 7000001000
                 * patientName : 18729553976
                 * doctorName : 陈医生
                 * doctorId : 4
                 * patientId : 4028801c6c69b344016c69ca145f0003
                 * medicalRecord : 1222
                 * preliminaryDiagnosis : 流行感冒
                 * sectionOfficeName : 外科
                 * id : 4028800b6c6f432d016c6f44e7240000
                 * diagnosisStartTime : 1565234882
                 */

                private String no;
                private String patientName;
                private String doctorName;
                private String doctorId;
                private String patientId;
                private String medicalRecord;
                private String preliminaryDiagnosis;
                private String sectionOfficeName;
                private String id;
                private long diagnosisStartTime;

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

                public String getMedicalRecord() {
                    return medicalRecord;
                }

                public void setMedicalRecord(String medicalRecord) {
                    this.medicalRecord = medicalRecord;
                }

                public String getPreliminaryDiagnosis() {
                    return preliminaryDiagnosis;
                }

                public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
                    this.preliminaryDiagnosis = preliminaryDiagnosis;
                }

                public String getSectionOfficeName() {
                    return sectionOfficeName;
                }

                public void setSectionOfficeName(String sectionOfficeName) {
                    this.sectionOfficeName = sectionOfficeName;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getDiagnosisStartTime() {
                    return diagnosisStartTime;
                }

                public void setDiagnosisStartTime(long diagnosisStartTime) {
                    this.diagnosisStartTime = diagnosisStartTime;
                }
            }
        }
    }
}
