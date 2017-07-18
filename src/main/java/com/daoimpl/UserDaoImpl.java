package com.daoimpl;

//import entities.User;

import com.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import com.daoapi.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public boolean save(User e) {
		e.setDateCreated(new Date());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return true;
		//return e.getUserId();
	}

	@Override
	public User findByID(Integer id) {
		return null;
	}

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.openSession();
		Query query= session.createQuery("from User where email=:email");
		//System.out.println(query);
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public User findByUsernameAndPass(String userName, String password) throws SQLException {
		Session session = sessionFactory.openSession();
		Query query= session.createQuery("from User where userName=:userName and password=:password");
		//System.out.println(query);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean updatePassword(String email, String password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.createQuery("update User set password=:password where email=:email");
		//System.out.println("update pass"+ query);
		query.setParameter("password", password);
		query.setParameter("email", email);
		query.executeUpdate();
		//System.out.println("working 1");

		query = session.createQuery("delete from OTPMapping where email=:email");
		query.setParameter("email",email);
		query.executeUpdate();
		session.getTransaction().commit();
		//System.out.println("working 2");
		return true;
	}

	@Override
	public byte[] getProfilePhoto(User user) {
		return user.getPhoto();
	}
}
