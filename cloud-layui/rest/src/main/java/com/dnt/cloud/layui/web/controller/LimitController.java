package com.dnt.cloud.layui.web.controller;

import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.service.ActivityService;
import com.dnt.cloud.layui.web.common.LimitRequest;
import com.dnt.cloud.layui.web.utils.LayuiReturnUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author dramatic
 */
@RestController
@RequestMapping("/test")
public class LimitController {

    @Resource
    private ActivityService activityService;

    private static Logger logger = LoggerFactory.getLogger(CalculationExcelController.class);


    /**
     * 测试 接口限制次数 配置
     *
     * @return
     */
    @LimitRequest(count = 10, time = 1000 * 60 * 5)
    @RequestMapping(value = "/LimitTimes", method = {RequestMethod.GET})
    public String LimitTimes() {
        //查询相关内容
        NrcActivityDomain nrcActivityDomain = activityService.queryActivityById(6L);
        return LayuiReturnUtils.LayuiReturndomain("查询成功", nrcActivityDomain);
    }

}
