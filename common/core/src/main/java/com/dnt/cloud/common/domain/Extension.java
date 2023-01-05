package com.dnt.cloud.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dnt.cloud.common.lang.StringUtil;

/**
 * <p>扩展信息</p>
 * @author sean won
 * @version $Id: Extension.java, v 0.1 2010-12-18 上午10:59:46 fuyangbiao Exp $
 */
public class Extension implements Serializable {
    private static final long serialVersionUID = 8034857888313309344L;

    /** 扩展信息列表 */
    private List<Kvp>         entryList        = new ArrayList<Kvp>();

    /**
     * 增加键值
     * @param key
     * @param value
     */
    public void add(String key, String value) {
        this.remove(key);
        this.entryList.add(new Kvp(key, value));
    }

    /**
     * 根据键对象获取值对象
     * @param key
     * @return
     */
    public String getValue(String key) {
        if (entryList == null || entryList.size() == 0) {
            return null;
        }
        for (Kvp kvp : entryList) {
            if (kvp == null) {
                continue;
            }
            if (StringUtil.equals(kvp.getKey(), key)) {
                return kvp.getValue();
            }
        }

        return null;
    }

    /**
     * 剔除键值
     * @param key
     */
    public void remove(String key) {
        if (entryList == null || entryList.size() == 0) {
            return;
        }
        for (Iterator<Kvp> it = entryList.iterator(); it.hasNext();) {
            Kvp kvp = it.next();
            if (kvp == null) {
                continue;
            }
            if (StringUtil.equals(kvp.getKey(), key)) {
                it.remove();
                return;
            }
        }
    }

    public List<Kvp> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Kvp> entryList) {
        this.entryList = entryList;
    }
}
