/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datakeeper.entities.Cata;
import com.datakeeper.persistence.dao.CataDAO;

/**
 * IMPLEMENTACIÓN DAO CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
@Repository
public class CataDAOImpl extends GenericDAOImpl<Cata, Integer> implements
		CataDAO {

	@Override
	public List<Cata> findAllByEsta(Integer idEsta) throws PersistenceException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria.add(Restrictions.eq("idEsta", idEsta));
			criteria.addOrder(Order.asc("codi"));
			@SuppressWarnings("unchecked")
			List<Cata> tmp = criteria.list();
			return tmp;
		} catch (Exception e) {
			throw new PersistenceException("Error in findAllByEsta:"
					+ e.getMessage(), e);
		}
	}

}
