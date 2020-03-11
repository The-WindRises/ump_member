package it.swiftelink.com.factory.model.InquiryReport;

public class CofirmRecipeOrderModel {

    private String prescriptionId;
    private String prescriptionDrugIds;
    private String patientAddressId;
    private String totalAmount;
    private String qty;
    private String expressPrice;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionDrugIds() {
        return prescriptionDrugIds;
    }

    public void setPrescriptionDrugIds(String prescriptionDrugIds) {
        this.prescriptionDrugIds = prescriptionDrugIds;
    }

    public String getPatientAddressId() {
        return patientAddressId;
    }

    public void setPatientAddressId(String patientAddressId) {
        this.patientAddressId = patientAddressId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }
}
