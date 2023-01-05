package com.dnt.cloud.service.velocity;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;

import com.dnt.cloud.service.template.Renderable;

/**
 * <p>渲染<code>Renderable</code>的event handler。</p>
 * @author sean won
 * @version $Id: RenderableInsertionEventHandler.java, v 0.1 2009-9-25 上午11:46:10 fuyangbiao Exp $
 */
public class RenderableInsertionEventHandler implements ReferenceInsertionEventHandler {

    /**
     * 插入value。
     *
     * @param reference reference名称
     * @param value 要插入的reference值
     */
    public Object referenceInsert(String reference, Object value) {
        if (value instanceof Renderable) {
            return ((Renderable) value).render();
        }

        return value;
    }

}
