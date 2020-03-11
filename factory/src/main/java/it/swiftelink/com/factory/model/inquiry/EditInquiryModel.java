package it.swiftelink.com.factory.model.inquiry;

import java.io.Serializable;

public class EditInquiryModel implements Serializable {

    private String id;
    private String patientName;
    private String gender;
    private String birthday;
    private String age;
    private String maritalStatus;
    private String weight;
    private String no;
    private String diagnosisLanguage;
    private String symptomDescription;
    private String status;

    public String getCollectionDoctor() {
        return collectionDoctor;
    }

    public void setCollectionDoctor(String collectionDoctor) {
        this.collectionDoctor = collectionDoctor;
    }

    private String collectionDoctor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDiagnosisLanguage() {
        return diagnosisLanguage;
    }

    public void setDiagnosisLanguage(String diagnosisLanguage) {
        this.diagnosisLanguage = diagnosisLanguage;
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
}
