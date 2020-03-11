package it.swiftelink.com.factory.model.doctor;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class CollectDoctorResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"id":"2c9380836c9d7c3c016c9ec5cfbe004d","userId":129,"name":"洪医生","headImg":"https://www.umpmedical.com:8877/web/upload/ba8ff3fe688342c5bdc391d824897f66/1565788556195.jpg","hospital":"北大深圳医院","birthday":1399996800000,"gender":"Male","age":5,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":null,"jobTitle":"主任医师","level":5,"countryCode":null,"tel":"17688775998","emergencyContact":"张华","emergencyContactTel":null,"contactAddr":"金洲路","birthplace":"陕西省","onlineTime":null,"todayOnlineTime":null,"scheduleTime":null,"lateEarlyTime":null,"amount":0,"doctorStatus":"OpenedVirtualCare","onlineStatus":"Online","accountStatus":"Normal","allowOrder":null,"nature":"PartTime","practiceYear":4,"location":"陕西省安康市汉滨区","language":"Cantonese Mandarin","description":"专职各种不服","createdBy":null,"creationDate":1565785483000,"lastUpdatedBy":"2c9380836c8fde9d016c90166547001b","lastUpdatedDate":1565789230000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"2c9380836c8fde9d016c90166547001b"},{"id":"402880fd6c9ed8ee016c9edf5b050000","userId":32,"name":"vanessa","headImg":"https://www.umpmedical.com:8877/web/upload/8e822f258a244949b41ae549abe240d3/1565667068314.jpg","hospital":"北大深圳医院","birthday":776448000000,"gender":"0","age":25,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":"133675556765","jobTitle":"主任医师","level":5,"countryCode":null,"tel":"+86 13379413275","emergencyContact":"张医师","emergencyContactTel":"13128851436","contactAddr":"三里屯","birthplace":"北京市","onlineTime":180,"todayOnlineTime":30,"scheduleTime":5,"lateEarlyTime":10,"amount":2300,"doctorStatus":"ToCertified","onlineStatus":"Online","accountStatus":"WriteOff","allowOrder":"Yes","nature":"PartTime","practiceYear":1,"location":"北京市北京市东城区","language":"Cantonese","description":"专治各种不服,技术好","createdBy":"32","creationDate":1565081425000,"lastUpdatedBy":"2c9280856c23ee7e016c23f6af9f0001","lastUpdatedDate":1565272660000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"DR000005"},{"id":"402880fd6c9ed8ee016c9edfdb120001","userId":null,"name":"洪安学","headImg":"https://www.umpmedical.com:8877/web/upload/5f94067e02f241fb8fd54eb994686f08/1566025354740.jpg","hospital":"北大深圳医院","birthday":777052800000,"gender":"Male","age":25,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":"133675556765","jobTitle":"主任医师","level":5,"countryCode":null,"tel":"+86 13379413275","emergencyContact":"洪安学","emergencyContactTel":"13379414275","contactAddr":"五里镇","birthplace":"陕西省","onlineTime":180,"todayOnlineTime":30,"scheduleTime":5,"lateEarlyTime":10,"amount":2300,"doctorStatus":"OpenedVirtualCare","onlineStatus":"Online","accountStatus":"WriteOff","allowOrder":"Yes","nature":"FullTime","practiceYear":20,"location":"陕西省安康市汉滨区","language":"Mandarin","description":"专职各种不服","createdBy":"32","creationDate":1565081425000,"lastUpdatedBy":"2c9280856c23ee7e016c23f6af9f0001","lastUpdatedDate":1565272660000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"2c9280856c23ee7e016c23f6af9f0001"}],"totalPages":1,"dataCount":3}
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
         * dataList : [{"id":"2c9380836c9d7c3c016c9ec5cfbe004d","userId":129,"name":"洪医生","headImg":"https://www.umpmedical.com:8877/web/upload/ba8ff3fe688342c5bdc391d824897f66/1565788556195.jpg","hospital":"北大深圳医院","birthday":1399996800000,"gender":"Male","age":5,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":null,"jobTitle":"主任医师","level":5,"countryCode":null,"tel":"17688775998","emergencyContact":"张华","emergencyContactTel":null,"contactAddr":"金洲路","birthplace":"陕西省","onlineTime":null,"todayOnlineTime":null,"scheduleTime":null,"lateEarlyTime":null,"amount":0,"doctorStatus":"OpenedVirtualCare","onlineStatus":"Online","accountStatus":"Normal","allowOrder":null,"nature":"PartTime","practiceYear":4,"location":"陕西省安康市汉滨区","language":"Cantonese Mandarin","description":"专职各种不服","createdBy":null,"creationDate":1565785483000,"lastUpdatedBy":"2c9380836c8fde9d016c90166547001b","lastUpdatedDate":1565789230000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"2c9380836c8fde9d016c90166547001b"},{"id":"402880fd6c9ed8ee016c9edf5b050000","userId":32,"name":"vanessa","headImg":"https://www.umpmedical.com:8877/web/upload/8e822f258a244949b41ae549abe240d3/1565667068314.jpg","hospital":"北大深圳医院","birthday":776448000000,"gender":"0","age":25,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":"133675556765","jobTitle":"主任医师","level":5,"countryCode":null,"tel":"+86 13379413275","emergencyContact":"张医师","emergencyContactTel":"13128851436","contactAddr":"三里屯","birthplace":"北京市","onlineTime":180,"todayOnlineTime":30,"scheduleTime":5,"lateEarlyTime":10,"amount":2300,"doctorStatus":"ToCertified","onlineStatus":"Online","accountStatus":"WriteOff","allowOrder":"Yes","nature":"PartTime","practiceYear":1,"location":"北京市北京市东城区","language":"Cantonese","description":"专治各种不服,技术好","createdBy":"32","creationDate":1565081425000,"lastUpdatedBy":"2c9280856c23ee7e016c23f6af9f0001","lastUpdatedDate":1565272660000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"DR000005"},{"id":"402880fd6c9ed8ee016c9edfdb120001","userId":null,"name":"洪安学","headImg":"https://www.umpmedical.com:8877/web/upload/5f94067e02f241fb8fd54eb994686f08/1566025354740.jpg","hospital":"北大深圳医院","birthday":777052800000,"gender":"Male","age":25,"sectionOfficeId":"379a785e-bda9-11e9-a23c-0242c0a82002","sectionOfficeName":"精神科","sectionOfficeTel":"133675556765","jobTitle":"主任医师","level":5,"countryCode":null,"tel":"+86 13379413275","emergencyContact":"洪安学","emergencyContactTel":"13379414275","contactAddr":"五里镇","birthplace":"陕西省","onlineTime":180,"todayOnlineTime":30,"scheduleTime":5,"lateEarlyTime":10,"amount":2300,"doctorStatus":"OpenedVirtualCare","onlineStatus":"Online","accountStatus":"WriteOff","allowOrder":"Yes","nature":"FullTime","practiceYear":20,"location":"陕西省安康市汉滨区","language":"Mandarin","description":"专职各种不服","createdBy":"32","creationDate":1565081425000,"lastUpdatedBy":"2c9280856c23ee7e016c23f6af9f0001","lastUpdatedDate":1565272660000,"doctorFiles":null,"patientId":null,"type":null,"doctorId":"2c9280856c23ee7e016c23f6af9f0001"}]
         * totalPages : 1
         * dataCount : 3
         */

        private int totalPages;
        private int dataCount;
        private List<DataListBean> dataList;

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

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * id : 2c9380836c9d7c3c016c9ec5cfbe004d
             * userId : 129
             * name : 洪医生
             * headImg : https://www.umpmedical.com:8877/web/upload/ba8ff3fe688342c5bdc391d824897f66/1565788556195.jpg
             * hospital : 北大深圳医院
             * birthday : 1399996800000
             * gender : Male
             * age : 5
             * sectionOfficeId : 379a785e-bda9-11e9-a23c-0242c0a82002
             * sectionOfficeName : 精神科
             * sectionOfficeTel : null
             * jobTitle : 主任医师
             * level : 5
             * countryCode : null
             * tel : 17688775998
             * emergencyContact : 张华
             * emergencyContactTel : null
             * contactAddr : 金洲路
             * birthplace : 陕西省
             * onlineTime : null
             * todayOnlineTime : null
             * scheduleTime : null
             * lateEarlyTime : null
             * amount : 0.0
             * doctorStatus : OpenedVirtualCare
             * onlineStatus : Online
             * accountStatus : Normal
             * allowOrder : null
             * nature : PartTime
             * practiceYear : 4
             * location : 陕西省安康市汉滨区
             * language : Cantonese Mandarin
             * description : 专职各种不服
             * createdBy : null
             * creationDate : 1565785483000
             * lastUpdatedBy : 2c9380836c8fde9d016c90166547001b
             * lastUpdatedDate : 1565789230000
             * doctorFiles : null
             * patientId : null
             * type : null
             * doctorId : 2c9380836c8fde9d016c90166547001b
             */

            private String id;
            private int userId;
            private String name;
            private String headImg;
            private String hospital;
            private long birthday;
            private String gender;
            private int age;
            private String sectionOfficeId;
            private String sectionOfficeName;
            private String sectionOfficeTel;
            private String jobTitle;
            private int level;
            private String countryCode;
            private String tel;
            private String emergencyContact;
            private String emergencyContactTel;
            private String contactAddr;
            private String birthplace;
            private String onlineTime;
            private String todayOnlineTime;
            private String scheduleTime;
            private String lateEarlyTime;
            private double amount;
            private String doctorStatus;
            private String onlineStatus;
            private String accountStatus;
            private String allowOrder;
            private String nature;
            private int practiceYear;
            private String location;
            private String language;
            private String description;
            private String createdBy;
            private long creationDate;
            private String lastUpdatedBy;
            private long lastUpdatedDate;
            private String doctorFiles;
            private String patientId;
            private String type;
            private String doctorId;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getHospital() {
                return hospital;
            }

            public void setHospital(String hospital) {
                this.hospital = hospital;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getSectionOfficeId() {
                return sectionOfficeId;
            }

            public void setSectionOfficeId(String sectionOfficeId) {
                this.sectionOfficeId = sectionOfficeId;
            }

            public String getSectionOfficeName() {
                return sectionOfficeName;
            }

            public void setSectionOfficeName(String sectionOfficeName) {
                this.sectionOfficeName = sectionOfficeName;
            }

            public String getSectionOfficeTel() {
                return sectionOfficeTel;
            }

            public void setSectionOfficeTel(String sectionOfficeTel) {
                this.sectionOfficeTel = sectionOfficeTel;
            }

            public String getJobTitle() {
                return jobTitle;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getEmergencyContact() {
                return emergencyContact;
            }

            public void setEmergencyContact(String emergencyContact) {
                this.emergencyContact = emergencyContact;
            }

            public String getEmergencyContactTel() {
                return emergencyContactTel;
            }

            public void setEmergencyContactTel(String emergencyContactTel) {
                this.emergencyContactTel = emergencyContactTel;
            }

            public String getContactAddr() {
                return contactAddr;
            }

            public void setContactAddr(String contactAddr) {
                this.contactAddr = contactAddr;
            }

            public String getBirthplace() {
                return birthplace;
            }

            public void setBirthplace(String birthplace) {
                this.birthplace = birthplace;
            }

            public String getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(String onlineTime) {
                this.onlineTime = onlineTime;
            }

            public String getTodayOnlineTime() {
                return todayOnlineTime;
            }

            public void setTodayOnlineTime(String todayOnlineTime) {
                this.todayOnlineTime = todayOnlineTime;
            }

            public String getScheduleTime() {
                return scheduleTime;
            }

            public void setScheduleTime(String scheduleTime) {
                this.scheduleTime = scheduleTime;
            }

            public String getLateEarlyTime() {
                return lateEarlyTime;
            }

            public void setLateEarlyTime(String lateEarlyTime) {
                this.lateEarlyTime = lateEarlyTime;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getDoctorStatus() {
                return doctorStatus;
            }

            public void setDoctorStatus(String doctorStatus) {
                this.doctorStatus = doctorStatus;
            }

            public String getOnlineStatus() {
                return onlineStatus;
            }

            public void setOnlineStatus(String onlineStatus) {
                this.onlineStatus = onlineStatus;
            }

            public String getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(String accountStatus) {
                this.accountStatus = accountStatus;
            }

            public String getAllowOrder() {
                return allowOrder;
            }

            public void setAllowOrder(String allowOrder) {
                this.allowOrder = allowOrder;
            }

            public String getNature() {
                return nature;
            }

            public void setNature(String nature) {
                this.nature = nature;
            }

            public int getPracticeYear() {
                return practiceYear;
            }

            public void setPracticeYear(int practiceYear) {
                this.practiceYear = practiceYear;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getDoctorFiles() {
                return doctorFiles;
            }

            public void setDoctorFiles(String doctorFiles) {
                this.doctorFiles = doctorFiles;
            }

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
            }
        }
    }
}
