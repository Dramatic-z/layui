package com.dnt.cloud.common.domain;

import java.io.Serializable;

/**
 * <p>键值对</p>
 * @author sean won
 * @version $Id: Kvp.java, v 0.1 2010-12-18 上午11:00:21 fuyangbiao Exp $
 */
public class Kvp implements Serializable {
    private static final long serialVersionUID = -1192195739822408992L;
    /** 键 */
    private String            key;
    /** 值 */
    private String            value;

    /**
     * 默认构造
     */
    public Kvp() {
    }

    /**
     * 根据键值构造
     * @param key
     * @param value
     */
    public Kvp(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
