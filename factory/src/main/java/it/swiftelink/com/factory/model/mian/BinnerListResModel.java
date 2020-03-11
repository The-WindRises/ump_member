package it.swiftelink.com.factory.model.mian;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class BinnerListResModel extends BaseResModel {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2f938785c42411e998960242c0a82002
         * title : 5G网络视频时代
         * image : https://www.umpmedical.com:8877/web/upload/4b67a8c74de545e59386838d1e1305a6/banner.png
         * sort : 2
         * linkAddr :
         * bannerType : package
         * packageId : b48fce4aecbd11e996550242c0a82002
         */

        private String id;
        private String title;
        private String image;
        private int sort;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
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
