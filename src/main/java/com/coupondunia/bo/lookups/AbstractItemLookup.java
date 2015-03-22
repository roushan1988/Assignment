package com.coupondunia.bo.lookups;

import com.coupondunia.entity.LookupItem;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by roushan on 17/3/15.
 */
public abstract class AbstractItemLookup<T extends LookupItem> extends ObserverSystemResource implements ILookup<String, T> {

    // Members
    protected List<String> masterList = new ArrayList<String>();
    protected Map<String, T> nameLookupMap;
    protected Map<Long, T> idLookupMap;
    protected final Comparator<T> comparator;

    protected final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    public AbstractItemLookup(SystemResource systemResource, final String resourceName, Comparator<T> comparator) {
        super(resourceName);
        systemResource.registerForReload(this); // Registering against a system resource for trigger command for reloading itself
        this.comparator = comparator;
    }

    protected abstract List<T> getAllItems();

    @Override
    protected void onReload() {
        List<T> items = getAllItems();
        Collections.sort(items, comparator);

        List<String> nameList = new ArrayList<String>();
        Map<String, T> nameLookup = new HashMap<String, T>();
        Map<Long, T> idLookup = new HashMap<Long, T>();

        for (T item : items) {
            nameList.add(item.getName());
            nameLookup.put(item.getName(), item);
            idLookup.put(item.getId(), item);
        }

        LOCK.writeLock().lock();
        try {
            masterList = nameList;
            nameLookupMap = nameLookup;
            idLookupMap = idLookup;
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    @Override
    public T getByKey(String itemName) {
        LOCK.readLock().lock();
        try {
            return nameLookupMap.get(itemName);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    public T getById(Long id) {
        LOCK.readLock().lock();
        try {
            return idLookupMap.get(id);
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    public Collection<T> getAll() {
        LOCK.readLock().lock();
        try {
            return Collections.unmodifiableCollection(idLookupMap.values());
        } finally {
            LOCK.readLock().unlock();
        }
    }

    @Override
    public T add(T object){
        LOCK.writeLock().lock();
        try {
            masterList.add(object.getName());
            nameLookupMap.put(object.getName(), object);
            idLookupMap.put(object.getId(), object);
        } finally {
            LOCK.writeLock().unlock();
        }
        return object;
    }

    @Override
    public void addAll(List<T> objectList){
        LOCK.writeLock().lock();
        try {
            for(T object: objectList){
                masterList.add(object.getName());
                nameLookupMap.put(object.getName(), object);
                idLookupMap.put(object.getId(), object);
            }
        } finally {
            LOCK.writeLock().unlock();
        }
    }
}
