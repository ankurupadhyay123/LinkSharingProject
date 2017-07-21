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

@Repository
public class ReadingItemDaoImpl implements ReadingItemDao{

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<ReadingItem> getInboxResources(User user) {
        Session session = sessionFactory.openSession();
        List<ReadingItem> readingItemList = session.createQuery("select resource from ReadingItem r where r.user.userName=:username").setParameter("username",user.getUserName()).list();
        session.close();
        return readingItemList;
    }

    @Override
    public List<Resource> getRecentResources() {
        Session session = sessionFactory.openSession();
        List<Resource> readingItemList = session.createQuery("select resource from ReadingItem r order by r.resource.dateCreated").setMaxResults(5).list();
        session.close();
        return readingItemList;
    }
}
