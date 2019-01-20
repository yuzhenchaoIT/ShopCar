package com.liu.get.e_commerceproject.bean;

import java.util.ArrayList;

/**
 * date:2018/12/7
 * author:刘振国(Liu)
 * function:
 * 详情页的评论的bean
 */
public class DetailsCommentsBeans {


    /**
     * result : [{"commodityId":1,"content":"好评","createTime":1542316468000,"headPic":"http://172.17.8.100/images/small/head_pic/2018-11-17/20181117120315.jpg","image":"url","nickName":"风情的人","userId":1},{"commodityId":1,"content":"好评","createTime":1542225213000,"headPic":"http://172.17.8.100/images/small/head_pic/2018-11-17/20181117120315.jpg","image":"url","nickName":"风情的人","userId":1},{"commodityId":1,"content":"好评","createTime":1542225172000,"headPic":"http://172.17.8.100/images/small/head_pic/2018-11-17/20181117120315.jpg","image":"url","nickName":"风情的人","userId":1}]
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
         * commodityId : 1
         * content : 好评
         * createTime : 1542316468000
         * headPic : http://172.17.8.100/images/small/head_pic/2018-11-17/20181117120315.jpg
         * image : url
         * nickName : 风情的人
         * userId : 1
         */

        private int commodityId;
        private String content;
        private long createTime;
        private String headPic;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
