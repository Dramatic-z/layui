package com.dnt.cloud.layui.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.service.ActivityService;
import com.dnt.cloud.layui.web.common.BaseAction;
import com.dnt.cloud.layui.web.domain.WeatherDomain;
import com.dnt.cloud.layui.web.utils.WeatherUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WeatherController extends BaseAction {

	@Resource
	private ActivityService activityService;


	static LinkedHashMap<String, String> loactionMap = new LinkedHashMap<>();

	public static void initLocationId(){
		//中国 9
		loactionMap.put("上海","101020100");
		loactionMap.put("北京","101010100");
		loactionMap.put("成都","101270101");
		loactionMap.put("广州","101280101");
		loactionMap.put("深圳","101280601");
		loactionMap.put("南京", "101190101");
		loactionMap.put("台北","101340101");
		loactionMap.put("香港","101320101");
		loactionMap.put("澳门","101330101");

		//国外 22
		loactionMap.put("纽约","1E98E");
		loactionMap.put("伦敦","7E2C");
		loactionMap.put("巴黎","7FA1");
		loactionMap.put("首尔","485B3");
		loactionMap.put("东京","65E77");
		loactionMap.put("大阪","83744");
		loactionMap.put("曼谷","EDF82");
		loactionMap.put("柏林","52271");
		loactionMap.put("新加坡","646E3");
		loactionMap.put("芭堤雅","21810");
		loactionMap.put("迪拜","AC806");
		loactionMap.put("威尼斯","575C9");
		loactionMap.put("多伦多","23CA3");
		loactionMap.put("温哥华","EF058");
		loactionMap.put("华盛顿","CD397");
		loactionMap.put("雅典","9D1D9");
		loactionMap.put("罗马","43336");
		loactionMap.put("釜山","E4617");
		loactionMap.put("莫斯科","554F");
		loactionMap.put("悉尼","1BABF");
		loactionMap.put("吉隆坡","64C48");
		loactionMap.put("洛杉矶","516E5");
	};


	/**
	 * 查询天气
	 * @return
	 */
	@RequestMapping(value = "/weather/queryWeather", method = { RequestMethod.GET })
	public String queryWeather() {

		logger.info("--->获取天气");
		NrcActivityDomain domain = activityService.queryActivityById(5L);
		if(domain!=null){
			LinkedHashMap<String, Object> map = JSON.parseObject(domain.getExtension(),LinkedHashMap.class, Feature.OrderedField);

			//Object obj = JSONObject.parse(domain.getExtension());
			return LayuiReturnObject(map);
		}
		return LayuiReturnObject(null);
	}

	/**
	 * 缓存天气数据
	 * @return
	 */
	@RequestMapping(value = "/weather/cacheWeather", method = { RequestMethod.GET })
	public String cacheWeather() {
		initLocationId();

		LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry : loactionMap.entrySet()) {
			WeatherDomain domain = WeatherUtils.sendRequestByPath(entry.getValue());
			newMap.put(entry.getKey(),domain);
		}
		if(!newMap.isEmpty()){
			String weatherStr = JSON.toJSONString(newMap);
			NrcActivityDomain domain = new NrcActivityDomain();
			domain.setExtension(weatherStr);
			activityService.saveActivity(domain);
		}

		return LayuiReturnObject(newMap);
	}


	public static String LayuiReturnObject(Object data){
		Map<String, Object> r = new HashMap<>();
		r.put("code", 0);
		r.put("data", data);
		return JSON.toJSONString(r);
	}


}
