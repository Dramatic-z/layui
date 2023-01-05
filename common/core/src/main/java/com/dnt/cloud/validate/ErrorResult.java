package com.dnt.cloud.validate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.dnt.cloud.core.common.exceptions.ReturnCode;

/**
 * <p>错误结果，为了记录多个错误参数，一次返回</p>
 * @author zc
 * @version $Id: ErrorResult.java, v 0.1 2015年7月8日 下午3:00:36 zhoucong Exp $
 */
public class ErrorResult {

    Map<String, List<String>> errorMap = new LinkedHashMap<String, List<String>>();

    public void add(ReturnCode returnCode, String fieldName) {
        if (!errorMap.containsKey(returnCode.getCode())) {
            errorMap.put(returnCode.getCode(), new ArrayList<String>());
        }
        errorMap.get(returnCode.getCode()).add(fieldName);
    }
    
    public boolean hasError() {
        return !errorMap.isEmpty();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(errorMap);
    }

}
