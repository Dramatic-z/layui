package com.dnt.cloud.service.template;

/**
 * 代表一个“可被渲染”的对象，用来代替通常所用的<code>toString</code>来渲染对象的方法。实现此接口有如下好处：
 * 
 * <ol>
 * <li>
 * 在<code>toString</code>中难以处理异常，而该接口提供的方法支持异常处理。
 * </li>
 * <li>
 * 通过<code>toString</code>增加调试代码的难度。
 * </li>
 * <li>
 * 使用<code>toString</code>难以利用多个步骤来初始化对象，而该接口则更方便。
 * </li>
 * </ol>
 * 
 * <p>
 * 通过velocity event cartrige可以方便地处理<code>Renderable</code>接口。
 * </p>
 * @author sean won
 * @version $Id: Renderable.java, v 0.1 2009-9-25 上午11:25:13 fuyangbiao Exp $
 */
public interface Renderable {
    /**
     * 渲染对象。
     *
     * @return 渲染的结果
     */
    String render();
}
