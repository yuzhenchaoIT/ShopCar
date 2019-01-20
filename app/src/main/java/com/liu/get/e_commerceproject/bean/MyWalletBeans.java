package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/13
 * author:刘振国(Liu)
 * function:
 */
public class MyWalletBeans {

    /**
     * result : {"balance":99999999,"detailList":[]}
     * message : 查询成功
     * status : 0000
     */

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
        /**
         * balance : 99999999
         * detailList : []
         */

        private int balance;
        private ArrayList<Redata> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public ArrayList<Redata> getDetailList() {
            return detailList;
        }

        public void setDetailList(ArrayList<Redata> detailList) {
            this.detailList = detailList;
        }

        public class Redata{

            /**
             * amount : 2
             * createTime : 1542476199000
             */

            private int amount;
            private long createTime;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

    }
}
