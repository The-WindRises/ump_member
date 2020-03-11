package it.swiftelink.com.factory.model.recipe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class RecipeInfoResModel extends BaseResModel {


    /**
     * data : {"id":"89925dcb7a93444b8b7d4de663bb2d2c","diagnosisId":"2c9380836cc2035d016cc2dddafd0056","description":"处方说明测试","validity":3,"creationDate":1566639326000,"prescriptionDrugs":[{"id":"7d09a18ec65211e998960242c0a82002","prescriptionId":"89925dcb7a93444b8b7d4de663bb2d2c","isOrder":null,"code":"HYA006020D","name":"通用 红霉素眼膏 0.5% 2g*2支","specification":"0.5% 2g*2支","onceMetering":1,"onceUnit":"袋","usageMethod":"口服","frequency":"每天1次","daysTaken":1,"quantity":1,"unit":null,"price":3.5,"type":null}],"vaildType":"NORMAL"}
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
         * id : 89925dcb7a93444b8b7d4de663bb2d2c
         * diagnosisId : 2c9380836cc2035d016cc2dddafd0056
         * description : 处方说明测试
         * validity : 3
         * creationDate : 1566639326000
         * prescriptionDrugs : [{"id":"7d09a18ec65211e998960242c0a82002","prescriptionId":"89925dcb7a93444b8b7d4de663bb2d2c","isOrder":null,"code":"HYA006020D","name":"通用 红霉素眼膏 0.5% 2g*2支","specification":"0.5% 2g*2支","onceMetering":1,"onceUnit":"袋","usageMethod":"口服","frequency":"每天1次","daysTaken":1,"quantity":1,"unit":null,"price":3.5,"type":null}]
         * vaildType : NORMAL
         */

        private String id;
        private String diagnosisId;
        private String description;
        private int validity;
        private long creationDate;
        private String vaildType;
        private List<PrescriptionDrugsBean> prescriptionDrugs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDiagnosisId() {
            return diagnosisId;
        }

        public void setDiagnosisId(String diagnosisId) {
            this.diagnosisId = diagnosisId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getValidity() {
            return validity;
        }

        public void setValidity(int validity) {
            this.validity = validity;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
        }

        public String getVaildType() {
            return vaildType;
        }

        public void setVaildType(String vaildType) {
            this.vaildType = vaildType;
        }

        public List<PrescriptionDrugsBean> getPrescriptionDrugs() {
            return prescriptionDrugs;
        }

        public void setPrescriptionDrugs(List<PrescriptionDrugsBean> prescriptionDrugs) {
            this.prescriptionDrugs = prescriptionDrugs;
        }

        public static class PrescriptionDrugsBean {
            /**
             * id : 7d09a18ec65211e998960242c0a82002
             * prescriptionId : 89925dcb7a93444b8b7d4de663bb2d2c
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
            private String daysTaken;
            private int quantity;
            private String unit;
            private double price;
            private String type;
            private boolean isCheck;

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

            public String getDaysTaken() {
                return daysTaken;
            }

            public void setDaysTaken(String daysTaken) {
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }
        }
    }
}
