package it.swiftelink.com.factory.model.recipe;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class OrderInfoFromOrderIdResModel extends BaseResModel {


    /**
     * data : {"totalAmount":3.5,"prescriptionDrugs":[{"id":"f425102fc64b11e998960242c0a82002","prescriptionId":"1fc0ad3a107e4edd9c8a37b854d22b0b","isOrder":null,"code":"HYA006020D","name":"通用 红霉素眼膏 0.5% 2g*2支","specification":"0.5% 2g*2支","onceMetering":1,"onceUnit":"袋","usageMethod":"口服","frequency":"每天1次","daysTaken":1,"quantity":1,"unit":null,"price":3.5,"totalAmount":null,"type":null}],"prescriptionId":"1fc0ad3a107e4edd9c8a37b854d22b0b","address":{"addressInfo":"天津市天津市南开区啦啦啦","name":"测试","mobile":"18028089896","id":"2c9380836cb917a2016cb932b1780004"},"qty":1}
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
         * totalAmount : 3.5
         * prescriptionDrugs : [{"id":"f425102fc64b11e998960242c0a82002","prescriptionId":"1fc0ad3a107e4edd9c8a37b854d22b0b","isOrder":null,"code":"HYA006020D","name":"通用 红霉素眼膏 0.5% 2g*2支","specification":"0.5% 2g*2支","onceMetering":1,"onceUnit":"袋","usageMethod":"口服","frequency":"每天1次","daysTaken":1,"quantity":1,"unit":null,"price":3.5,"totalAmount":null,"type":null}]
         * prescriptionId : 1fc0ad3a107e4edd9c8a37b854d22b0b
         * address : {"addressInfo":"天津市天津市南开区啦啦啦","name":"测试","mobile":"18028089896","id":"2c9380836cb917a2016cb932b1780004"}
         * qty : 1
         */

        private double totalAmount;
        private String prescriptionId;
        private AddressBean address;
        private int qty;
        private List<PrescriptionDrugsBean> prescriptionDrugs;

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getPrescriptionId() {
            return prescriptionId;
        }

        public void setPrescriptionId(String prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public List<PrescriptionDrugsBean> getPrescriptionDrugs() {
            return prescriptionDrugs;
        }

        public void setPrescriptionDrugs(List<PrescriptionDrugsBean> prescriptionDrugs) {
            this.prescriptionDrugs = prescriptionDrugs;
        }

        public static class AddressBean {
            /**
             * addressInfo : 天津市天津市南开区啦啦啦
             * name : 测试
             * mobile : 18028089896
             * id : 2c9380836cb917a2016cb932b1780004
             */

            private String addressInfo;
            private String name;
            private String mobile;
            private String id;

            public String getAddressInfo() {
                return addressInfo;
            }

            public void setAddressInfo(String addressInfo) {
                this.addressInfo = addressInfo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

    }
}
