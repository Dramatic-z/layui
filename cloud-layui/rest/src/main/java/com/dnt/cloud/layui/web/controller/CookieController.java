package com.dnt.cloud.layui.web.controller;

import com.dnt.cloud.layui.web.common.BaseAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author dramatic
 */
@RestController
public class CookieController extends BaseAction {

    /**
     * 测试 Cookie
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/cookie/query", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();

        return new ModelAndView("");
    }


}


