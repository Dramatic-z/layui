package com.dnt.cloud.layui.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.dnt.cloud.layui.domain.NrcActivityDomain;
import com.dnt.cloud.layui.service.ActivityService;
import com.dnt.cloud.layui.web.common.BaseAction;
import com.dnt.cloud.layui.web.domain.NrcRateDomain;
import com.dnt.cloud.layui.web.utils.RateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dramatic
 */
@RestController
public class RateController extends BaseAction {

	@Resource
	private ActivityService activityService;


	static LinkedHashMap<String, String> loactionMap = new LinkedHashMap<>();

	public static void initLocationId(){
		//汇率列表
		loactionMap.put("美元",null);
		loactionMap.put("欧元",null);
		loactionMap.put("港币",null);
		loactionMap.put("日元",null);
		loactionMap.put("英镑",null);
		loactionMap.put("澳大利亚元",null);
		loactionMap.put("加拿大元",null);
		loactionMap.put("泰国铢",null);
		loactionMap.put("新加坡元",null);
		loactionMap.put("瑞士法郎",null);
		loactionMap.put("丹麦克朗",null);
		loactionMap.put("澳门元",null);
		loactionMap.put("林吉特",null);
		loactionMap.put("挪威克朗",null);
		loactionMap.put("新西兰元",null);
		loactionMap.put("菲律宾比索",null);
		loactionMap.put("卢布",null);
		loactionMap.put("瑞典克朗",null);
		loactionMap.put("新台币",null);
		loactionMap.put("巴西雷亚尔",null);
		loactionMap.put("韩国元",null);
		loactionMap.put("南非兰特",null);
	};


	/**
	 * 查询汇率
	 * @return
	 */
	@RequestMapping(value = "/rate/queryRate", method = { RequestMethod.GET })
	public String queryRate() {

		logger.info("--->获取汇率");
		NrcActivityDomain domain = activityService.queryActivityById(6L);
		if(domain!=null){
			LinkedHashMap<String, Object> map = JSON.parseObject(domain.getExtension(),LinkedHashMap.class, Feature.OrderedField);

			//Object obj = JSONObject.parse(domain.getExtension());
			return layuiReturnObject(map);
		}
		return layuiReturnObject(null);
	}

	/**
	 * 缓存天气数据
	 * @return
	 */
	@RequestMapping(value = "/rate/cacheRate", method = { RequestMethod.GET })
	public String cacheRate() {
		initLocationId();

		LinkedHashMap<String, NrcRateDomain> newMap = new LinkedHashMap<>();
		JSONObject jsonObject = RateUtils.sendRequestByPath();

		for (Map.Entry<String, String> entry : loactionMap.entrySet()) {
			String country = entry.getKey();
			for (Map.Entry<String, Object> json : jsonObject.entrySet()) {
				Object obj = json.getValue();
				if(obj!=null&&obj.toString().indexOf(country)!=-1){
					NrcRateDomain domain = new NrcRateDomain();
					JSONObject rateObj = (JSONObject) JSONObject.toJSON(obj);
					domain.setBankConversionPri(rateObj.getString("bankConversionPri"));
					domain.setName(rateObj.getString("name"));
					domain.setfBuyPri(rateObj.getString("fBuyPri"));
					domain.setfSellPri(rateObj.getString("fSellPri"));
					domain.setmBuyPri(rateObj.getString("mBuyPri"));
					domain.setmSellPri(rateObj.getString("mSellPri"));
					domain.setTime(rateObj.getString("time"));
					if(country.equals("韩国元")){
						country = "韩元";
					}
					newMap.put(country,domain);
				}
			}
		}
		if(!newMap.isEmpty()){
			String weatherStr = JSON.toJSONString(newMap);
			NrcActivityDomain domain = new NrcActivityDomain();
			domain.setExtension(weatherStr);
			activityService.saveActivity(domain);
		}
		return layuiReturnObject(newMap);
	}


	public static String layuiReturnObject(Object data){
		Map<String, Object> r = new HashMap<>();
		r.put("code", 0);
		r.put("data", data);
		return JSON.toJSONString(r);
	}


}
