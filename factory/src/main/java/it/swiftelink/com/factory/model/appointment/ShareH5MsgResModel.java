package it.swiftelink.com.factory.model.appointment;

import it.swiftelink.com.common.factory.BaseResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/25 17:37
 */
public class ShareH5MsgResModel extends BaseResModel {

    /**
     * data : {"id":"ee0e7346db0150bcb20edb99f740b0c7","type":"wzInfo","language":"zh_CN","title":"视频问诊哦","describe":"测试用视频问诊哦","link":"https://www.umpmedical.com:8877/web/upload/1a835f823aa44a3a8a4537a5abffa790/disgnosisInfo.html","imgUrl":"https://www.umpmedical.com:8877/web/upload/e230249d4962486b8fe4c26127daedfe/微信图片_20191025103813.png","creationDate":"2019-10-24 18:23:26"}
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
         * id : ee0e7346db0150bcb20edb99f740b0c7
         * type : wzInfo
         * language : zh_CN
         * title : 视频问诊哦
         * describe : 测试用视频问诊哦
         * link : https://www.umpmedical.com:8877/web/upload/1a835f823aa44a3a8a4537a5abffa790/disgnosisInfo.html
         * imgUrl : https://www.umpmedical.com:8877/web/upload/e230249d4962486b8fe4c26127daedfe/微信图片_20191025103813.png
         * creationDate : 2019-10-24 18:23:26
         */

        private String id;
        private String type;
        private String language;
        private String title;
        private String describe;
        private String link;
        private String imgUrl;
        private String creationDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }
    }
}
