package it.swiftelink.com.vcs_member.utils;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class Banner extends SimpleBannerInfo {

    private String imageUrl;
    private String linkAddr;
    private String title;
    private String bannerType;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    private String packageId;

    public Banner() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Banner(String imageUrl, String linkAddr) {
        this.imageUrl = imageUrl;
        this.linkAddr = linkAddr;
    }

    public Banner(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
