package com.fdmgroup.judgecollege.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.judgecollege.entity.User;

public class UserDao extends Dao<User, String> {

	public UserDao() {
		super(User.class, String.class);
	}
	
	@Transactional
	public List<User> findByUsername(String username){
		TypedQuery<User> q = em.createQuery("select u from User u where LOWER(u.username) like :username", User.class);
		q.setParameter("name", "%" + username.toLowerCase() + "%");
		List<User> users = q.getResultList();
		return users;
	}

}
