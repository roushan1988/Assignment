package com.coupondunia.dao;

import com.coupondunia.entity.CricketPlayer;
import com.coupondunia.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by roushan on 15/3/15.
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    UserDaoHibernate(){
        super(User.class);
    }

    @Override
    public User getByUsername(String username, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass)
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> userList = findByDetachedCriteria(criteria, 0, 1);
        if(userList!=null && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getByEmail(String email, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistentClass)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> userList = findByDetachedCriteria(criteria, 0, 1);
        if(userList!=null && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

}
