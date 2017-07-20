package com.daoimpl;

import com.daoapi.ReadingItemDao;
import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.Topic;
import com.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ankur on 21/7/17.
 */

@Repository
public class ReadingItemDaoImpl implements ReadingItemDao{

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<Resource> getInboxResources(User user) {
        Session session = sessionFactory.openSession();
        List<Resource> readingItemList = session.createQuery("select resource from ReadingItem r where r.user.userName=:username").setParameter("username",user.getUserName()).list();
        session.close();
        return readingItemList;
    }
}
