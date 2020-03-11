package it.swiftelink.com.factory.model.InquiryReport;

import it.swiftelink.com.common.factory.BaseResModel;

public class InquiryReportResModel extends BaseResModel {


    /**
     * data : {"allergies":"ee","pastHistory":"ee","diagnosisSuggest":"ee","needPrescription":1,"familyHistory":"ee","diagnosis":{"birthday":"2015-08-07","no":"7000038000","patientName":"18729553976","gender":"FEMALE","patientId":"4028801c6c69b344016c69ca145f0003","preliminaryDiagnosis":"ee","symptomDescription":"啦啦啦","diagnosisStartTime":1565271508000,"doctorName":"13751116876","doctorId":"2c9380826c709981016c70c7ac66000b","sectionOfficeName":"外科","id":"2c9380826c7113f0016c715d11bb0017","age":4,"maritalStatus":"N","status":"INPROGRESS"},"id":"0ae057dcb9e211e9a23c0242c0a82002","currentMedicalHistory":"ee","mainAppeal":"1111"}
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
         * allergies : ee
         * pastHistory : ee
         * diagnosisSuggest : ee
         * needPrescription : 1
         * familyHistory : ee
         * diagnosis : {"birthday":"2015-08-07","no":"7000038000","patientName":"18729553976","gender":"FEMALE","patientId":"4028801c6c69b344016c69ca145f0003","preliminaryDiagnosis":"ee","symptomDescription":"啦啦啦","diagnosisStartTime":1565271508000,"doctorName":"13751116876","doctorId":"2c9380826c709981016c70c7ac66000b","sectionOfficeName":"外科","id":"2c9380826c7113f0016c715d11bb0017","age":4,"maritalStatus":"N","status":"INPROGRESS"}
         * id : 0ae057dcb9e211e9a23c0242c0a82002
         * currentMedicalHistory : ee
         * mainAppeal : 1111
         */

        private String allergies;
        private String pastHistory;
        private String diagnosisSuggest;
        private String needPrescription;
        private String familyHistory;
        private DiagnosisBean diagnosis;
        private String id;
        private String currentMedicalHistory;
        private String mainAppeal;
        private String weight;

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
        }

        public String getPastHistory() {
            return pastHistory;
        }

        public void setPastHistory(String pastHistory) {
            this.pastHistory = pastHistory;
        }

        public String getDiagnosisSuggest() {
            return diagnosisSuggest;
        }

        public void setDiagnosisSuggest(String diagnosisSuggest) {
            this.diagnosisSuggest = diagnosisSuggest;
        }

        public String getNeedPrescription() {
            return needPrescription;
        }

        public void setNeedPrescription(String needPrescription) {
            this.needPrescription = needPrescription;
        }

        public String getFamilyHistory() {
            return familyHistory;
        }

        public void setFamilyHistory(String familyHistory) {
            this.familyHistory = familyHistory;
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

        public String getCurrentMedicalHistory() {
            return currentMedicalHistory;
        }

        public void setCurrentMedicalHistory(String currentMedicalHistory) {
            this.currentMedicalHistory = currentMedicalHistory;
        }

        public String getMainAppeal() {
            return mainAppeal;
        }

        public void setMainAppeal(String mainAppeal) {
            this.mainAppeal = mainAppeal;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public static class DiagnosisBean {
            /**
             * birthday : 2015-08-07
             * no : 7000038000
             * patientName : 18729553976
             * gender : FEMALE
             * patientId : 4028801c6c69b344016c69ca145f0003
             * preliminaryDiagnosis : ee
             * symptomDescription : 啦啦啦
             * diagnosisStartTime : 1565271508000
             * doctorName : 13751116876
             * doctorId : 2c9380826c709981016c70c7ac66000b
             * sectionOfficeName : 外科
             * id : 2c9380826c7113f0016c715d11bb0017
             * age : 4
             * maritalStatus : N
             * status : INPROGRESS
             */

            private String birthday;
            private String no;
            private String medicalRecord;
            private String patientName;
            private String gender;
            private String patientId;
            private String preliminaryDiagnosis;
            private String symptomDescription;
            private long diagnosisStartTime;
            private String doctorName;
            private String doctorId;
            private String sectionOfficeName;
            private String id;
            private String age;
            private String maritalStatus;
            private String status;
            private String weight;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

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

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
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

            public String getSymptomDescription() {
                return symptomDescription;
            }

            public void setSymptomDescription(String symptomDescription) {
                this.symptomDescription = symptomDescription;
            }

            public long getDiagnosisStartTime() {
                return diagnosisStartTime;
            }

            public void setDiagnosisStartTime(long diagnosisStartTime) {
                this.diagnosisStartTime = diagnosisStartTime;
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

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getMedicalRecord() {
                return medicalRecord;
            }

            public void setMedicalRecord(String medicalRecord) {
                this.medicalRecord = medicalRecord;
            }
        }
    }
}
