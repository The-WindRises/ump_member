package it.swiftelink.com.vcs_member.weight.addressSelector;

import java.util.List;

public class ProviceDataBean {

    private List<ProviceListBean> proviceList;

    public List<ProviceListBean> getProviceList() {
        return proviceList;
    }

    public void setProviceList(List<ProviceListBean> proviceList) {
        this.proviceList = proviceList;
    }

    public static class ProviceListBean {
        /**
         * name : 北京市
         * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]}]
         */

        private String name;
        private List<CityBean> city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 北京市
             * area : ["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]
             */

            private String name;
            private List<String> area;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getArea() {
                return area;
            }

            public void setArea(List<String> area) {
                this.area = area;
            }

            @Override
            public String toString() {
                return "CityBean{" +
                        "name='" + name + '\'' +
                        ", area=" + area +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ProviceListBean{" +
                    "name='" + name + '\'' +
                    ", city=" + city +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProviceDataBean{" +
                "proviceList=" + proviceList +
                '}';
    }
}
