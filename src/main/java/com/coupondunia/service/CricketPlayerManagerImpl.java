package com.coupondunia.service;

import com.coupondunia.dao.CricketPlayerDao;
import com.coupondunia.entity.CricketPlayer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by roushan on 18/3/15.
 */
public class CricketPlayerManagerImpl extends GenericManagerImpl<CricketPlayer, Long> implements CricketPlayerManager {

    private CricketPlayerDao cricketPlayerDao;

    @Autowired
    public CricketPlayerManagerImpl(CricketPlayerDao cricketPlayerDao) {
        super(cricketPlayerDao);
        this.cricketPlayerDao = cricketPlayerDao;
    }
}
