package com.daoimpl;

import com.daoapi.OTPDao;
import com.entities.OTPMapping;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ankur on 14/7/17.
 */

@Repository
//@Transactional
public class OTPDaoImpl implements OTPDao {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public boolean save(OTPMapping otp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(otp);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + otp.toString());
        return true;
    }

    @Override
    public OTPMapping findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("from OTPMapping where email=:email");
        //System.out.println(query);
        query.setParameter("email", email);
        OTPMapping otp = (OTPMapping) query.uniqueResult();
        session.close();
        return otp;
    }
}
