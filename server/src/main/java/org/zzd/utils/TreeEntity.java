package org.zzd.utils;

import java.util.List;

public interface TreeEntity<E> {
    Long getId();

    Long getParentId();

    void setChildList(List<E> childList);
}