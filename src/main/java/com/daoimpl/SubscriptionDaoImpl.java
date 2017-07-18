package com.daoimpl;

import com.daoapi.SubscriptionDao;
import com.entities.Subscription;
import com.entities.Topic;
import com.entities.User;
import com.enums.Seriousness;
import com.enums.Visibility;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by ankur on 15/7/17.
 */

@Repository
public class SubscriptionDaoImpl implements SubscriptionDao{

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Subscription subscription = new Subscription();

    @Override
    public boolean subscribe(User user, Topic topic, String seriousness) {
        Seriousness serious = Seriousness.valueOf(seriousness);
        subscription.setSeriousness(serious);
        subscription.setDateCreated(new Date());
        subscription.setUser(user);
        subscription.setTopic(topic);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subscription);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + subscription.toString());
        return true;
    }

    @Override
    public Long getSubscriptions(User user) {
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("select count(user.userId) from Subscription where user.userId=:userId");
        //System.out.println(query);
        System.out.println("inside subs dao impl");
        query.setParameter("userId",user.getUserId());
        Long result =(Long) query.uniqueResult();
        System.out.println("sub result is "+result);
        return result;
    }
}
