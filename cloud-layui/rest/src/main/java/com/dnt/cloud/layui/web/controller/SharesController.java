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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dramatic
 */
@RestController
public class SharesController extends BaseAction {

	@Resource
	private ActivityService activityService;


	static LinkedHashMap<String, String> loactionMap = new LinkedHashMap<>();

	public static void initLocationId(){
		//汇率列表
		//http://hq.sinajs.cn/list=sh000001,sz399001
		loactionMap.put("上证指数","sh000001");
		loactionMap.put("深证成指","sz399001");
		loactionMap.put("创业板指","sz399006");

		//int_hangseng
		loactionMap.put("香港恒生指数","HSI");

		//gb_ixic 多数据，int 数据少
		//http://hq.sinajs.cn/list=int_dji
		loactionMap.put("道琼斯","DJIA");
		//http://hq.sinajs.cn/list=int_nasdaq
		loactionMap.put("纳斯达克","IXIC");
		//http://hq.sinajs.cn/list=int_sp500
		loactionMap.put("标普500指数","INX");
		//http://hq.sinajs.cn/list=int_nikkei
		loactionMap.put("日经225指数","N225");


		loactionMap.put("英国富时100","b_UKX");
		loactionMap.put("德国DAX指数","b_DAX");
		loactionMap.put("法国CAC40指数","b_CAC");
		loactionMap.put("俄罗斯MICEX指数","b_INDEXCF");
		loactionMap.put("台湾加权指数","znb_TWJQ");
	};


	/**
	 * 查询汇率
	 * @return
	 */
	@RequestMapping(value = "/share/queryRate", method = { RequestMethod.GET })
	public String queryRate() {

		logger.info("--->获取汇率");
		NrcActivityDomain domain = activityService.queryActivityById(4L);
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
	@RequestMapping(value = "/share/cacheRate", method = { RequestMethod.GET })
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
