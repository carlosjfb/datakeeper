/**
 * CJFB 2017 - All rights reserved
 */
package com.datakeeper.persistence.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import com.datakeeper.entities.Cata;

/**
 * INTERFACE DAO CATA
 * 
 * @author cjflores
 * @version 1.0.0
 * @since 1.0
 *
 */
public interface CataDAO extends GenericDAO<Cata, Integer> {
	List<Cata> findAllByEsta(Integer idEsta) throws PersistenceException;

	boolean findByOBject(Cata obj) throws PersistenceException;
}
