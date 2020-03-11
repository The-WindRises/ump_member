package it.swiftelink.com.factory.model.doctor;

import it.swiftelink.com.common.factory.BaseResModel;

public class DoctorInfoResModel extends BaseResModel {


    /**
     * data : {"id":"2c9280856c23ee7e016c23f6af9f0002","userId":40,"name":"张医生","headImg":"http://cms.qingbo.co:8091/web/upload/03531bf01a42444c921a60de29d6672c/a.jpg","hospital":"1","birthday":1561438800000,"gender":"1","age":1,"sectionOfficeId":"50bb9c84-98b1-11e9-bb93-0242ac150003","sectionOfficeName":"外科","sectionOfficeTel":"1","jobTitle":"副主任医师","level":5,"tel":"+86 13975097232","emergencyContact":"1","emergencyContactTel":"1","contactAddr":"1","birthplace":"1","onlineTime":1,"todayOnlineTime":1,"scheduleTime":1,"lateEarlyTime":1,"amount":19000,"doctorStatus":"OpenedVirtualCare","onlineStatus":"Online","accountStatus":"WriteOff","allowOrder":"1","nature":"FullTime","practiceYear":1,"location":"新疆维吾尔自治区","language":"Cantonese","description":"1","createdBy":"1","creationDate":1561505420000,"lastUpdatedBy":"99","lastUpdatedDate":1564159790000}
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
         * id : 2c9280856c23ee7e016c23f6af9f0002
         * userId : 40
         * name : 张医生
         * headImg : http://cms.qingbo.co:8091/web/upload/03531bf01a42444c921a60de29d6672c/a.jpg
         * hospital : 1
         * birthday : 1561438800000
         * gender : 1
         * age : 1
         * sectionOfficeId : 50bb9c84-98b1-11e9-bb93-0242ac150003
         * sectionOfficeName : 外科
         * sectionOfficeTel : 1
         * jobTitle : 副主任医师
         * level : 5
         * tel : +86 13975097232
         * emergencyContact : 1
         * emergencyContactTel : 1
         * contactAddr : 1
         * birthplace : 1
         * onlineTime : 1
         * todayOnlineTime : 1
         * scheduleTime : 1
         * lateEarlyTime : 1
         * amount : 19000
         * doctorStatus : OpenedVirtualCare
         * onlineStatus : Online
         * accountStatus : WriteOff
         * allowOrder : 1
         * nature : FullTime
         * practiceYear : 1
         * location : 新疆维吾尔自治区
         * language : Cantonese
         * description : 1
         * createdBy : 1
         * creationDate : 1561505420000
         * lastUpdatedBy : 99
         * lastUpdatedDate : 1564159790000
         * favorite :true
         */

        private String id;
        private int userId;
        private String name;
        private String headImg;
        private String hospital;
        private long birthday;
        private String gender;
        private String age;
        private String sectionOfficeId;
        private String sectionOfficeName;
        private String sectionOfficeTel;
        private String jobTitle;
        private int level;
        private String tel;
        private String emergencyContact;
        private String emergencyContactTel;
        private String contactAddr;
        private String birthplace;
        private long onlineTime;
        private long todayOnlineTime;
        private long scheduleTime;
        private long lateEarlyTime;
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
        private boolean favorite;

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
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

        public long getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(long onlineTime) {
            this.onlineTime = onlineTime;
        }

        public long getTodayOnlineTime() {
            return todayOnlineTime;
        }

        public void setTodayOnlineTime(long todayOnlineTime) {
            this.todayOnlineTime = todayOnlineTime;
        }

        public long getScheduleTime() {
            return scheduleTime;
        }

        public void setScheduleTime(long scheduleTime) {
            this.scheduleTime = scheduleTime;
        }

        public long getLateEarlyTime() {
            return lateEarlyTime;
        }

        public void setLateEarlyTime(long lateEarlyTime) {
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

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }
    }
}
