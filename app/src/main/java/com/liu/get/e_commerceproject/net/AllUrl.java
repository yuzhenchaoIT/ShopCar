package com.liu.get.e_commerceproject.net;

public class AllUrl {
    //登陆
    public static final String Login_Url = "http://172.17.8.100/small/user/v1/login";
    //注册
    public static final String Registered_Url = "http://172.17.8.100/small/user/v1/register";
    //Banner数据
    public static final String BANNERDATA_URL = "http://172.17.8.100/small/commodity/v1/bannerShow";
    //首页展示的数据
    public static final String HOME_ADTA_URL = "http://172.17.8.100/small/commodity/v1/commodityList";
    //详情页数据
    public static final String DETAILS_URL = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById";
    //详情页评论数据
    public static final String DETAILSCOMMENTS_URL = "http://172.17.8.100/small/commodity/v1/CommodityCommentList";
    //搜索页商品
    public static final String SEARCH_URL = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword";
    //圈子列表
    public static final String CIRCLE_URL = "http://172.17.8.100/small/circle/v1/findCircleList";
    //个人圈子
    public static final String MYCIRCLE_URL = "http://172.17.8.100/small/circle/verify/v1/findMyCircleById";
    //一级类目
    public static final String LEVEL_I_URL = "http://172.17.8.100/small/commodity/v1/findFirstCategory";
    //二级类目;
    public static final String LEVEL_II_URL = "http://172.17.8.100/small/commodity/v1/findSecondCategory";
    //修改昵称
    public static final String UPDATE_NICKNAME_URL = "http://172.17.8.100/small/user/verify/v1/modifyUserNick";
    //修改密码
    public static final String UPDATE_PWD_URL = "http://172.17.8.100/small/user/verify/v1/modifyUserPwd";
    //上传头像
    public static final String UPDATE_HEADIMAGE_URL = "http://172.17.8.100/small/user/verify/v1/modifyHeadPic";
    //查询足迹
    public static final String FOOTPRINT_URL = "http://172.17.8.100/commodity/verify/v1/browseList";
    //查询足迹
    public static final String MYCIRCLEDELETE_URL = "http://172.17.8.100/small/circle/verify/v1/deleteCircle";
    //加入购物车
    public static final String ADDSHOPPINGCAR_URL = "http://172.17.8.100/small/order/verify/v1/syncShoppingCart";
    //查询订单信息
    public static final String ORDER_ALL_URL = "http://172.17.8.100/small/order/verify/v1/findOrderListByStatus";
    //查询用户钱包
    public static final String WALLET_URL = "http://172.17.8.100/small/user/verify/v1/findUserWallet";
    //查询用户收货地址
    public static final String GETADDRESS_URL = "http://172.17.8.100/small/user/verify/v1/receiveAddressList";
    //修改默认收货地址
    public static final String CHANGEGETADDRESS_URL = "http://172.17.8.100/small/user/verify/v1/setDefaultReceiveAddress";
    //修改收货地址
    public static final String UPDATEADDRESS_URL = "http://172.17.8.100/small/user/verify/v1/changeReceiveAddress";
    //新增收货地址
    public static final String ADDADDRESS_URL = "http://172.17.8.100/user/verify/v1/addReceiveAddress";
    //查询购物车
    public static final String GETSHOPPINGCAR_URL = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";
    //创建订单
    public static final String CREATEORDER_URL = "http://172.17.8.100/small/order/verify/v1/createOrder";
    //支付
    public static final String PAYORDER_URL = "http://172.17.8.100/small/order/verify/v1/pay";
    //确认收货
    public static final String RECRIPT_URL = "http://172.17.8.100/small/order/verify/v1/confirmReceipt";
    //删除订单
    public static final String DELETEORDER_URL = "http://172.17.8.100/small/order/verify/v1/deleteOrder";
    //商品评论                   Evaluation
    public static final String EVALUATION_URL = "http://172.17.8.100/commodity/verify/v1/addCommodityComment";
    //发布圈子                  Publish Circle
    public static final String PUBLISH_CIRCLE_URL = "http://172.17.8.100/small/commodity/verify/v1/addCommodityComment";
}
