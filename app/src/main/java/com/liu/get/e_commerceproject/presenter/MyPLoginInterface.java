package com.liu.get.e_commerceproject.presenter;

/**
 * date:2018/12/5
 * author:刘振国(Liu)
 * function:
 * P层的接口
 * 分别是成功和失败
 */
public interface MyPLoginInterface {
    void HttpFailure();
    void HttpResponse(String json);
}
