package com.dnt.cloud.layui.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dnt.cloud.layui.web.domain.WxPushMessageDomain;
import com.dnt.cloud.layui.web.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 * 微信客服 接入消息推送
 * @author dramatic
 */
@RestController
@RequestMapping("/api/wx/messagePush")
public class ApiWxMessagePush {

    private Logger logger = LoggerFactory.getLogger(ApiWxMessagePush.class);

    /**
     * 消息推送验证消息的确来自微信服务器（验证接口）
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pushUrl", method = RequestMethod.GET)
    public void pushUrl(HttpServletRequest request, HttpServletResponse response) {
        // 验证方法
        pushVerification(request, response);
    }

    /**
     * 验证方法
     */
    public String pushVerification(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下表所示： 参数 描述 signature
         * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 timestamp
         * 时间戳 nonce 随机数 echostr 随机字符串
         */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        boolean checkSignature = WxUtils.CheckSignature(signature, timestamp, nonce);
        if (checkSignature) {
            logger.info("success");
            return echostr;
        } else {
            logger.info("fail");
        }
        return "";
    }

    /**
     * 消息推送（接收接口）
     */
    @RequestMapping(value = "/pushUrl", method = RequestMethod.POST)
    public String pushReceive(@RequestBody WxPushMessageDomain wxPushMessageDomain) {

        logger.info("接收消息为--》{}", JSON.toJSONString(wxPushMessageDomain));

        return "success";
    }
}
