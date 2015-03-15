package com.coupondunia.service;

import com.coupondunia.dao.GenericDaoHibernate;
import com.coupondunia.dao.UserDao;
import com.coupondunia.dao.UserDaoHibernate;
import com.coupondunia.entity.User;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager {

    private UserDao userDao;

    @Autowired
    public UserManagerImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String email, String password) {
        if(username != null){
            return userDao.getByUsername(username, password);
        }
        if(password != null){
            return userDao.getByUsername(email, password);
        }
        return null;
    }
}
