package it.swiftelink.com.factory.model.account;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/10/12 12:00
 */
public class LoginByPhoneModel {

    private String mobile;
    private String smsCode;
    private String type;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmscode() {
        return smsCode;
    }

    public void setSmscode(String smscode) {
        this.smsCode = smscode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
