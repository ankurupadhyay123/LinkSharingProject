package com.daoimpl;

import com.daoapi.TopicDao;
import com.entities.Subscription;
import com.entities.Topic;
import com.entities.User;
import com.enums.Visibility;
import com.servicesapi.SubscriptionService;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by ankur on 14/7/17.
 */

@Repository
public class TopicDaoImpl implements TopicDao {

    @Autowired
    SubscriptionService subscriptionService;

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Topic topic = new Topic();

    @Override
    public boolean save(User user,String topicName, String visibility) {
        Visibility vis = Visibility.valueOf(visibility);
        topic.setVisibility(vis);
        topic.setDateCreated(new Date());
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select createdBy FROM Topic where name=:name ").
                setParameter("name",topicName);
        //System.out.println("result is "+query.getResultList());
        try{
            Object result = query.uniqueResult();
            //System.out.println("result is "+result);
            if(result == null){
                System.out.println("topic available");
                topic.setCreatedBy(user);
                topic.setName(topicName);
                session.beginTransaction();
                session.save(topic);
                session.getTransaction().commit();
                session.close();
                subscriptionService.subscribe(user,topic,"CASUAL");
                System.out.println("Successfully created " + topic.toString());
                return true;
            }else {
                return false;
            }
        }
        catch (NonUniqueResultException e)
        {
            System.out.println("user is not unique");
            return false;
        }
    }
}
