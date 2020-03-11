package it.swiftelink.com.factory.model.order;

public class SaveEvaluateModel {


    /**
     * diagnosisId : 2c9280856c08eb13016c095295880002
     * orderId :
     * type : Diagnosis
     * initialDiagnosis : 扁桃体发炎
     * doctorScore : 5
     * patientScore : 5
     * doctorEvaluation : 积极配合,表达清晰
     * patientEvaluation : 服务好,华佗转世
     * doctorStatus : Evaluation
     * patientStatus : Evaluation
     */

    private String diagnosisId;
    private String orderId;
    private String type;
    private String initialDiagnosis;
    private int patientScore;
    private String patientEvaluation;
    private String patientStatus;
    private String doctorStatus;

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitialDiagnosis() {
        return initialDiagnosis;
    }

    public void setInitialDiagnosis(String initialDiagnosis) {
        this.initialDiagnosis = initialDiagnosis;
    }

    public int getPatientScore() {
        return patientScore;
    }

    public void setPatientScore(int patientScore) {
        this.patientScore = patientScore;
    }

    public String getPatientEvaluation() {
        return patientEvaluation;
    }

    public void setPatientEvaluation(String patientEvaluation) {
        this.patientEvaluation = patientEvaluation;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(String doctorStatus) {
        this.doctorStatus = doctorStatus;
    }
}
