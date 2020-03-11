package it.swiftelink.com.factory.model.account;

public class RegisterModel {


    /**
     * mobile : 18729553976
     * password : 123456
     * smsCode : 603512
     * type : 2
     */

    private String mobile;
    private String password;
    private String smsCode;
    private String type;
    private String  agrreementId;

    public String getAgrreementId() {
        return agrreementId;
    }

    public void setAgrreementId(String agrreementId) {
        this.agrreementId = agrreementId;
    }

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

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
