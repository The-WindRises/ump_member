package it.swiftelink.com.factory.model.mian;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/29 12:13
 */
public class MarkWordsResModel extends BaseResModel {


    /**
     * data : {"hints":["sdfaas","000000000000"],"headlines":[{"id":"7c557f11fa1d11e996550242c0a82002","title":"头条跳转问诊详情","image":null,"sort":null,"linkAddr":"","bannerType":"detail","packageId":null},{"id":"82596d53fa0111e996550242c0a82002","title":"头条跳转套餐卡","image":null,"sort":null,"linkAddr":"","bannerType":"package","packageId":"b48fce4aecbd11e996550242c0a82002"},{"id":"fa0c09bdf9fb11e996550242c0a82002","title":"头条跳转外链","image":null,"sort":null,"linkAddr":"https://www.baidu.com","bannerType":"linkurl","packageId":null}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> hints;
        private List<HeadlinesBean> headlines;

        public List<String> getHints() {
            return hints;
        }

        public void setHints(List<String> hints) {
            this.hints = hints;
        }

        public List<HeadlinesBean> getHeadlines() {
            return headlines;
        }

        public void setHeadlines(List<HeadlinesBean> headlines) {
            this.headlines = headlines;
        }

        public static class HeadlinesBean {
            /**
             * id : 7c557f11fa1d11e996550242c0a82002
             * title : 头条跳转问诊详情
             * image : null
             * sort : null
             * linkAddr :
             * bannerType : detail
             * packageId : null
             */

            private String id;
            private String title;
            private String image;
            private String sort;
            private String linkAddr;
            private String bannerType;
            private String packageId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLinkAddr() {
                return linkAddr;
            }

            public void setLinkAddr(String linkAddr) {
                this.linkAddr = linkAddr;
            }

            public String getBannerType() {
                return bannerType;
            }

            public void setBannerType(String bannerType) {
                this.bannerType = bannerType;
            }

            public String getPackageId() {
                return packageId;
            }

            public void setPackageId(String packageId) {
                this.packageId = packageId;
            }
        }
    }
}
