package com.daoimpl;

import com.entities.User;
import org.hibernate.Session;
import com.daoapi.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
//import utils.HibernateUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public boolean save(User e) {
		if(e.getUserName() == "admin"){
			e.setAdmin(true);
		}else {
			e.setAdmin(false);
		}
		e.setDateCreated(new Date());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return true;
	}

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.openSession();
		Query query= session.createQuery("from User where email=:email");
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public User findByUsernameAndPass(String userName, String password) throws SQLException {
		Session session = sessionFactory.openSession();
		Query query= session.createQuery("from User where userName=:userName and password=:password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public boolean updatePassword(String email, String password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query= session.createQuery("update User set password=:password where email=:email");
		query.setParameter("password", password);
		query.setParameter("email", email);
		query.executeUpdate();

		query = session.createQuery("delete from OTPMapping where email=:email");
		query.setParameter("email",email);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean updateUserData(User user, MultipartFile newPhoto, String email){
		if(newPhoto.getSize() == 0){
		    Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query= session.createQuery("update User set firstName=:firstname , lastName=:lastname , userName=:username , lastUpdated=:date where email=:email");
			query.setParameter("firstname", user.getFirstName());
			query.setParameter("lastname", user.getLastName());
			query.setParameter("username", user.getUserName());
			query.setParameter("date",new Date());
			query.setParameter("email", email);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;
			}
			else if(newPhoto.getSize() > 0){
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query= session.createQuery("update User set firstName=:firstname,lastName=:lastname,userName=:username,photo=:photo where email=:email");
			query.setParameter("firstname", user.getFirstName());
			query.setParameter("lastname", user.getLastName());
			query.setParameter("username", user.getUserName());
			try { query.setParameter("photo",newPhoto.getBytes()); }
			catch (IOException e) { e.printStackTrace(); }
			query.setParameter("email", email);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;
		}else {
			return false;
		}
	}

	@Override
	public byte[] getProfilePhoto(User user) {
		return user.getPhoto();
	}
}
