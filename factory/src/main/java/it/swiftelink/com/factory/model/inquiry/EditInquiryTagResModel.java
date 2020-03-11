package it.swiftelink.com.factory.model.inquiry;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/9/24 14:49
 */
public class EditInquiryTagResModel extends BaseResModel {


    /**
     * data : {"totalPages":2,"symptomDescription":["头痛","男科问题","儿科问题","腹部不适","关节痛","咽喉不适","妇科问题","皮肤问题","发烧","咳嗽"],"dataCount":15}
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
         * totalPages : 2
         * symptomDescription : ["头痛","男科问题","儿科问题","腹部不适","关节痛","咽喉不适","妇科问题","皮肤问题","发烧","咳嗽"]
         * dataCount : 15
         */

        private int totalPages;
        private int dataCount;
        private List<String> symptomDescription;

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

        public List<String> getSymptomDescription() {
            return symptomDescription;
        }

        public void setSymptomDescription(List<String> symptomDescription) {
            this.symptomDescription = symptomDescription;
        }
    }
}
