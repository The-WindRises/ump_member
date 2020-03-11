package it.swiftelink.com.vcs_member.base;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/8/30 21:50
 */
public class Guide  extends SimpleBannerInfo {

    private int resId;

    public Guide(int resId) {
        this.resId = resId;
    }

    @Override
    public Object getXBannerUrl() {
        return getResId();
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


}
