package com.coupondunia.service;

import com.coupondunia.entity.User;

/**
 * Created by roushan on 15/3/15.
 */
public interface UserManager extends GenericManager<User, Long> {

    public User login(String username, String email, String password);

}
