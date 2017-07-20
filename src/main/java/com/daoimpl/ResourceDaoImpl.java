package com.daoimpl;

import com.daoapi.ResourceDao;
import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.Topic;
import com.entities.User;
import com.enums.ResourceEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by ankur on 19/7/17.
 */

@Repository
public class ResourceDaoImpl implements ResourceDao {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Resource resource = new Resource();
    private ReadingItem readingItem = new ReadingItem();

    @Override
    public boolean saveLinkUrl(User user, String linkUrl, String description, Topic topic) {
        ResourceEnum resourceEnum = ResourceEnum.valueOf("LINK_RESOURCE");
        resource.setCreatedBy(user);
        resource.setDateCreated(new Date());
        resource.setDescription(description);
        resource.setResourceEnum(resourceEnum);
        resource.setUrl(linkUrl);
        resource.setTopic(topic);

        readingItem.setResource(resource);
        readingItem.setUser(user);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(resource);
        session.save(readingItem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean saveDocUrl(User user, String docUrl, String description, Topic topic) {
        ResourceEnum resourceEnum = ResourceEnum.valueOf("DOCUMENT_RESOURCE");
        resource.setCreatedBy(user);
        resource.setDateCreated(new Date());
        resource.setDescription(description);
        resource.setResourceEnum(resourceEnum);
        resource.setUrl(docUrl);
        resource.setTopic(topic);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(resource);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
