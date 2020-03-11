package it.swiftelink.com.factory.model.inquiry;

import it.swiftelink.com.common.factory.BaseResModel;

public class ToInquiryResModel extends BaseResModel {


    /**
     * data : {"info":{"birthday":"1992-01-02","no":"7000002000","diagnosisLanguage":"zh_CN,en_US","gender":"MALE","name":"test","weight":100,"id":"4028800b6bff7362016bff7463080000","symptomDescription":"头晕","age":20,"maritalStatus":"N","height":168}}
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
         * info : {"birthday":"1992-01-02","no":"7000002000","diagnosisLanguage":"zh_CN,en_US","gender":"MALE","name":"test","weight":100,"id":"4028800b6bff7362016bff7463080000","symptomDescription":"头晕","age":20,"maritalStatus":"N","height":168}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * birthday : 1992-01-02
             * no : 7000002000
             * diagnosisLanguage : zh_CN,en_US
             * gender : MALE
             * name : test
             * weight : 100
             * id : 4028800b6bff7362016bff7463080000
             * symptomDescription : 头晕
             * age : 20
             * maritalStatus : N
             * height : 168
             */

            private String birthday;
            private String no;
            private String diagnosisLanguage;
            private String gender;
            private String name;
            private double weight;
            private String id;
            private String symptomDescription;
            private String age;
            private String maritalStatus;
            private double height;

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
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

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSymptomDescription() {
                return symptomDescription;
            }

            public void setSymptomDescription(String symptomDescription) {
                this.symptomDescription = symptomDescription;
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

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }
        }
    }
}
