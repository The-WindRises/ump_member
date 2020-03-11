package it.swiftelink.com.factory.model.order;

public class EvaluateModel {


    /**
     * id : string
     * patientScore : 5
     * patientEvaluation : 服务好,
     * patientStatus : Evaluation
     */

    private String id;
    private int patientScore;
    private String patientEvaluation;
    private String patientStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
