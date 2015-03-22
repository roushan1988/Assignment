package com.coupondunia.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by roushan on 15/3/15.
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    protected final Logger log = Logger.getLogger(getClass());
    protected Class<T> persistentClass;
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public HibernateTemplate getHibernateTemplate() {
        if (hibernateTemplate == null) {
            if(sessionFactory == null){
                throw new AssertionError();
            }
            this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        }
        return this.hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired(required = true)
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return hibernateTemplate.loadAll(this.persistentClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAllDistinct() {
        return new ArrayList<T>(getAll());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(PK id) throws DataAccessException {
        T entity = (T) getHibernateTemplate().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean exists(PK id) {
        T entity = (T) getHibernateTemplate().get(this.persistentClass, id);
        return entity != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T save(T object) {
        return (T) getHibernateTemplate().merge(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T saveWithUpdate(T object) {
        getHibernateTemplate().persist(object);
        return (T) object;
    }

    @Override
    public void remove(PK id) {
        getHibernateTemplate().delete(this.get(id));
    }

    @Override
    public void remove(T object) {
        getHibernateTemplate().delete(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        String[] params = new String[queryParams.size()];
        Object[] values = new Object[queryParams.size()];

        int index = 0;
        for (String s : queryParams.keySet()) {
            params[index] = s;
            values[index++] = queryParams.get(s);
        }

        return (List<T>) getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, params, values);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByExample(final T exampleInstance) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException {
                Criteria crit = session.createCriteria(persistentClass);
                Example example = Example.create(exampleInstance);
                crit.add(example);
                return crit.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByExample(final T exampleInstance, final String[] excludeProperty) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException {
                Criteria crit = session.createCriteria(persistentClass);
                Example example = Example.create(exampleInstance);
                for (String exclude : excludeProperty) {
                    example.excludeProperty(exclude);
                }
                crit.add(example);
                return crit.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria) {
        return new ArrayList<T>((Collection<? extends T>) getHibernateTemplate().findByCriteria(detachedCriteria));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByDetachedCriteria(final DetachedCriteria detachedCriteria, final int firstResult, final int maxResults) {
        return new ArrayList<T>((Collection<? extends T>) getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByExampleHT(T exampleInstance) {
        return getHibernateTemplate().findByExample(exampleInstance);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByExampleHT(T exampleInstance, int firstResult, int maxResults) {
        return getHibernateTemplate().findByExample(exampleInstance, firstResult, maxResults);
    }

    @Override
    public void flush() {
        this.getHibernateTemplate().flush();
    }
}
