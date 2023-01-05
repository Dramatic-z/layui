package com.dnt.cloud.layui.web.domain;

/**
 * 单例
 * 懒惰模式: 用到时再初始化, 线程不安全, 可以在方法上使用synchronized关键字实现线程安全
 * 饥饿模式: 类加载时就初始化, 线程安全
 *
 * @author dramatic
 */
public class SingleCase {

    /**
     * 懒惰模式
     */
    private static SingleCase SingleCase = null;

    /**
     * 饥饿模式
     * 不加 final 使用反射可修改原有对象的属性。
     * 通过反射调用单例类的构造函数，创建新的单例对象，使用 field去访问到原本的单例对象，使用set方法把新创建的单例对象赋值给原本的对象。
     */
    /*private static final SingleCase SingleCase = new SingleCase();*/

    private SingleCase() {

    }

    public static SingleCase getInstance() {
        if (SingleCase == null) {
            SingleCase = new SingleCase();
        }
        return SingleCase;
    }


}
