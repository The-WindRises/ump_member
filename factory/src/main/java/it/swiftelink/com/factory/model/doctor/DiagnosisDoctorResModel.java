package it.swiftelink.com.factory.model.doctor;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class DiagnosisDoctorResModel extends BaseResModel {

    /**
     * data : {"dataList":[{"doctorName":"张医生","doctorId":"DR000022","onlineStatus":"Online"},{"doctorName":"李医生","doctorId":"DR000023","onlineStatus":"OnBusy"},{"doctorName":"X医生","doctorId":"DR000025","onlineStatus":"Offline"}],"totalPages":1,"dataCount":3}
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
         * dataList : [{"doctorName":"张医生","doctorId":"DR000022","onlineStatus":"Online"},{"doctorName":"李医生","doctorId":"DR000023","onlineStatus":"OnBusy"},{"doctorName":"X医生","doctorId":"DR000025","onlineStatus":"Offline"}]
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
             * doctorName : 张医生
             * doctorId : DR000022
             * onlineStatus : Online
             */

            private String doctorName;
            private String doctorId;
            private String onlineStatus;

            public String getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
            }

            public String getOnlineStatus() {
                return onlineStatus;
            }

            public void setOnlineStatus(String onlineStatus) {
                this.onlineStatus = onlineStatus;
            }
        }
    }
}
