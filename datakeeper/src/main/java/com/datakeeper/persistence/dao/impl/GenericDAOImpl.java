/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao.impl;

import java.io.Serializable;
import javax.persistence.PersistenceException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.datakeeper.persistence.dao.GenericDAO;
import lombok.Data;

/**
 * IMPLEMENTACIÓN DAO GENERICO
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 * @param <T>
 * @param <PK>
 */
@Data
public class GenericDAOImpl<T, PK extends Serializable> implements
		GenericDAO<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public PK save(T objeto) throws PersistenceException {
		try {
			return (PK) getSessionFactory().getCurrentSession().save(objeto);
		} catch (Exception e) {
			System.out.println("Error en GenericDAOImpl - save: " + e);
			return null;
		}
	}

	@Override
	public void update(T objeto) throws PersistenceException {
		try {
			getSessionFactory().getCurrentSession().update(objeto);
		} catch (Exception e) {
			System.out.println("Error en GenericDAOImpl - update: " + e);
		}
	}

}
