package it.swiftelink.com.factory.model.order;

import it.swiftelink.com.common.factory.BaseResModel;

public class ZhifubaoPayConfigResModel extends BaseResModel {

    /**
     * data : {"order":"alipay_sdk=alipay-sdk-java-3.4.49.ALL&app_id=2019062565656072&biz_content=%7B%22body%22%3A%22%E5%A4%84%E6%96%B9%22%2C%22out_trade_no%22%3A%22111%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%A4%84%E6%96%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22140.0000%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fbeta.umpclinic.com%3A8061%2Fapi%2Forder%2Falipay%2Fnotify&sign=H3fbhk2pSr8JnewUW4e0LthxzvvBulGR6X%2BED3M8vkoNjktEPLhVPgDhOiWbrqCZx79QBRYjZ2ftVCzLAeCOPog7Y51oDcNq4KHsZheC9sYV9788YJ6fzjRQHKX3ebv7J0fcTCKzA6ATQi8LH4Pne1E3jJHQn1z5K02mC8NVPd5XgMPQo4h3ZrK%2BR68WacaD9NakZlnFG8riOWlG%2B2NAIvE82XnfqJYsGeUAzp2BFgS3JpyEcbmAvkqXYgML2m%2BsKMlKx8lVo8i3UjvcK8XnKIor%2FOeSCk%2BMszKzzqhFQd0FS3ALSzJPkgm5Td3AMSKhwMvX1jIYABkxi%2FUKPbrUrQ%3D%3D&sign_type=RSA2&timestamp=2019-08-06+17%3A33%3A39&version=1.0"}
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
         * order : alipay_sdk=alipay-sdk-java-3.4.49.ALL&app_id=2019062565656072&biz_content=%7B%22body%22%3A%22%E5%A4%84%E6%96%B9%22%2C%22out_trade_no%22%3A%22111%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%A4%84%E6%96%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22140.0000%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fbeta.umpclinic.com%3A8061%2Fapi%2Forder%2Falipay%2Fnotify&sign=H3fbhk2pSr8JnewUW4e0LthxzvvBulGR6X%2BED3M8vkoNjktEPLhVPgDhOiWbrqCZx79QBRYjZ2ftVCzLAeCOPog7Y51oDcNq4KHsZheC9sYV9788YJ6fzjRQHKX3ebv7J0fcTCKzA6ATQi8LH4Pne1E3jJHQn1z5K02mC8NVPd5XgMPQo4h3ZrK%2BR68WacaD9NakZlnFG8riOWlG%2B2NAIvE82XnfqJYsGeUAzp2BFgS3JpyEcbmAvkqXYgML2m%2BsKMlKx8lVo8i3UjvcK8XnKIor%2FOeSCk%2BMszKzzqhFQd0FS3ALSzJPkgm5Td3AMSKhwMvX1jIYABkxi%2FUKPbrUrQ%3D%3D&sign_type=RSA2&timestamp=2019-08-06+17%3A33%3A39&version=1.0
         */

        private String order;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
