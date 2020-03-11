package it.swiftelink.com.factory.model.recipe;

import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;

public class RecipeOrderListResModel extends BaseResModel {

    /**
     * data : {"dataList":[{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"1","creationDate":1564101524000,"status":"DELIVER"},{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"2","creationDate":1562805524000,"status":"DELIVER"},{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"3","creationDate":1518658724000,"status":"DELIVER"}],"totalPages":1,"dataCount":3}
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
         * dataList : [{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"1","creationDate":1564101524000,"status":"DELIVER"},{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"2","creationDate":1562805524000,"status":"DELIVER"},{"no":"111","totalAmount":140,"quantity":12,"perscriptionDrugs":[{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}],"relationId":"da1421f0c1884115bc845d3e1997f5b2","id":"3","creationDate":1518658724000,"status":"DELIVER"}]
         * totalPages : 1
         * dataCount : 3
         */

        private int totalPages;
        private int dataCount;
        private List<DataListBean> dataList;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getDataCount() {
            return dataCount;
        }

        public void setDataCount(int dataCount) {
            this.dataCount = dataCount;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * no : 111
             * totalAmount : 140
             * quantity : 12
             * perscriptionDrugs : [{"quantity":10,"price":10,"name":"测试运营员6.26-1"},{"quantity":2,"price":20,"name":"测试运营员6.26-3"}]
             * relationId : da1421f0c1884115bc845d3e1997f5b2
             * id : 1
             * creationDate : 1564101524000
             * status : DELIVER
             */

            private String no;
            private double totalAmount;
            private double expressPrice;
            private int quantity;
            private String relationId;
            private String id;
            private long creationDate;
            private String status;
            private List<PerscriptionDrugsBean> perscriptionDrugs;

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getRelationId() {
                return relationId;
            }

            public void setRelationId(String relationId) {
                this.relationId = relationId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public long getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(long creationDate) {
                this.creationDate = creationDate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getExpressPrice() {
                return expressPrice;
            }

            public void setExpressPrice(double expressPrice) {
                this.expressPrice = expressPrice;
            }

            public List<PerscriptionDrugsBean> getPerscriptionDrugs() {
                return perscriptionDrugs;
            }

            public void setPerscriptionDrugs(List<PerscriptionDrugsBean> perscriptionDrugs) {
                this.perscriptionDrugs = perscriptionDrugs;
            }

            public static class PerscriptionDrugsBean {
                /**
                 * quantity : 10
                 * price : 10
                 * name : 测试运营员6.26-1
                 */

                private int quantity;
                private double price;
                private String name;

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
