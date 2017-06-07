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

import com.datakeeper.entities.Arch;
import com.datakeeper.persistence.dao.ArchDAO;
import com.datakeeper.utils.VarConstant;

/**
 * IMPLEMENTACIÓN DAO ARCH
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
@Repository
public class ArchDAOImpl extends GenericDAOImpl<Arch, Integer> implements
		ArchDAO {

	@Override
	public List<Arch> findAllByEsta(Integer idEsta) throws PersistenceException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Arch.class);
			criteria.add(Restrictions.eq("idEsta.idCata", idEsta));
			criteria.addOrder(Order.asc("nomb"));
			@SuppressWarnings("unchecked")
			List<Arch> tmp = criteria.list();
			return tmp;
		} catch (Exception e) {
			throw new PersistenceException(
					"Error en ArchDAOImpl - findAllByEsta:" + e.getMessage(), e);
		}
	}

	@Override
	public Integer counRecords() throws PersistenceException {
		Integer count = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Arch.class);
			criteria = criteria.add(Restrictions.eq("idEsta",
					VarConstant.CATA_ID_CATA_ACTIVO));
			criteria.setProjection(Projections.rowCount());
			count = (int) (long) criteria.uniqueResult();
		} catch (Exception e) {
			System.out.println("Error en ArchDAOImpl - counRecors: " + e);
		}
		return count;
	}
}
