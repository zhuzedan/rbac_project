package org.zzd.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote 解析树形数据工具类
 * @author zzd
 * @date 2023-08-03 14:51  
 */
public class TreeParser {
    /**
     * @apiNote 解析树形数据
     * @param topId 顶层id
     * @param entityList 实体列表
     * @return {@link List }<{@link E }>
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(Long topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        // 获取顶层元素集合
        Long parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    /**
     * @apiNote 获取子数据集合
     * @param id id
     * @param entityList 实体列表
     * @return {@link List }<{@link E }>
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(Long id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        Long parentId;
        // 子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        // 子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}