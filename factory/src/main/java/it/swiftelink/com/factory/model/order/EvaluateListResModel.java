package it.swiftelink.com.factory.model.order;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class EvaluateListResModel extends BaseResModel {


    /**
     * data : {"dataList":[{"no":"7001682000","patientName":"阿丽","patientScore":5,"doctorStatus":"Evaluation","doctorEvaluation":"差,","type":"Diagnosis","diagnosisStartTime":1566965585000,"doctorName":"啦啦啦","diagnosisId":"2c9380876cd64145016cd66bff8c0009","doctorDate":1566965714000,"doctorScore":2,"id":"2c9380876cd64145016cd66f4e25000d","doctorHeadImg":"https://www.umpmedical.com:8877/web/upload/dfec31630e0c456d91526464faaa3091/1566621351935.jpg","patientHeadImg":"https://www.umpmedical.com:8877/web/upload/15028304c4ef4c28ad89bf268a841733/magazine-unlock-01-2.3.1712-68EDD940C82E54D6A303B78ED19830C3.jpg","patientStatus":"Evaluation","patientEvaluation":"会员5,","patientDate":1566976884000}],"totalPages":1,"dataCount":1}
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
         * dataList : [{"no":"7001682000","patientName":"阿丽","patientScore":5,"doctorStatus":"Evaluation","doctorEvaluation":"差,","type":"Diagnosis","diagnosisStartTime":1566965585000,"doctorName":"啦啦啦","diagnosisId":"2c9380876cd64145016cd66bff8c0009","doctorDate":1566965714000,"doctorScore":2,"id":"2c9380876cd64145016cd66f4e25000d","doctorHeadImg":"https://www.umpmedical.com:8877/web/upload/dfec31630e0c456d91526464faaa3091/1566621351935.jpg","patientHeadImg":"https://www.umpmedical.com:8877/web/upload/15028304c4ef4c28ad89bf268a841733/magazine-unlock-01-2.3.1712-68EDD940C82E54D6A303B78ED19830C3.jpg","patientStatus":"Evaluation","patientEvaluation":"会员5,","patientDate":1566976884000}]
         * totalPages : 1
         * dataCount : 1
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
             * no : 7001682000
             * patientName : 阿丽
             * patientScore : 5
             * doctorStatus : Evaluation
             * doctorEvaluation : 差,
             * type : Diagnosis
             * diagnosisStartTime : 1566965585000
             * doctorName : 啦啦啦
             * diagnosisId : 2c9380876cd64145016cd66bff8c0009
             * doctorDate : 1566965714000
             * doctorScore : 2
             * id : 2c9380876cd64145016cd66f4e25000d
             * doctorHeadImg : https://www.umpmedical.com:8877/web/upload/dfec31630e0c456d91526464faaa3091/1566621351935.jpg
             * patientHeadImg : https://www.umpmedical.com:8877/web/upload/15028304c4ef4c28ad89bf268a841733/magazine-unlock-01-2.3.1712-68EDD940C82E54D6A303B78ED19830C3.jpg
             * patientStatus : Evaluation
             * patientEvaluation : 会员5,
             * patientDate : 1566976884000
             */

            private String no;
            private String patientName;
            private int patientScore;
            private String doctorStatus;
            private String doctorEvaluation;
            private String type;
            private long diagnosisStartTime;
            private String doctorName;
            private String diagnosisId;
            private long doctorDate;
            private int doctorScore;
            private String id;
            private String doctorHeadImg;
            private String patientHeadImg;
            private String patientStatus;
            private String patientEvaluation;
            private String patientDate;

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

            public int getPatientScore() {
                return patientScore;
            }

            public void setPatientScore(int patientScore) {
                this.patientScore = patientScore;
            }

            public String getDoctorStatus() {
                return doctorStatus;
            }

            public void setDoctorStatus(String doctorStatus) {
                this.doctorStatus = doctorStatus;
            }

            public String getDoctorEvaluation() {
                return doctorEvaluation;
            }

            public void setDoctorEvaluation(String doctorEvaluation) {
                this.doctorEvaluation = doctorEvaluation;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getDiagnosisStartTime() {
                return diagnosisStartTime;
            }

            public void setDiagnosisStartTime(long diagnosisStartTime) {
                this.diagnosisStartTime = diagnosisStartTime;
            }

            public String getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
            }

            public String getDiagnosisId() {
                return diagnosisId;
            }

            public void setDiagnosisId(String diagnosisId) {
                this.diagnosisId = diagnosisId;
            }

            public long getDoctorDate() {
                return doctorDate;
            }

            public void setDoctorDate(long doctorDate) {
                this.doctorDate = doctorDate;
            }

            public int getDoctorScore() {
                return doctorScore;
            }

            public void setDoctorScore(int doctorScore) {
                this.doctorScore = doctorScore;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDoctorHeadImg() {
                return doctorHeadImg;
            }

            public void setDoctorHeadImg(String doctorHeadImg) {
                this.doctorHeadImg = doctorHeadImg;
            }

            public String getPatientHeadImg() {
                return patientHeadImg;
            }

            public void setPatientHeadImg(String patientHeadImg) {
                this.patientHeadImg = patientHeadImg;
            }

            public String getPatientStatus() {
                return patientStatus;
            }

            public void setPatientStatus(String patientStatus) {
                this.patientStatus = patientStatus;
            }

            public String getPatientEvaluation() {
                return patientEvaluation;
            }

            public void setPatientEvaluation(String patientEvaluation) {
                this.patientEvaluation = patientEvaluation;
            }

            public String getPatientDate() {
                return patientDate;
            }

            public void setPatientDate(String patientDate) {
                this.patientDate = patientDate;
            }
        }
    }
}
