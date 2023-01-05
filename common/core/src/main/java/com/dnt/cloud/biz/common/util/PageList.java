package com.dnt.cloud.biz.common.util;

import java.util.ArrayList;
import java.util.Collection;

import com.dnt.cloud.common.lang.Paginator;

/**
 * 包含“分页”信息的<code>List</code>。
 * 
 * @author sean won
 * @version $Id: PageList.java, v 0.1 2009-10-10 下午01:03:23 fuyangbiao Exp $
 */
@SuppressWarnings("unchecked")
public class PageList extends ArrayList {
    private static final long serialVersionUID = 2284038944571426900L;

    private Paginator         paginator;

    /**
     * 创建一个<code>PageList</code>。
     */
    public PageList() {
        paginator = new Paginator();
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public PageList(Collection c) {
        this(c, null);
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public PageList(Collection c, Paginator paginator) {
        super(c);
        this.paginator = (paginator == null) ? new Paginator() : paginator;
    }

    /**
     * 取得分页器，可从中取得有关分页和页码的所有信息。
     *
     * @return 分页器对象
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * 设置分页器。
     *
     * @param paginator 要设置的分页器对象
     */
    public void setPaginator(Paginator paginator) {
        if (paginator != null) {
            this.paginator = paginator;
        }
    }
}
