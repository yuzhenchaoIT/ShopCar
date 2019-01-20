package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/13
 * author:刘振国(Liu)
 * function:
 */
public class AddressBean {


    /**
     * result : [{"address":"中国内陆","createTime":1544750962000,"id":54,"phone":"13412345678","realName":"\u201czhangsan\u201d","userId":104,"whetherDefault":1,"zipCode":"101101"}]
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
         * address : 中国内陆
         * createTime : 1544750962000
         * id : 54
         * phone : 13412345678
         * realName : “zhangsan”
         * userId : 104
         * whetherDefault : 1
         * zipCode : 101101
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
