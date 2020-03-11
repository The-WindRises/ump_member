package it.swiftelink.com.factory.model.recipe;

import com.google.gson.annotations.SerializedName;

public class PrescriptionDrugsBean {

    /**
     * id : f425102fc64b11e998960242c0a82002
     * prescriptionId : 1fc0ad3a107e4edd9c8a37b854d22b0b
     * isOrder : null
     * code : HYA006020D
     * name : 通用 红霉素眼膏 0.5% 2g*2支
     * specification : 0.5% 2g*2支
     * onceMetering : 1.0
     * onceUnit : 袋
     * usageMethod : 口服
     * frequency : 每天1次
     * daysTaken : 1
     * quantity : 1
     * unit : null
     * price : 3.5
     * totalAmount : null
     * type : null
     */

    private String id;
    private String prescriptionId;
    private String isOrder;
    @SerializedName("code")
    private String codeX;
    private String name;
    private String specification;
    private double onceMetering;
    private String onceUnit;
    private String usageMethod;
    private String frequency;
    private int daysTaken;
    private int quantity;
    private String unit;
    private double price;
    private String totalAmount;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getCodeX() {
        return codeX;
    }

    public void setCodeX(String codeX) {
        this.codeX = codeX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getOnceMetering() {
        return onceMetering;
    }

    public void setOnceMetering(double onceMetering) {
        this.onceMetering = onceMetering;
    }

    public String getOnceUnit() {
        return onceUnit;
    }

    public void setOnceUnit(String onceUnit) {
        this.onceUnit = onceUnit;
    }

    public String getUsageMethod() {
        return usageMethod;
    }

    public void setUsageMethod(String usageMethod) {
        this.usageMethod = usageMethod;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
