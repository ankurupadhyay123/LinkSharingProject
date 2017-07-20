package com.daoimpl;

import com.daoapi.SubscriptionDao;
import com.entities.Subscription;
import com.entities.Topic;
import com.entities.User;
import com.enums.Seriousness;
import com.enums.Visibility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
        query.setParameter("userId",user.getUserId());
        Long result =(Long) query.uniqueResult();
        return result;
    }

    @Override
    public Long getSubscriptionsForEachTopic() {
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("select count(createdBy.userId) from Topic where name In (select topic.name from Subscription)");
        //query.setParameter("userId",user.getUserId());
        Long result =(Long) query.uniqueResult();
        return result;
    }

    @Override
    public List<Topic> getSubscribedTopics(User user) {
        Session session = sessionFactory.openSession();
        List<Topic> subscriptionList = session.createQuery("select topic from Subscription s where s.user.userId=:userId").setParameter("userId",user.getUserId()).list();
        session.close();
        return subscriptionList;
    }
}
