package com.coupondunia.bo.lookups;

import java.util.Collection;
import java.util.List;

/**
 * Created by roushan on 17/3/15.
 */
public interface ILookup<K, V> {
    V getByKey(K key);
    V getById(Long id);
    Collection<V> getAll();
    V add(V object);
    void addAll(List<V> objectList);
}
