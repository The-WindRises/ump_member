package it.swiftelink.com.factory.model.inquiry;

import it.swiftelink.com.common.factory.BaseResModel;

public class InquiryDetailResModel extends BaseResModel {


    /**
     * data : {"birthday":-1021536000000,"allergies":"测试","no":"7001340000","patientName":"18729553976","gender":"FEMALE","patientId":"4028801c6c69b344016c69ca145f0003","medicalRecord":null,"weight":0,"preliminaryDiagnosis":null,"creationDate":1566222489000,"symptomDescription":"哈哈","diagnosisStartTime":1566222489000,"doctorName":"洪安学","doctorId":"2c9280856c23ee7e016c23f6af9f0001","sectionOfficeName":"精神科","id":"2c9380836caa2224016caa2297480001","age":"82岁","maritalStatus":"N","height":null,"status":"FINISH"}
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
         * birthday : -1021536000000
         * allergies : 测试
         * no : 7001340000
         * patientName : 18729553976
         * gender : FEMALE
         * patientId : 4028801c6c69b344016c69ca145f0003
         * medicalRecord : null
         * weight : 0.0
         * preliminaryDiagnosis : null
         * creationDate : 1566222489000
         * symptomDescription : 哈哈
         * diagnosisStartTime : 1566222489000
         * doctorName : 洪安学
         * doctorId : 2c9280856c23ee7e016c23f6af9f0001
         * sectionOfficeName : 精神科
         * id : 2c9380836caa2224016caa2297480001
         * age : 82岁
         * maritalStatus : N
         * height : null
         * status : FINISH
         */

        private String birthday;
        private String allergies;
        private String no;
        private String patientName;
        private String gender;
        private String patientId;
        private String medicalRecord;
        private String weight;
        private String preliminaryDiagnosis;
        private long creationDate;
        private String symptomDescription;
        private long diagnosisStartTime;
        private String doctorName;
        private String doctorId;
        private String sectionOfficeName;
        private String id;
        private String age;
        private String maritalStatus;
        private String height;
        private String status;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAllergies() {
            return allergies;
        }

        public void setAllergies(String allergies) {
            this.allergies = allergies;
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

        public String getMedicalRecord() {
            return medicalRecord;
        }

        public void setMedicalRecord(String medicalRecord) {
            this.medicalRecord = medicalRecord;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPreliminaryDiagnosis() {
            return preliminaryDiagnosis;
        }

        public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
            this.preliminaryDiagnosis = preliminaryDiagnosis;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
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

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
