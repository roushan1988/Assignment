package com.coupondunia.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by roushan on 15/3/15.
 */
public interface GenericDao<T, PK extends Serializable> {
    List<T> getAll();
    List<T> getAllDistinct();
    T get(PK id);
    boolean exists(PK id);
    T save(T object);
    void remove(PK id);
    void remove(T object);
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);
    List<T> findByExample(final T exampleInstance);
    List<T> findByExample(final T exampleInstance, final String[] excludeProperty);
    List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria);
    List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria, final int firstResult, final int maxResults);
    void flush();
    public List<T> findByExampleHT(T exampleInstance);
    public List<T> findByExampleHT(T exampleInstance, int firstResult, int maxResults);

}
