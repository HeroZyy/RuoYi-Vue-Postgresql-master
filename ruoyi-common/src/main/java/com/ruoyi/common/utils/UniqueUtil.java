package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueUtil {
    /**
     * 去重函数
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new HashSet<>(); // 创建 HashSet 集合用于记录不同的对象
        List<T> resultList = new ArrayList<>(); // 创建新的 ArrayList 用于存储去重后的对象
        for (T item : list) { // 遍历原始 ArrayList
            if (!set.contains(item)) { // 检查该对象是否已存在
                set.add(item); // 如果不存在，则将该对象添加到 HashSet 中
                resultList.add(item); // 并将其添加到结果列表中
            }
        }
        return resultList; // 返回去重后的 ArrayList
    }
}
