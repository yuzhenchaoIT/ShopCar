package com.liu.get.e_commerceproject.bean;

/**
 * date:2018/12/12
 * author:刘振国(Liu)
 * function:
 *
 * 修改用户头像的bean
 *
 */
public class UpdateHeadImageBeans {
    /**
     * headPath : http://172.17.8.100/images/small/head_pic/2018-12-12/20181212134723.jpg
     * message : 上传成功
     * status : 0000
     */
    private String headPath;
    private String message;
    private String status;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
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
}
