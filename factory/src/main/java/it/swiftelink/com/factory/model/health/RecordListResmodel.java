package it.swiftelink.com.factory.model.health;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class RecordListResmodel extends BaseResModel {


    /**
     * data : {"dataList":[{"diagnosisReportId":"35b234f3221611eab0c8506b4b231e38","diagnosisId":"2b096308ce3b4f7b9af338e38d5e56c2","reportStatus":"NORMAL","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576225275000},{"diagnosisId":"984af3dca7634229a8faedb748fcd8ee","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576225247000},{"diagnosisId":"d40737de2bbb4c3290a6b275ae0ac9ea","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224887000},{"diagnosisId":"5d204b43bf7a4d2ebd0dbb0ebd8d866f","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224518000},{"diagnosisId":"cb994468137b4b3b861b64fd031c2c35","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224173000},{"diagnosisId":"1a63e03f372c4472a66954ea0b04b99a","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576223919000},{"diagnosisId":"99e530038e764800b506ecb89ac4e847","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205880000},{"diagnosisId":"e64e9466bf39458fa331bfb538d28d64","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205801000},{"diagnosisId":"2f6dd56f265c4fdebc53b40002c019c2","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205700000},{"diagnosisId":"170545eaadb94b74bc7fc65963da60ee","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205378000}],"totalPages":2,"dataCount":16}
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
         * dataList : [{"diagnosisReportId":"35b234f3221611eab0c8506b4b231e38","diagnosisId":"2b096308ce3b4f7b9af338e38d5e56c2","reportStatus":"NORMAL","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576225275000},{"diagnosisId":"984af3dca7634229a8faedb748fcd8ee","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576225247000},{"diagnosisId":"d40737de2bbb4c3290a6b275ae0ac9ea","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224887000},{"diagnosisId":"5d204b43bf7a4d2ebd0dbb0ebd8d866f","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224518000},{"diagnosisId":"cb994468137b4b3b861b64fd031c2c35","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576224173000},{"diagnosisId":"1a63e03f372c4472a66954ea0b04b99a","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576223919000},{"diagnosisId":"99e530038e764800b506ecb89ac4e847","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205880000},{"diagnosisId":"e64e9466bf39458fa331bfb538d28d64","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205801000},{"diagnosisId":"2f6dd56f265c4fdebc53b40002c019c2","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205700000},{"diagnosisId":"170545eaadb94b74bc7fc65963da60ee","symptomDescription":"关节痛,男科问题,儿科问题","diagnosisStartTime":1576205378000}]
         * totalPages : 2
         * dataCount : 16
         */

        private int totalPages;
        private int dataCount;
        private List<RecordInfoBean> dataList;

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

        public List<RecordInfoBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<RecordInfoBean> dataList) {
            this.dataList = dataList;
        }

        public static class RecordInfoBean {
            /**
             * diagnosisReportId : 35b234f3221611eab0c8506b4b231e38
             * diagnosisId : 2b096308ce3b4f7b9af338e38d5e56c2
             * reportStatus : NORMAL
             * symptomDescription : 关节痛,男科问题,儿科问题
             * diagnosisStartTime : 1576225275000
             */

            private String diagnosisReportId;
            private String diagnosisId;
            private String reportStatus;
            private String symptomDescription;
            private long diagnosisStartTime;

            public String getDiagnosisReportId() {
                return diagnosisReportId;
            }

            public void setDiagnosisReportId(String diagnosisReportId) {
                this.diagnosisReportId = diagnosisReportId;
            }

            public String getDiagnosisId() {
                return diagnosisId;
            }

            public void setDiagnosisId(String diagnosisId) {
                this.diagnosisId = diagnosisId;
            }

            public String getReportStatus() {
                return reportStatus;
            }

            public void setReportStatus(String reportStatus) {
                this.reportStatus = reportStatus;
            }

            public String getSymptomDescription() {
                return symptomDescription;
            }

            public void setSymptomDescription(String symptomDescription) {
                this.symptomDescription = symptomDescription;
            }

            public long getDiagnosisStartTime() {
                return diagnosisStartTime;
            }

            public void setDiagnosisStartTime(long diagnosisStartTime) {
                this.diagnosisStartTime = diagnosisStartTime;
            }
        }
    }
}
