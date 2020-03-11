package it.swiftelink.com.factory.model.user;

import it.swiftelink.com.common.factory.BaseResModel;

public class UserInfoResModel extends BaseResModel {


    /**
     * data : {"id":"2c9280856c23ee7e016c23f3bd650000","userId":33,"headImg":"http://cms.qingbo.co:8091/web/upload/9e6e1de47cf64bdc8bfc1599e2eb002e/touxiang.jpg","mechanismId":null,"name":"18729553976","tel":"18729553976","medicalRecord":"1222","gender":"FeMale","birthday":null,"age":null,"maritalStatus":null,"identityNumber":null,"height":null,"weight":null,"temperature":null,"bloodPressure":null,"bloodSugar":null,"pulse":null,"headCircumference":null,"packageExpireDate":null,"status":null,"packageId":null,"packageName":null,"packageTime":null,"channel":null,"vip":null,"amount":1500,"createdBy":null,"creationDate":null,"lastUpdatedBy":null,"lastUpdatedDate":null}
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
         * id : 2c9280856c23ee7e016c23f3bd650000
         * userId : 33
         * headImg : http://cms.qingbo.co:8091/web/upload/9e6e1de47cf64bdc8bfc1599e2eb002e/touxiang.jpg
         * mechanismId : null
         * name : 18729553976
         * tel : 18729553976
         * medicalRecord : 1222
         * gender : FeMale
         * birthday : null
         * age : null
         * maritalStatus : null
         * identityNumber : null
         * height : null
         * weight : null
         * temperature : null
         * bloodPressure : null
         * bloodSugar : null
         * pulse : null
         * headCircumference : null
         * packageExpireDate : null
         * status : null
         * packageId : null
         * packageName : null
         * packageTime : null
         * channel : null
         * vip : null
         * amount : 1500
         * createdBy : null
         * creationDate : null
         * lastUpdatedBy : null
         * lastUpdatedDate : null
         */

        private String id;
        private int userId;
        private String headImg;
        private String mechanismId;
        private String name;
        private String tel;
        private String medicalRecord;
        private String gender;
        private String birthday;
        private String age;
        private String maritalStatus;
        private String identityNumber;
        private String height;
        private String weight;
        private String temperature;
        private String bloodSugar;
        private String pulse;
        private String headCircumference;
        private String packageExpireDate;
        private String status;
        private String packageId;
        private String packageName;
        private String packageTime;
        private String channel;
        private String vip;
        private double amount;
        private String createdBy;
        private String creationDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private int couponNum;
        private String bloodPressure;
        private String temperatureType;
        private String bloodSugarType;
        private int packageNum;
        private String territory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMechanismId() {
            return mechanismId;
        }

        public void setMechanismId(String mechanismId) {
            this.mechanismId = mechanismId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMedicalRecord() {
            return medicalRecord;
        }

        public void setMedicalRecord(String medicalRecord) {
            this.medicalRecord = medicalRecord;
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

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public String getPulse() {
            return pulse;
        }

        public void setPulse(String pulse) {
            this.pulse = pulse;
        }

        public String getHeadCircumference() {
            return headCircumference;
        }

        public void setHeadCircumference(String headCircumference) {
            this.headCircumference = headCircumference;
        }

        public String getPackageExpireDate() {
            return packageExpireDate;
        }

        public void setPackageExpireDate(String packageExpireDate) {
            this.packageExpireDate = packageExpireDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPackageTime() {
            return packageTime;
        }

        public void setPackageTime(String packageTime) {
            this.packageTime = packageTime;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public String getTemperatureType() {
            return temperatureType;
        }

        public void setTemperatureType(String temperatureType) {
            this.temperatureType = temperatureType;
        }

        public String getBloodSugarType() {
            return bloodSugarType;
        }

        public void setBloodSugarType(String bloodSugarType) {
            this.bloodSugarType = bloodSugarType;
        }

        public int getPackageNum() {
            return packageNum;
        }

        public void setPackageNum(int packageNum) {
            this.packageNum = packageNum;
        }

        public String getTerritory() {
            return territory;
        }

        public void setTerritory(String territory) {
            this.territory = territory;
        }
    }
}
