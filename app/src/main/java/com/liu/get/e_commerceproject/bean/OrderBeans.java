package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/14
 * author:刘振国(Liu)
 * function:
 */
public class OrderBeans {
    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg","commodityPrice":139,"orderDetailId":17},{"commentStatus":1,"commodityCount":2,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":18}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20181213093931790227","orderStatus":1,"payAmount":217,"payMethod":1,"userId":227},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg","commodityPrice":139,"orderDetailId":15},{"commentStatus":1,"commodityCount":2,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":16}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20181213093925149227","orderStatus":1,"payAmount":217,"payMethod":1,"userId":227}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private ArrayList<OrderListBean> orderList;

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

    public ArrayList<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg","commodityPrice":139,"orderDetailId":17},{"commentStatus":1,"commodityCount":2,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":18}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20181213093931790227
         * orderStatus : 1
         * payAmount : 217
         * payMethod : 1
         * userId : 227
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private ArrayList<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public ArrayList<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(ArrayList<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 27
             * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
             * commodityPic : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/3/5.jpg
             * commodityPrice : 139
             * orderDetailId : 17
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

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

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
