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
import com.datakeeper.utils.VarConstant;

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
			throw new PersistenceException(
					"Error en CataDAOImpl - findAllByEsta:" + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findByOBject(Cata obj) throws PersistenceException {
		boolean find = false;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria = criteria.add(Restrictions.eq("codi", obj.getCodi()));
			if (obj.getNomb() != null) {
				criteria = criteria.add(Restrictions.eq("nomb", obj.getNomb()));
			}
			if (obj.getValo() != null) {
				criteria = criteria.add(Restrictions.eq("valo", obj.getValo()));
			}
			criteria = criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			List<Cata> tmp = criteria.list();
			if (tmp != null && tmp.size() > 0) {
				find = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(
					"Error en CataDAOImpl - findByObject:" + e.getMessage(), e);
		}
		return find;
	}
}
