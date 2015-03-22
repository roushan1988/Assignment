package com.coupondunia.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
public interface GenericManager<T, PK extends Serializable> {
    List<T> getAll();
    T get(PK id);
    boolean exists(PK id);
    T save(T object);
    T saveWithUpdate(T object);
    void remove(PK id);
    void flush();
}
