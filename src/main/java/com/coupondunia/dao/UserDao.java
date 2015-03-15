package com.coupondunia.dao;

import com.coupondunia.entity.User;

/**
 * Created by roushan on 15/3/15.
 */
public interface UserDao extends GenericDao<User, Long> {

    public User getByUsername(String username, String password);
    public User getByEmail(String email, String password);
}
