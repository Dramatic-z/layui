package com.dnt.cloud.layui.web.utils;

import com.dnt.cloud.layui.web.domain.ApiSyncCategoryRequestDomain;
import com.dnt.cloud.layui.web.domain.NrcSyncCategoryDomain;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 因为BeanUtils.copyProperties只能给目标对象的属性赋值，却不能在List集合下循环赋值，因此添加该方法
 * 如：List<AdminEntity> 赋值到 List<AdminVo> ，List<AdminVo>中的 AdminVo 属性都会被赋予到值
 * S: 数据源类 ，T: 目标类::new(eg: AdminVo::new)
 *
 * @author dramatic
 */
public class CopyObjectListUtils extends BeanUtils {

    /**
     * @param sources 数据源类
     * @param target  目标类
     * @param <S>     <S>
     * @param <T>     <T>
     * @return return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        if(CollectionUtils.isNotEmpty(sources)){
            List<T> list = new ArrayList<>(sources.size());
            for (Object source : sources) {
                T t = target.get();
                BeanUtils.copyProperties(source, t);
                list.add(t);
            }
            return list;
        }
        return null;
    }



    /**
     * 深度复制属性
     *
     * @param source 复制数据源
     * @return
     */
    private NrcSyncCategoryDomain convertCategoryDomain(NrcSyncCategoryDomain source) {
        NrcSyncCategoryDomain target = new NrcSyncCategoryDomain();
        if (source == null) {
            return null;
        }
        //复制基础数据
        BeanUtils.copyProperties(source, target);
        List<NrcSyncCategoryDomain> childList = source.getChildList();
        if (CollectionUtils.isEmpty(childList)) {
            return target;
        }
        //复制深度属性
        target.setChildList(
                //两个参数分别代表 需要拷贝的集合 , 拷贝至集合的对象  （::new）为lambda表达式经过eta转换后的简写
                CopyObjectListUtils.copyListProperties(
                        source.getChildList(), NrcSyncCategoryDomain::new));
        //复制二级深度属性
        childList.forEach(data -> {
            List<NrcSyncCategoryDomain> childList1 = target.getChildList();
            if (CollectionUtils.isNotEmpty(childList1)) {
                childList1.forEach(data2 -> {
                    if (data.getCode().equals(data2.getCode())) {
                        data2.setChildList(CopyObjectListUtils.copyListProperties(
                                data.getChildList(), NrcSyncCategoryDomain::new));
                    }
                });
            }
        });
        return target;
    }

}
