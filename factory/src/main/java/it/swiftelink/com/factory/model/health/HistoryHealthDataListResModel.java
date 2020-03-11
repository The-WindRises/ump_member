package it.swiftelink.com.factory.model.health;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class HistoryHealthDataListResModel extends BaseResModel {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {


        private List<DataListBean> dataList;

        private int totalPages;
        private int dataCount;

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getDataCount() {
            return dataCount;
        }

        public void setDataCount(int dataCount) {
            this.dataCount = dataCount;
        }


        public static class DataListBean {
            /**
             * id : 1
             * parentId : 38e8fe63-9e5b-11e9-bb93-0242ac150003
             * height : 167
             * weight : 68
             * temperatureUnderarm : 37
             * temperatureOralCavity : 37
             * temperatureAnus : 37
             * bloodPressureHigh : 100
             * bloodPressureLow : 60
             * bloodSugarFasting : 45
             * bloodSugarAfterMeal : 45
             * bloodSugarRandom : 54
             * pulse : 100
             * headCircumference : 43
             * allergies : 花苞
             * createdBy :
             * creationDate : 1562911718000
             * lastUpdatedBy : null
             * lastUpdatedDate : 1562911732000
             */

            private String id;
            private String parentId;
            private String height;
            private String weight;
            private String temperatureUnderarm;
            private String temperatureOralCavity;
            private String temperatureAnus;
            private String bloodPressureHigh;
            private String bloodPressureLow;
            private String bloodSugarFasting;
            private String bloodSugarAfterMeal;
            private String bloodSugarRandom;
            private String pulse;
            private String headCircumference;
            private String allergies;
            private String createdBy;
            private long creationDate;
            private String lastUpdatedBy;
            private long lastUpdatedDate;

            private String temperature;
            private String bloodPressure;
            private String bloodSugar;

            private String temperatureType;
            private String bloodSugarType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
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

            public String getTemperatureUnderarm() {
                return temperatureUnderarm;
            }

            public void setTemperatureUnderarm(String temperatureUnderarm) {
                this.temperatureUnderarm = temperatureUnderarm;
            }

            public String getTemperatureOralCavity() {
                return temperatureOralCavity;
            }

            public void setTemperatureOralCavity(String temperatureOralCavity) {
                this.temperatureOralCavity = temperatureOralCavity;
            }

            public String getTemperatureAnus() {
                return temperatureAnus;
            }

            public void setTemperatureAnus(String temperatureAnus) {
                this.temperatureAnus = temperatureAnus;
            }

            public String getBloodPressureHigh() {
                return bloodPressureHigh;
            }

            public void setBloodPressureHigh(String bloodPressureHigh) {
                this.bloodPressureHigh = bloodPressureHigh;
            }

            public String getBloodPressureLow() {
                return bloodPressureLow;
            }

            public void setBloodPressureLow(String bloodPressureLow) {
                this.bloodPressureLow = bloodPressureLow;
            }

            public String getBloodSugarFasting() {
                return bloodSugarFasting;
            }

            public void setBloodSugarFasting(String bloodSugarFasting) {
                this.bloodSugarFasting = bloodSugarFasting;
            }

            public String getBloodSugarAfterMeal() {
                return bloodSugarAfterMeal;
            }

            public void setBloodSugarAfterMeal(String bloodSugarAfterMeal) {
                this.bloodSugarAfterMeal = bloodSugarAfterMeal;
            }

            public String getBloodSugarRandom() {
                return bloodSugarRandom;
            }

            public void setBloodSugarRandom(String bloodSugarRandom) {
                this.bloodSugarRandom = bloodSugarRandom;
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

            public String getAllergies() {
                return allergies;
            }

            public void setAllergies(String allergies) {
                this.allergies = allergies;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public long getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(long creationDate) {
                this.creationDate = creationDate;
            }

            public String getLastUpdatedBy() {
                return lastUpdatedBy;
            }

            public void setLastUpdatedBy(String lastUpdatedBy) {
                this.lastUpdatedBy = lastUpdatedBy;
            }

            public long getLastUpdatedDate() {
                return lastUpdatedDate;
            }

            public void setLastUpdatedDate(long lastUpdatedDate) {
                this.lastUpdatedDate = lastUpdatedDate;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getBloodPressure() {
                return bloodPressure;
            }

            public void setBloodPressure(String bloodPressure) {
                this.bloodPressure = bloodPressure;
            }

            public String getBloodSugar() {
                return bloodSugar;
            }

            public void setBloodSugar(String bloodSugar) {
                this.bloodSugar = bloodSugar;
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
        }
    }


}
