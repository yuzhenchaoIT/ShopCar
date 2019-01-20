package com.liu.get.e_commerceproject.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.liu.get.e_commerceproject.util.HttpUtil;
import com.liu.get.e_commerceproject.util.HttpUtilInterface;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * date:2018/12/3
 * author:刘振国(Liu)
 * function:
 *
 *  P层
 *  用于做链接网络请求的中间通道
 */
public class MyPersenter {
    //登陆和注册判断手机号格式和密码格式
    public boolean submit(String number, String pwd) {
        final String REGEX_MOBILE ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
        // validate
        if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(pwd)) {
            if (number.matches(REGEX_MOBILE) && pwd.matches(REGEX_PASSWORD)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    //登陆
    public void LoginAndRegistere(String url, Map<String,String> map, final MyPLoginInterface myPLoginInterface){
        new HttpUtil().doPost(url, map, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //Httpget请求
    public void LoaderData_HTTPGET(String url, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doGet(url, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //带请求头的GET请求
    public void HeadGET(String url,String userId,String sessionId, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doHeadGet(url, userId, sessionId, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //put请求  带请求头
    public void PutHttp(String url, Map<String,String> map,String userId,String sessionId, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doHeadPut(url, map, userId, sessionId, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //上传用户头像的接口
    public void postHeadImage(String url, File img, String userId, String sessionId, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doHeadPostImage(url, img, userId, sessionId, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //delet请求
    public void MyHttpDelet(String url, String userId, String sessionId, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doHeadDelete(url,userId, sessionId, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
    //post请求+请求头
    public void PostHeatHttp(String url, Map<String,String> map,String userId,String sessionId, final MyPLoginInterface myPLoginInterface){
        HttpUtil.getInterface().doHeadPost(url, map, userId, sessionId, new HttpUtilInterface() {
            @Override
            public void HttpFailure() {
                myPLoginInterface.HttpFailure();
            }

            @Override
            public void HttpResponse(String json) {
                myPLoginInterface.HttpResponse(json);
            }
        });
    }
}
