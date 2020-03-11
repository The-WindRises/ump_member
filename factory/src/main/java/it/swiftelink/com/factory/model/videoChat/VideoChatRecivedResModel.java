package it.swiftelink.com.factory.model.videoChat;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/12 10:23
 */
public class VideoChatRecivedResModel {


    /**
     * cmd : 102
     * sendTime : 1570789694509
     * data : {"acceptType":"DISPATCH","doctorHeadImg":"https://www.umpmedical.com:8877/web/upload/dfec31630e0c456d91526464faaa3091/1566621351935.jpg","doctorId":"DR000029","doctorName":"啦啦啦","no":"7003556000","patientHeadImg":"https://www.umpmedical.com:8877/web/upload/64956db97f8a4137a3d324cf8efd606b/「8.25」可以随机播放好听的中文歌！_109951164206170906.jpg","patientId":"2972c59ae462494abe05918098d54dfe","patientName":"啦啦啦","uuid":"cafa9da9c88a46faadea7dd1c8f67d73"}
     */

    private int cmd;
    private long sendTime;
    private DataBean data;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * acceptType : DISPATCH
         * doctorHeadImg : https://www.umpmedical.com:8877/web/upload/dfec31630e0c456d91526464faaa3091/1566621351935.jpg
         * doctorId : DR000029
         * doctorName : 啦啦啦
         * no : 7003556000
         * patientHeadImg : https://www.umpmedical.com:8877/web/upload/64956db97f8a4137a3d324cf8efd606b/「8.25」可以随机播放好听的中文歌！_109951164206170906.jpg
         * patientId : 2972c59ae462494abe05918098d54dfe
         * patientName : 啦啦啦
         * uuid : cafa9da9c88a46faadea7dd1c8f67d73
         */

        private String acceptType;
        private String doctorHeadImg;
        private String doctorId;
        private String doctorName;
        private String no;
        private String patientHeadImg;
        private String patientId;
        private String patientName;
        private String uuid;

        public String getAcceptType() {
            return acceptType;
        }

        public void setAcceptType(String acceptType) {
            this.acceptType = acceptType;
        }

        public String getDoctorHeadImg() {
            return doctorHeadImg;
        }

        public void setDoctorHeadImg(String doctorHeadImg) {
            this.doctorHeadImg = doctorHeadImg;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getPatientHeadImg() {
            return patientHeadImg;
        }

        public void setPatientHeadImg(String patientHeadImg) {
            this.patientHeadImg = patientHeadImg;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
