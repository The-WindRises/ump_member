package it.swiftelink.com.factory.model.account;

import java.io.Serializable;

public class LoginModel implements Serializable {


    private String mobile;
    private String password;
    private String meid;
    private String openId;
    private String unionId;
    private int extType;
    private String headImg;
    private String userName;
    private String smsCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getOpen_id() {
        return openId;
    }

    public void setOpen_id(String open_id) {
        this.openId = open_id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public int getExtType() {
        return extType;
    }

    public void setExtType(int extType) {
        this.extType = extType;
    }
}
