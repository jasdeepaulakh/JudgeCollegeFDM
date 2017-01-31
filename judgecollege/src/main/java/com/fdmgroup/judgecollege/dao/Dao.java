package com.fdmgroup.judgecollege.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.judgecollege.entity.DataObject;
import com.fdmgroup.judgecollege.exceptions.EntityNotExistException;
import com.fdmgroup.judgecollege.exceptions.InvalidPrimaryKeyException;
import com.fdmgroup.judgecollege.exceptions.NotCompleteEntityException;
import com.fdmgroup.judgecollege.exceptions.NullArgumentException;

public abstract class Dao <E extends DataObject,K>{

	@PersistenceContext
	protected EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	private Class<E> entity;
	private Class<K> pK;

	public Dao(Class<E> entity, Class<K> pK) {
		super();
		this.entity = entity;
		this.pK = pK;
	}
	
	@Transactional
	public E findByPrimaryKey(K primaryKey) throws NullArgumentException, InvalidPrimaryKeyException {
		if(primaryKey == null)
			throw new NullArgumentException("You did not insert an argument.");
		E instance = em.find(entity, primaryKey);
		if(instance == null)
			throw new InvalidPrimaryKeyException("You entered a PK that does not have correponding object in database.");
		return instance;
	}
	
	@Transactional
	public void createEntry(E entity) throws NullArgumentException, NotCompleteEntityException{
		if(entity == null) {
			throw new NullArgumentException("You did not insert an argument.");
		}
		try{
			em.persist(entity);
		} catch(ConstraintViolationException e){
			throw new NotCompleteEntityException("Some reqired fields are missing or you are using the wrong data type.");
		}
	}

	@Transactional
	public E updateEntry(E entity) throws NullArgumentException, NotCompleteEntityException, EntityNotExistException {
		if(entity == null)
			throw new NullArgumentException("You did not insert an argument.");
		E updatedEntity = null;
		try{
			updatedEntity =em.merge(entity);
		} catch(ConstraintViolationException e){
			throw new NotCompleteEntityException("Some reqired fields are missing or you are using the wrong data type.");
		} catch(IllegalArgumentException e){
			throw new EntityNotExistException("The Entity you want to update does not exist in DB.");
		}
		return updatedEntity;
	}

	@Transactional
	public void removeEntry(E entity) throws EntityNotExistException{
		try{
			em.remove(entity);
		} catch(IllegalArgumentException e){
			throw new EntityNotExistException("The Entity you want to remove does not exist in DB.");
		}
		
	}

	@Transactional
	public List<E> listAll() {
		TypedQuery<E> q = (TypedQuery<E>) em.createQuery("select e from " + entity.getSimpleName() + " e");
		List<E> resultList = q.getResultList();
		return resultList;
	}
}
