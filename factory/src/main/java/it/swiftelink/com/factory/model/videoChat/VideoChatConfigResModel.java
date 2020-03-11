package it.swiftelink.com.factory.model.videoChat;

import it.swiftelink.com.common.factory.BaseResModel;

public class VideoChatConfigResModel extends BaseResModel {


    /**
     * data : {"no":"7000001000","patientName":"18729553976","doctorName":"张医生","patientId":"2c9280856c23ee7e016c23f3bd650000","doctorId":"2","doctorHeadImg":"http://cms.qingbo.co:8091/web/upload/03531bf01a42444c921a60de29d6672c/a.jpg","uuid":"111","patientHeadImg":"http://cms.qingbo.co:8091/web/upload/9e6e1de47cf64bdc8bfc1599e2eb002e/touxiang.jpg"}
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
         * no : 7000001000
         * patientName : 18729553976
         * doctorName : 张医生
         * patientId : 2c9280856c23ee7e016c23f3bd650000
         * doctorId : 2
         * doctorHeadImg : http://cms.qingbo.co:8091/web/upload/03531bf01a42444c921a60de29d6672c/a.jpg
         * uuid : 111
         * patientHeadImg : http://cms.qingbo.co:8091/web/upload/9e6e1de47cf64bdc8bfc1599e2eb002e/touxiang.jpg
         */

        private String no;
        private String patientName;
        private String doctorName;
        private String patientId;
        private String doctorId;
        private String doctorHeadImg;
        private String uuid;
        private String patientHeadImg;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorHeadImg() {
            return doctorHeadImg;
        }

        public void setDoctorHeadImg(String doctorHeadImg) {
            this.doctorHeadImg = doctorHeadImg;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getPatientHeadImg() {
            return patientHeadImg;
        }

        public void setPatientHeadImg(String patientHeadImg) {
            this.patientHeadImg = patientHeadImg;
        }
    }
}
