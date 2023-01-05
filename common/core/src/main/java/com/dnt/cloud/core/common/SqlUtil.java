package com.dnt.cloud.core.common;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public class SqlUtil {

    /**
     * <p>null -> null
     * <p>1, 2 -> '1','2'
     * <p>1    -> '1'
     */
    public static String toStrings(String value) {
        Set<String> set = SetUtil.parseString(value);
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        StringBuilder ret = new StringBuilder();
        for (String s : set) {
            ret.append("'").append(s).append("',");
        }
        ret.setLength(ret.length() - 1);
        return ret.toString();
    }
    
    /**
     * 组装 sql中like的字符串条件
     * 输入：a   输出： %a%
     * @param value
     * @return
     */
    public static String getLikeString(String value){
        StringBuffer res = new StringBuffer("%");
        res.append(value);
        res.append("%");
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(toStrings("1"));
        System.out.println(getLikeString("小"));
    }
}
