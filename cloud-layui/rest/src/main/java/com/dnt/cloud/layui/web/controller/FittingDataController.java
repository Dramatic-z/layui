package com.dnt.cloud.layui.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dnt.cloud.common.lang.StringUtil;
import com.dnt.cloud.layui.web.utils.HttpHeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

/**
 * @author dramatic
 */
@Component
public class FittingDataController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否开启推送定时任务
     */
    @Value("${nrc.timed.fitting.target:true}")
    private static String target;
    /**
     * 试衣推送 appKey
     */
    private static String appKey = "8BF28D29";
    /**
     * 试衣推送 appSecret
     */
    private static String appSecret = "6550D66CDB6166ED648BA7B73077A068";
    /**
     * 试衣推送 获取token Url
     */
    private static String tokenUrl = "https://t-dec-api.wedochina.cn/common/auth/token";
    /**
     * 试衣推送 推送试衣数据 Url
     */
    private static String fittingUrl = "https://t-dec-api.wedochina.cn/api/SmartRetail/Fitting";

    public static void main(String[] args) {
        getToken();
    }

    /**
     * 获取token
     */
    public static String getToken() {
        //生成token字符
        String token = appKey + ":" + appSecret;
        //BASE64Encoder
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(token.getBytes());
        encode = "Basic " + encode;
        //发送请求
        String res = HttpHeaderUtil.sendGetRequest(tokenUrl, encode);
        if (StringUtil.isNotBlank(res) && res.contains("code\":0")) {
            Map<String, Object> reMap = JSON.parseObject(res);
            JSONObject result = (JSONObject) JSON.toJSON(reMap.get("result"));
            if (result != null) {
                Object accessToken = result.get("accessToken");
                System.err.println(accessToken.toString());
                return accessToken.toString();
            }
        }
        return null;
    }


}
