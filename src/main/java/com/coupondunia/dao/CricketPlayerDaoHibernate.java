package com.coupondunia.dao;

import com.coupondunia.entity.CricketPlayer;
import com.coupondunia.entity.Player;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
@Repository("cricketPlayerDao")
public class CricketPlayerDaoHibernate extends GenericDaoHibernate<CricketPlayer, Long> implements CricketPlayerDao {

    public CricketPlayerDaoHibernate() {
        super(CricketPlayer.class);
    }
}
