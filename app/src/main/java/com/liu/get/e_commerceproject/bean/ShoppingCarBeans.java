package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/14
 * author:刘振国(Liu)
 * function:
 */
public class ShoppingCarBeans {

    /**
     * result : [{"commodityId":162,"commodityName":"冬季新款 牛皮纯色保暖绒里纯色套脚休闲鞋英伦风商务休闲鞋","count":6,"pic":"http://172.17.8.100/images/small/commodity/nx/swxxx/7/1.jpg","price":258}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private ArrayList<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 162
         * commodityName : 冬季新款 牛皮纯色保暖绒里纯色套脚休闲鞋英伦风商务休闲鞋
         * count : 6
         * pic : http://172.17.8.100/images/small/commodity/nx/swxxx/7/1.jpg
         * price : 258
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
