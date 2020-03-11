package it.swiftelink.com.factory.model.order;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class LogisticsResModel extends BaseResModel {

    /**
     * data : {"result":{"issign":"1","number":"75167529201222","expName":"中通快递","deliverystatus":"3","courier":"","expSite":"www.zto.com ","expPhone":"95311","logo":"http://img3.fegine.com/express/zto.jpg","courierPhone":"13510823531","type":"zto","list":[{"time":"2019-08-15 20:22:09","status":"【深圳市】  已签收, 签收人凭取货码签收, 如有疑问请电联: 13510823531 / 4001787878, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2019-08-15 11:50:21","status":"【深圳市】  快件已送达【菜鸟的深圳布吉丽湖花园13栋2号店】, 如有问题请电联（13510823531 / 4001787878）, 感谢使用中通快递, 期待再次为您服务!"},{"time":"2019-08-15 06:06:26","status":"【深圳市】  【深圳吉华】 的宁太逍（13510823531） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）"},{"time":"2019-08-15 05:13:19","status":"【深圳市】  快件已经到达 【深圳吉华】"},{"time":"2019-08-15 01:26:49","status":"【深圳市】  快件离开 【深圳中心】 已发往 【深圳吉华】"},{"time":"2019-08-15 01:10:23","status":"【深圳市】  快件已经到达 【深圳中心】"},{"time":"2019-08-13 22:51:08","status":"【南京市】  快件离开 【南京中转部】 已发往 【深圳中心】"},{"time":"2019-08-13 22:48:40","status":"【南京市】  快件已经到达 【南京中转部】"},{"time":"2019-08-13 19:35:12","status":"【南京市】  快件离开 【南京龙潭二部】 已发往 【深圳中心】"},{"time":"2019-08-13 16:23:38","status":"【南京市】  【南京龙潭二部】（15094306376） 的 公司客户17号杯子（18851070464） 已揽收"}]},"addressInfo":"广东省深圳市龙岗区山海城市中心"}
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
         * result : {"issign":"1","number":"75167529201222","expName":"中通快递","deliverystatus":"3","courier":"","expSite":"www.zto.com ","expPhone":"95311","logo":"http://img3.fegine.com/express/zto.jpg","courierPhone":"13510823531","type":"zto","list":[{"time":"2019-08-15 20:22:09","status":"【深圳市】  已签收, 签收人凭取货码签收, 如有疑问请电联: 13510823531 / 4001787878, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2019-08-15 11:50:21","status":"【深圳市】  快件已送达【菜鸟的深圳布吉丽湖花园13栋2号店】, 如有问题请电联（13510823531 / 4001787878）, 感谢使用中通快递, 期待再次为您服务!"},{"time":"2019-08-15 06:06:26","status":"【深圳市】  【深圳吉华】 的宁太逍（13510823531） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）"},{"time":"2019-08-15 05:13:19","status":"【深圳市】  快件已经到达 【深圳吉华】"},{"time":"2019-08-15 01:26:49","status":"【深圳市】  快件离开 【深圳中心】 已发往 【深圳吉华】"},{"time":"2019-08-15 01:10:23","status":"【深圳市】  快件已经到达 【深圳中心】"},{"time":"2019-08-13 22:51:08","status":"【南京市】  快件离开 【南京中转部】 已发往 【深圳中心】"},{"time":"2019-08-13 22:48:40","status":"【南京市】  快件已经到达 【南京中转部】"},{"time":"2019-08-13 19:35:12","status":"【南京市】  快件离开 【南京龙潭二部】 已发往 【深圳中心】"},{"time":"2019-08-13 16:23:38","status":"【南京市】  【南京龙潭二部】（15094306376） 的 公司客户17号杯子（18851070464） 已揽收"}]}
         * addressInfo : 广东省深圳市龙岗区山海城市中心
         */

        private ResultBean result;
        private String addressInfo;

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getAddressInfo() {
            return addressInfo;
        }

        public void setAddressInfo(String addressInfo) {
            this.addressInfo = addressInfo;
        }

        public static class ResultBean {
            /**
             * issign : 1
             * number : 75167529201222
             * expName : 中通快递
             * deliverystatus : 3
             * courier :
             * expSite : www.zto.com
             * expPhone : 95311
             * logo : http://img3.fegine.com/express/zto.jpg
             * courierPhone : 13510823531
             * type : zto
             * list : [{"time":"2019-08-15 20:22:09","status":"【深圳市】  已签收, 签收人凭取货码签收, 如有疑问请电联: 13510823531 / 4001787878, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】"},{"time":"2019-08-15 11:50:21","status":"【深圳市】  快件已送达【菜鸟的深圳布吉丽湖花园13栋2号店】, 如有问题请电联（13510823531 / 4001787878）, 感谢使用中通快递, 期待再次为您服务!"},{"time":"2019-08-15 06:06:26","status":"【深圳市】  【深圳吉华】 的宁太逍（13510823531） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）"},{"time":"2019-08-15 05:13:19","status":"【深圳市】  快件已经到达 【深圳吉华】"},{"time":"2019-08-15 01:26:49","status":"【深圳市】  快件离开 【深圳中心】 已发往 【深圳吉华】"},{"time":"2019-08-15 01:10:23","status":"【深圳市】  快件已经到达 【深圳中心】"},{"time":"2019-08-13 22:51:08","status":"【南京市】  快件离开 【南京中转部】 已发往 【深圳中心】"},{"time":"2019-08-13 22:48:40","status":"【南京市】  快件已经到达 【南京中转部】"},{"time":"2019-08-13 19:35:12","status":"【南京市】  快件离开 【南京龙潭二部】 已发往 【深圳中心】"},{"time":"2019-08-13 16:23:38","status":"【南京市】  【南京龙潭二部】（15094306376） 的 公司客户17号杯子（18851070464） 已揽收"}]
             */

            private String issign;
            private String number;
            private String expName;
            private String deliverystatus;
            private String courier;
            private String expSite;
            private String expPhone;
            private String logo;
            private String courierPhone;
            private String type;
            private List<ListBean> list;

            public String getIssign() {
                return issign;
            }

            public void setIssign(String issign) {
                this.issign = issign;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getExpName() {
                return expName;
            }

            public void setExpName(String expName) {
                this.expName = expName;
            }

            public String getDeliverystatus() {
                return deliverystatus;
            }

            public void setDeliverystatus(String deliverystatus) {
                this.deliverystatus = deliverystatus;
            }

            public String getCourier() {
                return courier;
            }

            public void setCourier(String courier) {
                this.courier = courier;
            }

            public String getExpSite() {
                return expSite;
            }

            public void setExpSite(String expSite) {
                this.expSite = expSite;
            }

            public String getExpPhone() {
                return expPhone;
            }

            public void setExpPhone(String expPhone) {
                this.expPhone = expPhone;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getCourierPhone() {
                return courierPhone;
            }

            public void setCourierPhone(String courierPhone) {
                this.courierPhone = courierPhone;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * time : 2019-08-15 20:22:09
                 * status : 【深圳市】  已签收, 签收人凭取货码签收, 如有疑问请电联: 13510823531 / 4001787878, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】
                 */

                private String time;
                private String status;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
