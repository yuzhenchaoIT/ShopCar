package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 * 主页的所有数据(除去上方的view pager)
 */
public class HomeRec_DataBean {

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        private ArrayList<RxxpBean> rxxp;
        private ArrayList<PzshBean> pzsh;
        private ArrayList<MlssBean> mlss;

        public ArrayList<RxxpBean> getRxxp() {
            return rxxp;
        }

        public void setRxxp(ArrayList<RxxpBean> rxxp) {
            this.rxxp = rxxp;
        }

        public ArrayList<PzshBean> getPzsh() {
            return pzsh;
        }

        public void setPzsh(ArrayList<PzshBean> pzsh) {
            this.pzsh = pzsh;
        }

        public ArrayList<MlssBean> getMlss() {
            return mlss;
        }

        public void setMlss(ArrayList<MlssBean> mlss) {
            this.mlss = mlss;
        }

        public static class RxxpBean {
            private int id;
            private String name;
            private ArrayList<CommodityListBean> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ArrayList<CommodityListBean> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(ArrayList<CommodityListBean> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBean {
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

        public static class PzshBean {
            private int id;
            private String name;
            private ArrayList<CommodityListBeanX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ArrayList<CommodityListBeanX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(ArrayList<CommodityListBeanX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanX {
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

        public static class MlssBean {

            private int id;
            private String name;
            private ArrayList<CommodityListBeanXX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ArrayList<CommodityListBeanXX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(ArrayList<CommodityListBeanXX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanXX {
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
    }
}
