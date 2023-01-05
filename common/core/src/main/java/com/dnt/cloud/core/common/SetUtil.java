package com.dnt.cloud.core.common;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.dnt.cloud.common.lang.diagnostic.Profiler;

public class SetUtil {

    public static <T> String toString(Set<T> set) {
        if (set == null || set.size() == 0) {
            return null;
        }
        return ListUtil.appendJoin(set, ",");
    }

    public static <T> String joinString(Set<T> set) {
        if (set == null || set.size() == 0) {
            return null;
        }
        return StringUtils.join(set, ',');
    }

    public static Set<String> parseString(String param) {
        if (StringUtils.isBlank(param)) {
            return new LinkedHashSet<String>();
        }
        return new LinkedHashSet<String>(Arrays.asList(StringUtils.split(param, ',')));
    }

    public static void main(String[] args) throws InterruptedException {
        Set<String> set = new LinkedHashSet<String>();
        set.add("b");
        set.add("a");
        set.add("c");
        System.out.println(SetUtil.toString(set));
        System.out.println(SetUtil.parseString("b,b,c,a,,"));

        System.out.println(SetUtil.toString(null));
        System.out.println(SetUtil.parseString(null));
        System.out.println(SetUtil.parseString(""));
        System.out.println(SetUtil.parseString("  "));

        Profiler.start();
        Thread.sleep(100);
        Profiler.enter("a");
        Thread.sleep(200);
        Profiler.release();
        Profiler.enter("b");
        Thread.sleep(300);
        Profiler.release();
        Profiler.release();
        System.out.println(Profiler.dump());
    }

}
