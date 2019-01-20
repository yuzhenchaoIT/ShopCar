package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/8
 * author:刘振国(Liu)
 * function:
 *
 * 搜索页面的bean
 *
 */
public class SearchBean {


    /**
     * result : [{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278,"saleNum":0},{"commodityId":26,"commodityName":"【加绒保暖 软底舒适】蝴蝶结情侣雪地靴平底真皮磨砂加绒冬季保暖豆豆鞋棉鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/2/1.jpg","price":129,"saleNum":0},{"commodityId":31,"commodityName":"【畅销款毛毛鞋+加绒真毛】冬款新尖货时尚防滑耐磨保暖加绒金属搭扣加绒方头低跟毛毛鞋豆豆鞋单鞋女鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/7/1.jpg","price":168,"saleNum":0},{"commodityId":28,"commodityName":"秋季新款女鞋【牛皮】艾斯臣女鞋单鞋蝴蝶结平底单鞋豆豆鞋女加绒保暖小毛球平底女鞋单鞋豆豆鞋女冬女士单鞋毛毛鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/4/1.jpg","price":159,"saleNum":0},{"commodityId":30,"commodityName":"韩版秋冬季甜美蝴蝶结真皮兔毛棉瓢鞋加绒豆豆鞋平底毛毛鞋女单鞋潮","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/6/1.jpg","price":148,"saleNum":0},{"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg","price":139,"saleNum":0}]
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
         * commodityId : 29
         * commodityName : 秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲
         * masterPic : http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg
         * price : 278
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

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

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
