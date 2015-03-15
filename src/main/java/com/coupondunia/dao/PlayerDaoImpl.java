package com.coupondunia.dao;

import com.coupondunia.entity.CricketPlayer;
import com.coupondunia.entity.Player;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
@Repository
public class PlayerDaoImpl extends GenericDaoHibernate<CricketPlayer, Long> implements PlayerDao {
    //Session factory injected by spring context
    private SessionFactory sessionFactory;

    public PlayerDaoImpl() {
        super(CricketPlayer.class);
    }

    public PlayerDaoImpl(Class<CricketPlayer> persistentClass) {
        super(persistentClass);
    }

    public PlayerDaoImpl(Class<CricketPlayer> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

    //This setter will be used by Spring context to inject the sessionFactory instance
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addCricketPlayer(Player player) {
        this.sessionFactory.getCurrentSession().save(player);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Player> getAllPlayers() {
        return this.sessionFactory.getCurrentSession().createQuery("from CricketPlayer").list();
    }

    @Override
    public void deletePlayer(Integer id) {
        CricketPlayer player = (CricketPlayer) sessionFactory.getCurrentSession()
                .load(CricketPlayer.class, id);
        if (null != player) {
            this.sessionFactory.getCurrentSession().delete(player);
        }

    }
}
