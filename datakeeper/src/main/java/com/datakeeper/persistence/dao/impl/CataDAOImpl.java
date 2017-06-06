/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
	public boolean checkIfObjectExist(Cata entitie) throws PersistenceException {
		boolean find = false;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria = criteria.add(Restrictions.eq("codi", entitie.getCodi()));
			if (entitie.getNomb() != null) {
				criteria = criteria.add(Restrictions.eq("nomb",
						entitie.getNomb()));
			}
			if (entitie.getValo() != null) {
				criteria = criteria.add(Restrictions.eq("valo",
						entitie.getValo()));
			}
			criteria = criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			List<Cata> tmp = criteria.list();
			if (tmp != null && tmp.size() > 0) {
				find = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(
					"Error en CataDAOImpl - checkIfObjectExist:"
							+ e.getMessage(), e);
		}
		return find;
	}

	@Override
	public Cata getObjectCata(Cata entitie) throws PersistenceException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria = criteria.add(Restrictions.eq("codi", entitie.getCodi()));
			if (entitie.getNomb() != null) {
				criteria = criteria.add(Restrictions.eq("nomb",
						entitie.getNomb()));
			}
			if (entitie.getValo() != null) {
				criteria = criteria.add(Restrictions.eq("valo",
						entitie.getValo()));
			}
			criteria = criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			return (Cata) criteria.uniqueResult();
		} catch (Exception e) {
			throw new PersistenceException(
					"Error en CataDAOImpl - getObjectCata:" + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cata> findByCodi(String codi) throws PersistenceException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria.add(Restrictions.eq("codi", codi));
			criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			criteria.addOrder(Order.asc("codi"));
			return criteria.list();
		} catch (Exception e) {
			throw new PersistenceException("Error en CataDAOImpl - findByCodi:"
					+ e.getMessage(), e);
		}
	}

	@Override
	public Integer counRecords() throws PersistenceException {
		Integer count = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Cata.class);
			criteria = criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			criteria.setProjection(Projections.rowCount());
			count = (int) (long) criteria.uniqueResult();
		} catch (Exception e) {
			System.out.println("Error en CataDAOImpl - counRecors: " + e);
		}
		return count;
	}
}
